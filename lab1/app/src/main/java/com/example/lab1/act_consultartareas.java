package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab1.Modelo.Tarea;

import java.util.ArrayList;

public class act_consultartareas extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static SQLiteDatabase db;
    private static final String NOMBRE_DB = "MultiSQlite";
    private final String TABLA_TAREA = "tarea";

    Spinner filtro_spinner;
    Button btn_agregar, btn_buscar, btn_actualizar;
    ListView listView;

    ArrayList<Tarea> listaTareas;
    ArrayAdapter<Tarea> adapter;

    Tarea tarea;
    Tarea tareaModificada;
    String filtro;

    int id;

    public static final String tbTarea = "CREATE TABLE IF NOT EXISTS tarea( id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "nombre STRING NOT NULL,"+"objetivo STRING NOT NULL,"+"fechaEntrega STRING NOT NULL,"+"horaEntrega STRING NOT NULL,"+"categoria STRING NOT NULL);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        abrirDataBase();

        setContentView(R.layout.lyt_tareas);

        filtro_spinner = findViewById(R.id.filtro_spinner);
        btn_buscar = findViewById(R.id.btn_buscar);
        btn_agregar = findViewById(R.id.btn_agregar);
        listView = findViewById(R.id.lista);

        tarea = getIntent().getParcelableExtra("tarea");
        tareaModificada = getIntent().getParcelableExtra("tareaModificada");

        //spinner
        ArrayAdapter<CharSequence> adapterspinner = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filtro_spinner.setAdapter(adapterspinner);
        filtro_spinner.setOnItemSelectedListener(this);
        //spinner

        if (tarea != null){
            addTarea(tarea);
        }//Fin if

        if (tareaModificada != null){
            updateTarea(tareaModificada);
        }//Fin

        if (getTarea() != null){
            listaTareas = getTarea();
        }//Fin If

        if (listaTareas != null){
            adapter = new ArrayAdapter<>(act_consultartareas.this, android.R.layout.simple_list_item_1, listaTareas);
            listView.setAdapter(adapter);
        }//Fin if

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act_consultartareas.this, act_agregartarea.class);
                startActivity(intent);
            }//Fin onClick
        });//Fin setOnClickListener

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tarea tarea = listaTareas.get(i);
                id = listaTareas.get(i).getId();
                Intent intent = new Intent(act_consultartareas.this, act_modificartarea.class);
                intent.putExtra("tarea", tarea);
                startActivity(intent);
            }//Fin onItemClick
        });//Fin setOnItemClickListener

    }//Fin onCreate

    public void abrirDataBase(){
        try {
            db = openOrCreateDatabase(NOMBRE_DB, MODE_PRIVATE, null);
            db.execSQL(tbTarea);
        }//Fin try
        catch (SQLiteOutOfMemoryException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error al crear la base de datos", Toast.LENGTH_SHORT).show();
        }//Fin catch
    }//Fin metodo

    private boolean addTarea(Tarea tarea){
        ContentValues content = new ContentValues();
        content.put("nombre", tarea.getNombre());
        content.put("objetivo", tarea.getObjetivo());
        content.put("fechaEntrega", tarea.getFechaEntrega());
        content.put("horaEntrega", tarea.getHoraEntrega());
        content.put("categoria", tarea.getSpinner());
        return db.insert(TABLA_TAREA, null, content)>0;
    }//Fin addTarea

    private ArrayList<Tarea> getTarea(){
        Cursor cursor = db.query(TABLA_TAREA, new String[]{"id", "nombre", "objetivo", "fechaEntrega", "horaEntrega"},
        null, null, null, null, "id desc");
        cursor.moveToFirst();
        ArrayList<Tarea> listaTa = new ArrayList<>();

        while (!cursor.isAfterLast()){
            Tarea tarea = new Tarea();
            tarea.setId(cursor.getInt(0));
            tarea.setNombre(cursor.getString(1));
            tarea.setObjetivo(cursor.getString(2));
            tarea.setFechaEntrega(cursor.getString(3));
            tarea.setHoraEntrega(cursor.getString(4));
            //tarea.setSpinner(cursor.getString(5));
            listaTa.add(tarea);
            cursor.moveToNext();
        }//Fin while

        cursor.close();
        return listaTa;
    }//Fin getTarea

    private boolean updateTarea(Tarea tarea){
        ContentValues content = new ContentValues();
        content.put("nombre", tarea.getNombre());
        content.put("objetivo", tarea.getObjetivo());
        content.put("fechaEntrega", tarea.getFechaEntrega());
        content.put("horaEntrega", tarea.getHoraEntrega());
        //content.put("categoria", tarea.getSpinner());
        return db.update(TABLA_TAREA, content, "id="+tarea.getId(), null)>0;
    }//Fin updateTarea

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        filtro = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}//Fin clase