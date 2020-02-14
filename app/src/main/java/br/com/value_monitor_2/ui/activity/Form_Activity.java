package br.com.value_monitor_2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import br.com.value_monitor_2.R;
import br.com.value_monitor_2.database.AgendaDatabase;
import br.com.value_monitor_2.database.dao.RoomValueGroup;
import br.com.value_monitor_2.model.ValueGroup;

import static br.com.value_monitor_2.ui.activity.ConstantsActivity.VALUE_KEY;

public class Form_Activity extends AppCompatActivity {

    private GetField getField = new GetField();
    private ValueGroup valueGroup;
    private RoomValueGroup dao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        AgendaDatabase agendaDatabase = AgendaDatabase.getInstance(this);
        dao = agendaDatabase.getRoomValueGroup();
        startFields();
        loadValueGroup();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_menu_save){
            saveValue();
        }
        return super.onOptionsItemSelected(item);

    }

    private static class GetField {
        EditText edtAnnotation, edtValue, edtDate;
    }

    private void startFields() {

        getField.edtAnnotation = findViewById(R.id.edt_annotation_id);
        getField.edtValue = findViewById(R.id.edt_value_id);
        getField.edtDate = findViewById(R.id.edt_date_id);

    }

    private void loadField() {
        valueGroup.setAnnotation(getField.edtAnnotation.getText().toString());
        valueGroup.setValue(Double.valueOf(getField.edtValue.getText().toString()));
        valueGroup.setDate(getField.edtDate.getText().toString());

    }

    private void loadValueGroup() {
        Intent intent = getIntent();
        if (intent.hasExtra(VALUE_KEY)) {
            setTitle("Editar Valor");
            valueGroup = (ValueGroup) intent.getSerializableExtra(VALUE_KEY);
            loadField();
        } else {
            setTitle("Lança Valor");
            valueGroup = new ValueGroup();
        }
    }

    private void saveValue() {

        loadField();
        if (valueGroup.validId()) {
            dao.update(valueGroup);
        } else {
            dao.save(valueGroup);
        }
        finish();
    }


}
