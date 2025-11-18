// Archivo: data/model/Pago.java
package com.example.emergenciapp.data.model;

public class Pago {
    private String solicitudId;
    private String metodoPago;
    private double costoBase;
    private double costoVisita;
    private double ivaPorcentaje;
    private double ivaMonto;
    private double totalEstimado;
    private String estadoPago;

    // Constructor completo
    public Pago(String solicitudId, String metodoPago, double costoBase,
                double costoVisita, double ivaPorcentaje, double ivaMonto,
                double totalEstimado, String estadoPago) {
        this.solicitudId = solicitudId;
        this.metodoPago = metodoPago;
        this.costoBase = costoBase;
        this.costoVisita = costoVisita;
        this.ivaPorcentaje = ivaPorcentaje;
        this.ivaMonto = ivaMonto;
        this.totalEstimado = totalEstimado;
        this.estadoPago = estadoPago;
    }

    // Constructor con cálculo automático
    public Pago(String solicitudId, String metodoPago, double costoBase, double costoVisita) {
        this.solicitudId = solicitudId;
        this.metodoPago = metodoPago;
        this.costoBase = costoBase;
        this.costoVisita = costoVisita;
        this.ivaPorcentaje = 0.21; // 21% IVA
        this.ivaMonto = (costoBase + costoVisita) * ivaPorcentaje;
        this.totalEstimado = costoBase + costoVisita + ivaMonto;
        this.estadoPago = "PENDIENTE";
    }

    // Getters
    public String getSolicitudId() { return solicitudId; }
    public String getMetodoPago() { return metodoPago; }
    public double getCostoBase() { return costoBase; }
    public double getCostoVisita() { return costoVisita; }
    public double getIvaPorcentaje() { return ivaPorcentaje; }
    public double getIvaMonto() { return ivaMonto; }
    public double getTotalEstimado() { return totalEstimado; }
    public String getEstadoPago() { return estadoPago; }

    // Setters
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }
}