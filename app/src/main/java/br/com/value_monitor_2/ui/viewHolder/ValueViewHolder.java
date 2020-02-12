package br.com.value_monitor_2.ui.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.value_monitor_2.R;
import br.com.value_monitor_2.model.ValueGroup;
import br.com.value_monitor_2.ui.listener.ValueListener;

public class ValueViewHolder extends RecyclerView.ViewHolder {

    private TextView edtAnnotation, edtValue, edtDate;
    private Context context;

    public ValueViewHolder(@NonNull View itemView,Context context) {
        super(itemView);

        edtAnnotation = itemView.findViewById(R.id.id_txt_name);
        edtValue = itemView.findViewById(R.id.id_txt_value);
        edtDate = itemView.findViewById(R.id.id_txt_date);
        this.context = context;
    }

    public void bindData(ValueGroup valueGroup, ValueListener valueListener){

        edtAnnotation.setText(valueGroup.getAnnotation());
        edtValue.setText(String.valueOf(valueGroup.getValue()));
        edtDate.setText(valueGroup.getDate());



    }


}
