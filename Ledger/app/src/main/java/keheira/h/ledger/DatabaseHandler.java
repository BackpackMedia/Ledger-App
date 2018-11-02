package keheira.h.ledger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Keheira on 5/29/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler instance = null;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PeopleManager";
    private static final String TABLE_PEOPLE = "people";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_AMOUNT = "amount";
    //private static final Date KEY_DATE = null;
    private static final String KEY_REASON = "reason";

    public static DatabaseHandler getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHandler(context.getApplicationContext());
        }
        return instance;
    }

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PEOPLE_TABLE = "CREATE TABLE "+ TABLE_PEOPLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_NUMBER + " TEXT," +
                KEY_AMOUNT + " TEXT," +  KEY_REASON + " TEXT" + ")";
        db.execSQL(CREATE_PEOPLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);
        onCreate(db);
    }

    public void addPerson(Payee payee){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, payee.getName());
        values.put(KEY_NUMBER, payee.getNumber());
        values.put(KEY_AMOUNT, payee.getAmount());
        //values.put(KEY_DATE, payee.getDate());
        values.put(KEY_REASON, payee.getReason());

        db.insert(TABLE_PEOPLE, null, values);
        db.close();
    }

    Payee getPayee(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PEOPLE, new String[]{KEY_ID, KEY_NAME, KEY_NUMBER, KEY_AMOUNT, KEY_REASON}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Payee payee = new Payee(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Double.parseDouble(cursor.getString(3)),cursor.getString(4));
        cursor.close();
        db.close();
        return payee;
    }

    public ArrayList<Payee> getPeople(){
        ArrayList<Payee> personList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PEOPLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Payee payee = new Payee();
                payee.setId(Integer.parseInt(cursor.getString(0)));
                payee.setName(cursor.getString(1));
                payee.setNumber(cursor.getString(2));
                payee.setAmount(Double.parseDouble(cursor.getString(3)));
                //payee.setDate();
                payee.setReason(cursor.getString(4));
                personList.add(payee);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return personList;
    }

    public void deletePerson(Payee payee){
        SQLiteDatabase db = this.getWritableDatabase();
        db. delete(TABLE_PEOPLE, KEY_ID + "=?", new String[]{String.valueOf(payee.getId())});
        db.close();
    }

    public int getPeopleCount(){
        String countQuery = "SELECT * FROM " + TABLE_PEOPLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
