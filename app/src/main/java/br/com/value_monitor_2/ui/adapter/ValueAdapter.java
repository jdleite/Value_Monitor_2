package br.com.value_monitor_2.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.value_monitor_2.R;
import br.com.value_monitor_2.model.ValueGroup;
import br.com.value_monitor_2.ui.listener.ValueListener;
import br.com.value_monitor_2.ui.viewHolder.ValueViewHolder;

public class ValueAdapter extends RecyclerView.Adapter<ValueViewHolder> {
    private List<ValueGroup> valueGroupList;
    private ValueListener valueListener;

    public ValueAdapter(List<ValueGroup> valueGroupList, ValueListener valueListener) {
        this.valueGroupList = valueGroupList;
        this.valueListener = valueListener;
    }

    @NonNull
    @Override
    public ValueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View valueView = inflater.inflate(R.layout.activity_value_row, parent, false);
        return new ValueViewHolder(valueView,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ValueViewHolder holder, int position) {
        ValueGroup valueGroup = valueGroupList.get(position);
        holder.bindData(valueGroup,valueListener);
    }

    @Override
    public int getItemCount() {
        return valueGroupList.size();
    }

    public void load(List<ValueGroup> valueGroups){
        this.valueGroupList.clear();
        this.valueGroupList.addAll(valueGroups);
        notifyDataSetChanged();
    }
}
