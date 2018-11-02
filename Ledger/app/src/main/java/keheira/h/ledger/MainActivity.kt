package keheira.h.ledger

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

import java.util.ArrayList

class MainActivity : Activity() {
    private var listView: ListView? = null
    private var log: ArrayList<Payee>? = null
    private var adapter: ListAdapter? = null
    private var db: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<View>(R.id.listView) as ListView
        db = DatabaseHandler.getInstance(this)
        getInfo()
    }

    private fun deletePerson() {
        listView!!.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            db!!.deletePerson(log!![position])
            log!!.removeAt(position)
            adapter!!.notifyDataSetChanged()
            true
        }
    }

    fun addPerson(view: View) {
        val i = Intent(this@MainActivity, AddPayee::class.java)
        startActivity(i)
    }

    private fun getInfo() {
        log = db!!.people
        if (log!!.size != 0) {
            //list adapter
            adapter = ListAdapter(this, log!!)
            listView!!.adapter = adapter
            deletePerson()
        }
    }

    public override fun onResume() {
        super.onResume()
        getInfo()
        if(adapter != null)
            adapter!!.notifyDataSetChanged()
    }
}
