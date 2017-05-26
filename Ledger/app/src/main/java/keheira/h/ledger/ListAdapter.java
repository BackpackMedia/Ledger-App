package keheira.h.ledger;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Keheira on 5/26/2017.
 */

public class ListAdapter extends ArrayAdapter<Payee> {
    public ListAdapter(Context context, ArrayList<Payee> payee) {
        super(context, 0, payee);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Payee payee = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ledger_items, parent, false);
        }
        TextView name = (TextView)convertView.findViewById(R.id.name);
        TextView amount = (TextView)convertView.findViewById(R.id.amount);
        name.setText(payee.getName());
        amount.setText(String.valueOf(payee.getAmount()));
        return convertView;
    }
}
