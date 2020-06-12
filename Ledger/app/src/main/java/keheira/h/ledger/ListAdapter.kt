package keheira.h.ledger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import java.text.DecimalFormat
import java.util.ArrayList

/**
 * Created by Keheira on 5/26/2017.
 */

class ListAdapter(private val payeeList: ArrayList<Payee>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.payee_item, parent, false))
    }

    override fun getItemCount() : Int { return payeeList.size }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var payee = payeeList[position]
        holder.name.text = payee.name
        //format amount
        val df = DecimalFormat("0.00")
        holder.amount.text = df.format(payee.amount)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val amount = itemView.findViewById<TextView>(R.id.amount)
        //val date = convertView.findViewById<View>(R.id.date) as TextView
    }
}
