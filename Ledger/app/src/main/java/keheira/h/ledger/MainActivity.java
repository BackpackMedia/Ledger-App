package keheira.h.ledger;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView listView;
    private ArrayList<Payee> log;
    private ListAdapter adapter;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        db = DatabaseHandler.getInstance(this);
        getInfo();
    }

    private void deletePerson(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.deletePerson(log.get(position));
                log.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.add:
                addPerson();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addPerson() {
        Intent i = new Intent(MainActivity.this, Add_Payee.class);
        startActivity(i);
    }
    private void getInfo(){
        log = db.getPeople();
        if (log.size() != 0) {
            //list adapter
            adapter = new ListAdapter(this, log);
            listView.setAdapter(adapter);
            deletePerson();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        getInfo();
        adapter.notifyDataSetChanged();
    }
}
