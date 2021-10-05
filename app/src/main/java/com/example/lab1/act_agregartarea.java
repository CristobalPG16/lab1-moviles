package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.Modelo.Tarea;

public class act_agregartarea extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText txtNombre, txtObjetivo, txtFechaEntrega, txtHoraEntrega;
    Button btn_guardar;

    Spinner spinnerCategoria;

    Tarea tarea;
    String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_agregartarea);

        txtNombre = findViewById(R.id.txtNombre);
        txtObjetivo = findViewById(R.id.txtObjetivo);
        txtFechaEntrega = findViewById(R.id.txtFechaEntrega);
        txtHoraEntrega = findViewById(R.id.txtHoraEntrega);

        btn_guardar = findViewById(R.id.btn_guardar);

        //spiner
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> adapterspinner = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterspinner);
        spinnerCategoria.setOnItemSelectedListener(this);
        //spiner

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNombre.getText().toString().isEmpty() || txtObjetivo.getText().toString().isEmpty() || txtFechaEntrega.getText().toString().isEmpty() || txtHoraEntrega.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }//Fin if
                else {
                    tarea = new Tarea(0, txtNombre.getText().toString(), txtObjetivo.getText().toString(), txtFechaEntrega.getText().toString(), txtHoraEntrega.getText().toString(),categoria);
                    limpiar();
                    Toast.makeText(getApplicationContext(), "Agregado correctamente", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(act_agregartarea.this, act_consultartareas.class);
                    intent.putExtra("tarea", tarea);
                    startActivity(intent);
                }//Fin else
            }//Fin onClick
        });//Fin setOnClickListener
    }//Fin onCreate

    public void limpiar(){
        txtNombre.setText("");
        txtObjetivo.setText("");
        txtFechaEntrega.setText("");
        txtHoraEntrega.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        categoria = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}//Fin clase