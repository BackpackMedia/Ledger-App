package keheira.h.ledger

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import keheira.h.ledger.databinding.NewPayeeBinding

/**
 * Created by Keheira on 5/28/2017.
 */

class AddPayee : Fragment() {
    private lateinit var binding: NewPayeeBinding
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NewPayeeBinding.inflate(
                inflater,
                container,
                false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.savePayeeBtn.setOnClickListener {
            addPerson()
        }
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

    private fun addPerson() {
        name = binding.nameTxt.text.toString()
        number = binding.numTxt.text.toString()
        amount = java.lang.Double.parseDouble(binding.amountTxt.text.toString())
        reason = binding.reasonTxt.text.toString()
        if (checkFields()) {
           //show dialog
        } else {
            val db = DatabaseHandler.getInstance(context)
            val person = Payee(name, number, amount!!, reason)
            db.addPerson(person)
            findNavController().navigate(R.id.save_payee)
        }
    }

    private fun checkFields(): Boolean {
        return name == null || number == null ||
                amount == null || reason == null
    }
}
