package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.Modelo.Tarea;

public class act_agregartarea extends AppCompatActivity {

    TextView txtNombre, txtObjetivo, txtFechaEntrega, txtHoraEntrega;
    Spinner spinnerCategoria;
    Button btn_guardar;

    Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_agregartarea);

        txtNombre = findViewById(R.id.txtNombre);
        txtObjetivo = findViewById(R.id.txtObjetivo);
        txtFechaEntrega = findViewById(R.id.txtFechaEntrega);
        txtHoraEntrega = findViewById(R.id.txtHoraEntrega);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);

        btn_guardar = findViewById(R.id.btn_guardar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtNombre.getText().toString().isEmpty() || txtObjetivo.getText().toString().isEmpty() || txtFechaEntrega.getText().toString().isEmpty() || txtHoraEntrega.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }//Fin if
                else {
                    String nombre = txtNombre.getText().toString();
                    String objetivo = txtObjetivo.getText().toString();
                    String fechaEntrega = txtFechaEntrega.getText().toString();
                    String horaEntrga = txtHoraEntrega.getText().toString();

                    tarea = new Tarea(0, nombre, objetivo, fechaEntrega, horaEntrga);

                    Intent intent = new Intent(act_agregartarea.this, act_consultartareas.class);
                    intent.putExtra("tarea", tarea);
                    startActivity(intent);
                }//Fin else
            }//Fin onClick
        });//Fin setOnClickListener
    }//Fin onCreate
}//Fin clase