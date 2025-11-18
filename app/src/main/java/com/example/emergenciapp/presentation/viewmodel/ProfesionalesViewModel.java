// Archivo: presentation/viewmodel/ProfesionalesViewModel.java
package com.example.emergenciapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.emergenciapp.data.model.Profesional;
import com.example.emergenciapp.data.remote.ProfesionalRepositoryImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProfesionalesViewModel extends ViewModel {

    private final ProfesionalRepositoryImpl profesionalRepository;
    private final MutableLiveData<List<Profesional>> _profesionales = new MutableLiveData<>();
    public LiveData<List<Profesional>> profesionales = _profesionales;

    private List<Profesional> listaMaestra = new ArrayList<>(); // Para mantener la lista original

    public ProfesionalesViewModel() {
        this.profesionalRepository = new ProfesionalRepositoryImpl();
    }

    /**
     * Carga la lista de profesionales basándose en el oficio y ubicación
     */
    public void cargarProfesionales(String oficio, double lat, double lng) {
        // Obtener profesionales del repositorio
        List<Profesional> resultado = profesionalRepository.buscarProfesionales(oficio, lat, lng);
        listaMaestra = new ArrayList<>(resultado); // Guardar copia
        _profesionales.setValue(resultado);
    }

    /**
     * Filtrar por mejor calificación
     */
    public void filtrarPorCalificacion() {
        List<Profesional> listaOrdenada = new ArrayList<>(listaMaestra);
        Collections.sort(listaOrdenada, new Comparator<Profesional>() {
            @Override
            public int compare(Profesional p1, Profesional p2) {
                return Double.compare(p2.getCalificacionPromedio(), p1.getCalificacionPromedio());
            }
        });
        _profesionales.setValue(listaOrdenada);
    }

    /**
     * Filtrar por más cercano
     */
    public void filtrarPorDistancia() {
        List<Profesional> listaOrdenada = new ArrayList<>(listaMaestra);
        Collections.sort(listaOrdenada, new Comparator<Profesional>() {
            @Override
            public int compare(Profesional p1, Profesional p2) {
                return Double.compare(p1.getDistanciaKm(), p2.getDistanciaKm());
            }
        });
        _profesionales.setValue(listaOrdenada);
    }

    /**
     * Filtrar por precio (simulado por ahora)
     */
    public void filtrarPorPrecio() {
        // Por ahora retorna la lista original
        // En producción, ordenarías por un campo 'precio' del Profesional
        _profesionales.setValue(new ArrayList<>(listaMaestra));
    }
}