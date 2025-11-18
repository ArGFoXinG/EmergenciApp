// Archivo: presentation/viewmodel/PagoViewModel.java
package com.example.emergenciapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.emergenciapp.data.model.Pago;
import com.example.emergenciapp.data.remote.PagoRepositoryImpl;

public class PagoViewModel extends ViewModel {

    private final PagoRepositoryImpl pagoRepository;
    private final MutableLiveData<Boolean> _pagoExitoso = new MutableLiveData<>();
    public LiveData<Boolean> pagoExitoso = _pagoExitoso;

    private final MutableLiveData<Pago> _resumenPago = new MutableLiveData<>();
    public LiveData<Pago> resumenPago = _resumenPago;

    public PagoViewModel() {
        this.pagoRepository = new PagoRepositoryImpl();
    }

    /**
     * Calcula el resumen de pago basándose en el servicio
     */
    public void calcularResumen(String solicitudId, double costoBase) {
        Pago pago = new Pago(solicitudId, "Tarjeta de Crédito", costoBase, 0.0);
        _resumenPago.setValue(pago);
    }

    /**
     * Procesa el pago del servicio
     */
    public void procesarPago(Pago pago) {
        boolean resultado = pagoRepository.procesarPago(pago);
        _pagoExitoso.postValue(resultado);
    }
}