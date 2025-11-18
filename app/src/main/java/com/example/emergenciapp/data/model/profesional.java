// Archivo: data/model/Profesional.java
package com.example.emergenciapp.data.model;

public class profesional { // ¡Clase con mayúscula!
    private String id;
    private String nombre;
    private String oficio;
    private double calificacionPromedio;
    private int cantidadOpiniones;
    private double distanciaKm;
    private int tiempoEstimadoMin;
    private String perfilImageUrl;

    // Constructor con mayúscula
    public profesional(String id, String nombre, String oficio, double calificacionPromedio, int cantidadOpiniones, double distanciaKm, int tiempoEstimadoMin, String perfilImageUrl) {
        this.id = id;
        this.nombre = nombre;
        this.oficio = oficio;
        this.calificacionPromedio = calificacionPromedio;
        this.cantidadOpiniones = cantidadOpiniones;
        this.distanciaKm = distanciaKm;
        this.tiempoEstimadoMin = tiempoEstimadoMin;
        this.perfilImageUrl = perfilImageUrl;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getOficio() { return oficio; }
    public double getCalificacionPromedio() { return calificacionPromedio; }
    public int getCantidadOpiniones() { return cantidadOpiniones; }
    public double getDistanciaKm() { return distanciaKm; }
    public int getTiempoEstimadoMin() { return tiempoEstimadoMin; }
    public String getPerfilImageUrl() { return perfilImageUrl; }
}