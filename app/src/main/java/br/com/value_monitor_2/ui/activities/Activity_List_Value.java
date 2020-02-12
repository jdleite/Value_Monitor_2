package br.com.value_monitor_2.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.value_monitor_2.R;
import br.com.value_monitor_2.database.AgendaDatabase;
import br.com.value_monitor_2.database.dao.RoomValueGroup;
import br.com.value_monitor_2.model.ValueGroup;
import br.com.value_monitor_2.ui.adapter.ValueAdapter;
import br.com.value_monitor_2.ui.listener.ValueListener;

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




        recyclerView = findViewById(R.id.id_recyclerview);




        getActionButton();





    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    public void getActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_List_Value.this, Form_Activity.class));
            }
        });
    }

    public void getList(){
        valueGroupList.addAll(dao.findAll());
        adapter = new ValueAdapter(valueGroupList,valueListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
