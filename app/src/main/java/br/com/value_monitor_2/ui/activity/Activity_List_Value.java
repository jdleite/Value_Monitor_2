package br.com.value_monitor_2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import br.com.value_monitor_2.R;
import br.com.value_monitor_2.database.AgendaDatabase;
import br.com.value_monitor_2.database.dao.RoomValueGroup;
import br.com.value_monitor_2.model.ValueGroup;
import br.com.value_monitor_2.ui.adapter.ValueAdapter;
import br.com.value_monitor_2.ui.listener.ValueListener;

import static br.com.value_monitor_2.ui.activity.ConstantsActivity.VALUE_KEY;

public class Activity_List_Value extends AppCompatActivity {

    private RoomValueGroup dao;
    private RecyclerView recyclerView;
    private List<ValueGroup> valueGroupList = new ArrayList<>();
    private ValueAdapter adapter;
    private ValueListener valueListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Valores Lançados");
        setContentView(R.layout.activity_list_value);

        AgendaDatabase agendaDatabase = AgendaDatabase.getInstance(this);
        dao = agendaDatabase.getRoomValueGroup();
        recyclerView = findViewById(R.id.id_recyclerview);
        getActionButton();


        valueListener = new ValueListener() {
            @Override
            public void onListClick(ValueGroup valueGroup) {

                openFormUpdateValue(valueGroup);

            }

            @Override
            public void onDeleteClick(ValueGroup valueGroup) {
                adapter.removeListValue(valueGroup);
                dao.remove(valueGroup);


            }
        };


    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    private void getActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Activity_List_Value.this, Form_Activity.class));
            }
        });
    }

    private void getList() {
        adapter = new ValueAdapter(valueGroupList, valueListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.load(dao.findAll());
    }

    private void openFormUpdateValue(ValueGroup valueGroup) {
        Intent intent = new Intent(Activity_List_Value.this, Form_Activity.class);
        intent.putExtra(VALUE_KEY, valueGroup);
        startActivity(intent);


    }


}
