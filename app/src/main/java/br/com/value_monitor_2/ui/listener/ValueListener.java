package br.com.value_monitor_2.ui.listener;

import br.com.value_monitor_2.model.ValueGroup;

public interface ValueListener {
    void onListClick(ValueGroup valueGroup);

    void onDeleteClick(ValueGroup valueGroup);

}
