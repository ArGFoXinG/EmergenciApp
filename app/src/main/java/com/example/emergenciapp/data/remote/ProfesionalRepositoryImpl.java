// Archivo: data/remote/ProfesionalRepositoryImpl.java
package com.example.emergenciapp.data.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.emergenciapp.data.model.profesional; // ¡Debe ser con P mayúscula!

import java.util.Arrays;
import java.util.List;

public class ProfesionalRepositoryImpl {

    /**
     * Simula la llamada a la API para obtener profesionales cercanos.
     * Esto usa los datos de la ubicación (lat, lng) que se pasaron.
     */
    public LiveData<List<profesional>> getProfesionalesCercanos(String oficio, double lat, double lng) {
        MutableLiveData<List<profesional>> data = new MutableLiveData<>();

        // --- SIMULACIÓN de Datos para llenar el RecyclerView (Card Views) ---
        List<profesional> mockList = Arrays.asList(
                new profesional("p1", "Juan Perez", oficio, 4.9, 35, 2.5, 15, null),
                new profesional("p2", "Carlos Garcia", oficio, 4.7, 201, 3.1, 20, null),
                new profesional("p3", "Mario Lopez", oficio, 4.5, 18, 2.1, 12, null),
                new profesional("p4", "Marisa Rodriguez", oficio, 4.9, 8, 4.0, 25, null)
        );
        // --------------------------------------------------------

        data.setValue(mockList);
        return data;
    }
}