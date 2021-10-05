package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab1.Modelo.Tarea;

public class act_modificartarea extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText txtModificarNombre, txtModificarObjetivo, txtModificarFecha, txtModificarHora;
    Spinner spinner_modificarCategoria;
    Button btnModificar;
    String nuevaCategoria;

    Tarea tarea;
    Tarea tareaModificada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_modificartarea);

        txtModificarNombre = findViewById(R.id.txtModificarNombre);
        txtModificarObjetivo = findViewById(R.id.txtModificarObjetivo);
        txtModificarFecha = findViewById(R.id.txtModificarFecha);
        txtModificarHora = findViewById(R.id.txtModificarHora);
        spinner_modificarCategoria = findViewById(R.id.spinner_modificarCategoria);
        btnModificar = findViewById(R.id.btnModificar);

        tarea = getIntent().getParcelableExtra("tarea");

        //spinner
        ArrayAdapter<CharSequence> adapterspinner = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_modificarCategoria.setAdapter(adapterspinner);
        spinner_modificarCategoria.setOnItemSelectedListener(this);
        //spinner

        if (tarea != null){
            txtModificarNombre.setText(tarea.getNombre());
            txtModificarObjetivo.setText(tarea.getObjetivo());
            txtModificarFecha.setText(tarea.getFechaEntrega());
            txtModificarHora.setText(tarea.getHoraEntrega());
        }//Fin if

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtModificarNombre.getText().toString().isEmpty() || txtModificarObjetivo.getText().toString().isEmpty() || txtModificarFecha.getText().toString().isEmpty() || txtModificarHora.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }//Fin if
                else {
                    tareaModificada = new Tarea(tarea.getId(), txtModificarNombre.getText().toString(), txtModificarObjetivo.getText().toString(),txtModificarFecha.getText().toString(),txtModificarHora.getText().toString(),nuevaCategoria);
                    Toast.makeText(getApplicationContext(), "Modificado correctamente", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(act_modificartarea.this, act_consultartareas.class);
                    intent.putExtra("tareaModificada", tareaModificada);
                    startActivity(intent);
                }//Fin else
            }//Fin onClick
        });//Fin setOnClickListener

    }//Fin onCreate

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        nuevaCategoria = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}//Fin clase