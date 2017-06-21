package keheira.h.ledger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.EditText;
import static android.R.attr.password;

/**
 * Created by Keheira on 5/28/2017.
 */

public class Add_Payee extends Activity {
    private EditText nametxt, numbertxt, amounttxt, reasontxt;
    private String name, reason, number;
    private Double amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_payee);

        nametxt = (EditText)findViewById(R.id.nameTxt);
        numbertxt = (EditText)findViewById(R.id.numTxt);
        amounttxt = (EditText)findViewById(R.id.amountTxt);
        reasontxt = (EditText)findViewById(R.id.reasonTxt);
    }

    public void addPerson(View view){
        name = nametxt.getText().toString();
        number = numbertxt.getText().toString();
        amount = Double.parseDouble(amounttxt.getText().toString());
        reason = reasontxt.getText().toString();
        if(checkFields()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Message");
            builder.setMessage("Make sure all fields are filled in")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            DatabaseHandler db = DatabaseHandler.getInstance(this);
            Payee person = new Payee(name, number, amount, reason);
            db.addPerson(person);
            finish();
        }
    }

    private boolean checkFields() {
        return (name == null || number == null ||
                amount == null || reason == null);
    }
}
