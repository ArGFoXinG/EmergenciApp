// Archivo: presentation/viewmodel/SeleccionViewModel.java
package com.example.emergenciapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.emergenciapp.data.model.Oficio;
import com.example.emergenciapp.data.remote.ServicioRepositoryImpl;

import java.util.List;

public class SeleccionViewModel extends ViewModel {

    private final ServicioRepositoryImpl servicioRepository;

    public SeleccionViewModel() {
        // Inicializa el repositorio para usar sus métodos
        this.servicioRepository = new ServicioRepositoryImpl();
    }

    /**
     * Devuelve la lista de oficios para ser observada por la Activity.
     */
    public LiveData<List<Oficio>> getOficios() {
        // Llama al repositorio para obtener los datos
        return servicioRepository.getListaOficios();
    }
}