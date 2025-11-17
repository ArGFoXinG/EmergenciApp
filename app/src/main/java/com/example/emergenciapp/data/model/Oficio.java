// Archivo: data/model/Oficio.java
package com.example.emergenciapp.data.model;

public class Oficio {
    private int id;
    private String nombre; // Ej: "CARPINTERO", "PLOMERO"
    private int iconResId; // ID del recurso de icono

    public Oficio(int id, String nombre, int iconResId) {
        this.id = id;
        this.nombre = nombre;
        this.iconResId = iconResId;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getIconResId() { return iconResId; }
}