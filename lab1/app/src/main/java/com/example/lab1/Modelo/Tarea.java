package com.example.lab1.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Tarea implements Parcelable {

    private int id;
    private String nombre,objetivo, fechaEntrega, horaEntrega;

    public Tarea(int id, String nombre, String objetivo, String fechaEntrega, String horaEntrega) {
        this.id = id;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
    }

    public Tarea() {
        this.id = 0;
        this.nombre = "";
        this.objetivo = "";
        this.fechaEntrega = "";
        this.horaEntrega = "";
    }

    protected Tarea(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        objetivo = in.readString();
        fechaEntrega = in.readString();
        horaEntrega = in.readString();
    }

    public static final Creator<Tarea> CREATOR = new Creator<Tarea>() {
        @Override
        public Tarea createFromParcel(Parcel in) {
            return new Tarea(in);
        }

        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    @Override
    public String toString() {
        return "ID: "+ id +
                " - Nombre: " + nombre +
                " - Objetivo: " + objetivo +
                " - Fecha de Entrega: " + fechaEntrega +
                " - Hora de Entrega: " + horaEntrega;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(objetivo);
        parcel.writeString(fechaEntrega);
        parcel.writeString(horaEntrega);
    }
}//Fin clase
