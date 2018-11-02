package keheira.h.ledger

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import java.text.DecimalFormat
import java.util.ArrayList

/**
 * Created by Keheira on 5/26/2017.
 */

class ListAdapter(context: Context, payee: ArrayList<Payee>) : ArrayAdapter<Payee>(context, 0, payee) {
    private val resultsList: ArrayList<Payee>? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val payee = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ledger_items, parent, false)
        }
        val name = convertView!!.findViewById<View>(R.id.name) as TextView
        val amount = convertView.findViewById<View>(R.id.amount) as TextView
        //val date = convertView.findViewById<View>(R.id.date) as TextView

        name.text = payee!!.name
        //format amount
        val df = DecimalFormat("0.00")
        amount.text = df.format(payee.amount)

        //date.text = "12/12/99"
        return convertView
    }
}
