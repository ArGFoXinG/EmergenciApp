// Archivo: presentation/viewmodel/PagoViewModel.java
package com.example.emergenciapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.emergenciapp.data.model.Pago;

public class PagoViewModel extends ViewModel {

    private final MutableLiveData<Pago> _resumenPago = new MutableLiveData<>();
    public LiveData<Pago> resumenPago = _resumenPago;

    private final MutableLiveData<Boolean> _pagoConfirmado = new MutableLiveData<>();
    public LiveData<Boolean> pagoConfirmado = _pagoConfirmado;

    /**
     * Realiza el cálculo del total basado en el costo base.
     */
    public void calcularResumen(double costoBase, String solicitudId) {
        double ivaPorcentaje = 0.21;
        double ivaMonto = costoBase * ivaPorcentaje;
        double total = costoBase + ivaMonto;

        // Crea el objeto Pago con la información calculada
        Pago pago = new Pago(
                solicitudId,
                "Tarjeta", // Método por defecto
                costoBase,
                ivaPorcentaje,
                ivaMonto,
                total,
                "PENDIENTE"
        );
        _resumenPago.setValue(pago);
    }

    /**
     * Simula la confirmación del pago.
     */
    public void confirmarPago(String metodo) {
        // En una app real: Aquí llamarías a la pasarela de pagos (Mercado Pago/Stripe).

        // --- SIMULACIÓN ---
        if ("Tarjeta".equals(metodo)) {
            // Simulación exitosa
            _pagoConfirmado.postValue(true);
        } else {
            // Simulación pago en efectivo (se considera "confirmado" el servicio)
            _pagoConfirmado.postValue(true);
        }
    }
}