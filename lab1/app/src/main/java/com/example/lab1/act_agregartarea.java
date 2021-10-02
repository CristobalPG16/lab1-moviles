package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class act_agregartarea extends AppCompatActivity {

    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_agregartarea);


        btn_guardar = findViewById(R.id.btn_guardar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(act_agregartarea.this, act_consultartareas.class);
                startActivity(intent);

                //prueba de que el boton escucha, es un mensaje
                //Toast.makeText(getApplicationContext(),"Bienvenido ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}