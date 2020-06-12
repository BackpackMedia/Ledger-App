package keheira.h.ledger

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DeleteDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Error")
                    .setMessage("Make sure all fields are filled in")
                    .setPositiveButton("OK",
                            DialogInterface.OnClickListener{ dialog, which ->
                                dialog.cancel()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}