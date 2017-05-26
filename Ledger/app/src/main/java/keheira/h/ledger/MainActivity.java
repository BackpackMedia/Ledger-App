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
        ArrayList<String> log = new ArrayList<>();

        //add in names
        log.add("Mom");

        //list adapter
        ArrayAdapter<String> items = new ArrayAdapter<String>(this, R.layout.ledger_items, log);
        listView.setAdapter(items);
    }
}
