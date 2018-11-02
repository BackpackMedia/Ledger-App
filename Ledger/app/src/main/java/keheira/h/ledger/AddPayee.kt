package keheira.h.ledger

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import android.widget.EditText
import android.R.attr.password
import android.app.DatePickerDialog
import java.util.*

/**
 * Created by Keheira on 5/28/2017.
 */

class AddPayee : Activity() {
    private var nametxt: EditText? = null
    private var numbertxt: EditText? = null
    private var amounttxt: EditText? = null
    private var reasontxt: EditText? = null
    //private var datetxt: EditText? = null
    private var name: String? = null
    private var reason: String? = null
    private var number: String? = null
    private var amount: Double? = null
    //private var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_payee)

        nametxt = findViewById<View>(R.id.nameTxt) as EditText
        numbertxt = findViewById<View>(R.id.numTxt) as EditText
        amounttxt = findViewById<View>(R.id.amountTxt) as EditText
        reasontxt = findViewById<View>(R.id.reasonTxt) as EditText
        //datetxt = findViewById<View>(R.id.dateTxt) as EditText
    }

    /*fun setDate(view: View){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth ->
            date = "${monthOfYear +1} / $dayOfMonth / $year"
            datetxt!!.setText(date)
        }, year, month, day)
        dpd.show()
    }*/

    fun addPerson(view: View) {
        name = nametxt!!.text.toString()
        number = numbertxt!!.text.toString()
        amount = java.lang.Double.parseDouble(amounttxt!!.text.toString())
        reason = reasontxt!!.text.toString()
        if (checkFields()) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Message")
            builder.setMessage("Make sure all fields are filled in")
                    .setPositiveButton("OK"
                    ) { dialog, which -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        } else {
            val db = DatabaseHandler.getInstance(this)
            val person = Payee(name, number, amount!!, reason)
            db.addPerson(person)
            finish()
        }
    }

    private fun checkFields(): Boolean {
        return name == null || number == null ||
                amount == null || reason == null
    }
}
