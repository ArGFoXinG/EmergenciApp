// Archivo: data/remote/PagoRepositoryImpl.java
package com.example.emergenciapp.data.remote;

import com.example.emergenciapp.data.model.Pago;

public class PagoRepositoryImpl {

    /**
     * Simula el procesamiento de un pago
     * En producción, esto haría una petición POST a la API de pagos
     */
    public boolean procesarPago(Pago pago) {
        // --- SIMULACIÓN (Mock) ---
        System.out.println("=== PROCESANDO PAGO ===");
        System.out.println("Solicitud ID: " + pago.getSolicitudId());
        System.out.println("Método de Pago: " + pago.getMetodoPago());
        System.out.println("Costo Base: $" + pago.getCostoBase());
        System.out.println("Costo Visita: $" + pago.getCostoVisita());
        System.out.println("IVA: $" + pago.getIvaMonto());
        System.out.println("Total: $" + pago.getTotalEstimado());
        System.out.println("=======================");

        // Simulamos que el pago siempre es exitoso
        try {
            // Simulamos un delay de red
            Thread.sleep(1500);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene el costo base de un servicio según el oficio
     */
    public double obtenerCostoBase(String oficio) {
        // Costos base simulados por oficio
        switch (oficio.toUpperCase()) {
            case "CARPINTERO":
                return 45000.0;
            case "PLOMERO":
                return 35000.0;
            case "ELECTRICISTA":
                return 40000.0;
            case "GASISTA":
                return 50000.0;
            case "ALBAÑIL":
                return 55000.0;
            case "CERRAJERO":
                return 30000.0;
            default:
                return 40000.0;
        }
    }
}