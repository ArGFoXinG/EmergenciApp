package com.example.emergenciapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.emergenciapp.data.model.profesional; // ¡Debe ser con P mayúscula!
import com.example.emergenciapp.data.remote.ProfesionalRepositoryImpl;

import java.util.List;

public class ProfesionalesViewModel extends ViewModel {

    private final ProfesionalRepositoryImpl repository;
    private final MutableLiveData<List<profesional>> _profesionales = new MutableLiveData<>();
    public LiveData<List<profesional>> profesionales = _profesionales;

    public ProfesionalesViewModel() {
        this.repository = new ProfesionalRepositoryImpl();
    }

    /**
     * Llama al repositorio para cargar la lista de profesionales, usando el oficio y la ubicación.
     */
    public void loadProfesionales(String oficio, double lat, double lng) {
        repository.getProfesionalesCercanos(oficio, lat, lng).observeForever(profesionalesList -> {
            // El resultado del repositorio se publica en el LiveData de este ViewModel
            _profesionales.setValue(profesionalesList);
        });
    }

    /**
     * Lógica para ordenar la lista (Ej. por mejor calificación).
     */
    public void orderByCalificacion() {
        // Aquí iría la lógica para reordenar la lista que ya está en _profesionales
        // Simplemente llamamos a la función para cumplir con la arquitectura.
    }
}