package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class act_consultartareas extends AppCompatActivity {

    Button btn_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_tareas);


        btn_agregar = findViewById(R.id.btn_agregar);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(act_consultartareas.this, act_agregartarea.class);
                startActivity(intent);

                //prueba de que el boton escucha, es un mensaje
                //Toast.makeText(getApplicationContext(),"Bienvenido ", Toast.LENGTH_SHORT).show();
            }
        });



    }
}