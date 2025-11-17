// Archivo: presentation/viewmodel/SolicitudViewModel.java
package com.example.emergenciapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.emergenciapp.data.model.solicitud;
import com.example.emergenciapp.data.remote.ServicioRepositoryImpl;

public class SolicitudViewModel extends ViewModel {

    private final ServicioRepositoryImpl servicioRepository;
    // LiveData para notificar a la Activity si la solicitud fue exitosa
    private final MutableLiveData<Boolean> _creacionExitosa = new MutableLiveData<>();
    public LiveData<Boolean> creacionExitosa = _creacionExitosa;

    public SolicitudViewModel() {
        this.servicioRepository = new ServicioRepositoryImpl();
    }

    /**
     * Llama al repositorio para guardar la nueva solicitud.
     */
    public void crearNuevaSolicitud(solicitud solicitud) {
        // En un proyecto real, esto se manejaría asíncronamente
        boolean resultado = servicioRepository.guardarSolicitud(solicitud);
        _creacionExitosa.postValue(resultado);
    }
}