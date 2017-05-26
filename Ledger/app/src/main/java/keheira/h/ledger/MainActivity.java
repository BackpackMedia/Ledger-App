package keheira.h.ledger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        ArrayList<Payee> log = new ArrayList<>();

        //add in names
        log.add(new Payee("Mom", 100.50));
        log.add(new Payee("Dad", 50.00));
        log.add(new Payee("Brother", 60.00));
        log.add(new Payee("Cousin", 10.00));

        //list adapter
        ListAdapter adapter = new ListAdapter(this, log);
        listView.setAdapter(adapter);
    }
}
