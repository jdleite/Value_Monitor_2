package br.com.value_monitor_2.ui.viewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.value_monitor_2.R;
import br.com.value_monitor_2.model.ValueGroup;
import br.com.value_monitor_2.ui.listener.ValueListener;

public class ValueViewHolder extends RecyclerView.ViewHolder {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private TextView txtAnnotation, txtValue, txtDate;
    CardView cardView;
    private ImageView imgClose;
    private Context context;

    public ValueViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        txtAnnotation = itemView.findViewById(R.id.id_txt_name);
        txtValue = itemView.findViewById(R.id.id_txt_value);
        txtDate = itemView.findViewById(R.id.id_txt_date);
        imgClose = itemView.findViewById(R.id.id_img_close);
        cardView = itemView.findViewById(R.id.id_cardview);
        this.context = context;
    }

    public void bindData(final ValueGroup valueGroup, final ValueListener valueListener) {

        txtAnnotation.setText(valueGroup.getAnnotation());
        txtValue.setText(valueGroup.getValue());
        txtDate.setText(valueGroup.getDate());


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueListener.onListClick(valueGroup);
            }
        });


        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle(R.string.remocao_valor)
                        .setMessage(R.string.deseja_remover)
                        .setIcon(R.drawable.close)
                        .setPositiveButton(context.getString(R.string.sim), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                valueListener.onDeleteClick(valueGroup);

                            }
                        })
                        .setNeutralButton(context.getString(R.string.nao),null)
                        .show();
            }
        });


    }




}
