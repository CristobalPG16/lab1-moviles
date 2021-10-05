package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab1.Modelo.Tarea;

public class act_modificartarea extends AppCompatActivity {

    EditText txtModificarNombre, txtModificarObjetivo, txtModificarFecha, txtModificarHora;
    Spinner spinner_modificarCategoria;
    Button btnModificar;

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
                    tareaModificada = new Tarea(tarea.getId(), txtModificarNombre.getText().toString(), txtModificarObjetivo.getText().toString(),txtModificarFecha.getText().toString(),txtModificarHora.getText().toString());
                    Toast.makeText(getApplicationContext(), "Modificado correctamente", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(act_modificartarea.this, act_consultartareas.class);
                    intent.putExtra("tareaModificada", tareaModificada);
                    startActivity(intent);
                }//Fin else
            }//Fin onClick
        });//Fin setOnClickListener

    }//Fin onCreate
}//Fin clase