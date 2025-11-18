// Archivo: data/model/Pago.java
package com.example.emergenciapp.data.model;

public class Pago {
    private String solicitudId;
    private String metodo; // "Tarjeta" o "Efectivo"
    private double costoBase;
    private double ivaPorcentaje;
    private double ivaMonto;
    private double totalEstimado;
    private String estadoPago; // "PENDIENTE", "PAGADO"

    // Constructor completo para la creación
    public Pago(String solicitudId, String metodo, double costoBase, double ivaPorcentaje, double ivaMonto, double totalEstimado, String estadoPago) {
        this.solicitudId = solicitudId;
        this.metodo = metodo;
        this.costoBase = costoBase;
        this.ivaPorcentaje = ivaPorcentaje;
        this.ivaMonto = ivaMonto;
        this.totalEstimado = totalEstimado;
        this.estadoPago = estadoPago;
    }

    // Getters y Setters
    public double getCostoBase() { return costoBase; }
    public double getTotalEstimado() { return totalEstimado; }
    public String getMetodo() { return metodo; }
    // ... (añade el resto de Getters si los necesitas)
}