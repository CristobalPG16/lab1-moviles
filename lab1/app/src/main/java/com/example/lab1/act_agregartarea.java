package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.Modelo.Tarea;

public class act_agregartarea extends AppCompatActivity {

    TextView txtNombre, txtObjetivo, txtFechaEntrega, txtHoraEntrega;
    Button btn_guardar;

    Spinner spinnerCategoria;

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
                    tarea = new Tarea(0, txtNombre.getText().toString(), txtObjetivo.getText().toString(), txtFechaEntrega.getText().toString(), txtHoraEntrega.getText().toString());
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

}//Fin clase