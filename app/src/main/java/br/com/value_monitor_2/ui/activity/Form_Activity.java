package br.com.value_monitor_2.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.value_monitor_2.R;
import br.com.value_monitor_2.database.AgendaDatabase;
import br.com.value_monitor_2.database.dao.RoomValueGroup;
import br.com.value_monitor_2.model.ValueGroup;

import static br.com.value_monitor_2.ui.activity.ConstantsActivity.VALUE_KEY;

public class Form_Activity extends AppCompatActivity {

    private GetField getField = new GetField();
    private ValueGroup valueGroup;
    private RoomValueGroup dao;
    private String txtMonthName;
    private SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
    String todayDate = dt.format(new Date());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        AgendaDatabase agendaDatabase = AgendaDatabase.getInstance(this);
        dao = agendaDatabase.getRoomValueGroup();
        startFields();
        loadValueGroup();

        txtMonthName = getMonthName();

        getField.edtValue.setRawInputType(Configuration.KEYBOARD_12KEY);
        getField.edtValue.addTextChangedListener(new CurrencyTextWatcher(getField.edtValue));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_menu_save) {
            dateMask();
            saveValue();
        }
        return super.onOptionsItemSelected(item);

    }

    private static class GetField {
        EditText edtAnnotation, edtValue, edtDate;
        TextView txtValue;
    }

    private void startFields() {
        getField.edtAnnotation = findViewById(R.id.edt_annotation_id);
        getField.edtValue = findViewById(R.id.edt_value_id);
        getField.edtDate = findViewById(R.id.edt_date_id);
        getField.edtDate.setText(todayDate);
        getField.txtValue = findViewById(R.id.txt_value_form);


    }

    private void loadField() {
        getField.edtAnnotation.setText(valueGroup.getAnnotation());
        getField.edtValue.setText(String.valueOf(valueGroup.getValue()));
        dateMask();
        getField.edtDate.setText(String.valueOf(valueGroup.getDate()));


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

        if (!validation()) {
            return;
        } else {
            setValueGroup();
            if (valueGroup.validId()) {
                dao.update(valueGroup);
            } else {
                dao.save(valueGroup);
            }
            finish();
        }
    }

    private void setValueGroup() {
        valueGroup.setAnnotation(getField.edtAnnotation.getText().toString());
        valueGroup.setValue(getField.txtValue.getText().toString());
        valueGroup.setDate(todayDate);
        valueGroup.setMonth_name(txtMonthName);
    }

    private void dateMask() {
        todayDate.replaceAll("/", "");
    }

    public class CurrencyTextWatcher implements TextWatcher {

        private String current = "";
        private int index;
        private boolean deletingDecimalPoint;
        private final EditText currency;

        public CurrencyTextWatcher(EditText p_currency) {
            currency = p_currency;
        }


        @Override
        public void beforeTextChanged(CharSequence p_s, int p_start, int p_count, int p_after) {

            if (p_after > 0) {
                index = p_s.length() - p_start;
            } else {
                index = p_s.length() - p_start - 1;
            }
            if (p_count > 0 && p_s.charAt(p_start) == ',') {
                deletingDecimalPoint = true;
            } else {
                deletingDecimalPoint = false;
            }

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            try {
                if (!editable.toString().equals(current)) {
                    currency.removeTextChangedListener(this);
                    if (deletingDecimalPoint) {
                        editable.delete(editable.length() - index - 1, editable.length() - index);
                    }
                    // Currency char may be retrieved from  NumberFormat.getCurrencyInstance()
                    String v_text = editable.toString().replace("R$", "").replace(",", "");
                    v_text = v_text.replaceAll("\\s", "");
                    double v_value = 0;
                    if (v_text != null && v_text.length() > 0) {
                        v_value = Double.parseDouble(v_text);
                    }
                    // Currency instance may be retrieved from a static member.
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                    String v_formattedValue = numberFormat.format((v_value / 100));
                    current = v_formattedValue;
                    currency.setText(v_formattedValue);
                    if (index > v_formattedValue.length()) {
                        currency.setSelection(v_formattedValue.length());
                    } else {
                        currency.setSelection(v_formattedValue.length() - index);
                    }
                    // include here anything you may want to do after the formatting is completed.
                    currency.addTextChangedListener(this);
                    getField.txtValue.setText(v_formattedValue);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private String getMonthName() {
        Calendar calendar = Calendar.getInstance();
        String mes = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);

        if (mes.equalsIgnoreCase("january")) {
            return "Janeiro";
        } else if (mes.equalsIgnoreCase("february")) {
            return "Fevereiro";
        } else if (mes.equalsIgnoreCase("march")) {
            return "Março";
        } else if (mes.equalsIgnoreCase("april")) {
            return "Abril";
        } else if (mes.equalsIgnoreCase("may")) {
            return "Maio";
        } else if (mes.equalsIgnoreCase("june")) {
            return "Junho";
        } else if (mes.equalsIgnoreCase("july")) {
            return "Julho";
        } else if (mes.equalsIgnoreCase("august")) {
            return "Agosto";
        } else if (mes.equalsIgnoreCase("september")) {
            return "Setembro";
        } else if (mes.equalsIgnoreCase("october")) {
            return "Outubro";
        } else if (mes.equalsIgnoreCase("november")) {
            return "Novembro";
        }

        return mes;
    }

    private boolean validation() {
        if (getField.edtAnnotation.getText().toString().trim().isEmpty()) {
            getField.edtAnnotation.setError(getString(R.string.campo_obrigatorio));
            return false;
        } else if (getField.edtValue.getText().toString().trim().isEmpty()) {
            getField.edtValue.setError(getString(R.string.campo_obrigatorio));
            return false;
        } else if (getField.edtDate.getText().toString().trim().isEmpty()) {
            getField.edtDate.setError(getString(R.string.campo_obrigatorio));
            return false;
        }
        return true;

    }
}
