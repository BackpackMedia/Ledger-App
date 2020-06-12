package keheira.h.ledger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import keheira.h.ledger.databinding.PayeeListBinding
import java.util.*

class PayeeListFragment : Fragment() {
    private lateinit var binding: PayeeListBinding
    private var log: ArrayList<Payee>? = null
    private var adapter: ListAdapter? = null
    private var db: DatabaseHandler? = null

    interface RefreshAdapterListener {
        fun refreshAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PayeeListBinding.inflate(
                inflater,
                container,
                false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        db = DatabaseHandler.getInstance(context)
        binding.addPayeeBtn.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_addPayee)
        }
    }

    private fun deletePerson() {
//        binding.payeeListview.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
//            db!!.deletePerson(log!![position])
//            log!!.removeAt(position)
//            adapter!!.notifyDataSetChanged()
//            true
//        }
    }

    private fun getInfo() {
        log = db!!.people
        if (log!!.size == 0){
            binding.emptyListText.visibility = View.VISIBLE
            binding.payeeListview.visibility = View.GONE
        }else {
            binding.emptyListText.visibility = View.GONE
            binding.payeeListview.visibility = View.VISIBLE
            //list adapter
            adapter = ListAdapter(log!!)
            binding.payeeListview.adapter = adapter
            binding.payeeListview.layoutManager = LinearLayoutManager(context)
        }
    }

    fun refreshAdapter() {
        adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        getInfo()
        if(adapter != null)
            refreshAdapter()
    }
}