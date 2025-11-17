package com.example.emergenciapp.data.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.emergenciapp.R;
import com.example.emergenciapp.data.model.Oficio;
import com.example.emergenciapp.data.model.solicitud; // NUEVO IMPORT
import com.example.emergenciapp.data.model.usuario; // Si no lo tienes, puedes ignorar el error por ahora

import java.util.Arrays;
import java.util.List;

public class ServicioRepositoryImpl {

    // Simula la obtención de la lista de oficios
    public LiveData<List<Oficio>> getListaOficios() {
        MutableLiveData<List<Oficio>> oficiosLiveData = new MutableLiveData<>();

        int placeholderIcon = 0;

        List<Oficio> oficios = Arrays.asList(
                new Oficio(1, "CARPINTERO", placeholderIcon),
                new Oficio(2, "GASISTA", placeholderIcon),
                new Oficio(3, "CERRAJERO", placeholderIcon),
                new Oficio(4, "ALBAÑIL", placeholderIcon),
                new Oficio(5, "PLOMERO", placeholderIcon)
        );

        oficiosLiveData.setValue(oficios);
        return oficiosLiveData;
    }

    /**
     * NUEVO MÉTODO: Simula la acción de guardar una nueva solicitud en el servidor.
     * En producción, aquí se usaría Retrofit (POST)
     */
    public boolean guardarSolicitud(solicitud solicitud) {
        // Imprimimos la información para simular que se envía y se registra
        System.out.println("--- NUEVA SOLICITUD ---");
        System.out.println("Oficio: " + solicitud.getOficioNombre());
        System.out.println("Cliente ID: " + solicitud.getClienteId());
        System.out.println("Descripción: " + solicitud.getDescripcion());
        System.out.println("Ubicación: " + solicitud.getDireccionConfirmada());
        System.out.println("-----------------------");

        // Retorna true para simular que la operación API fue exitosa
        return true;
    }
}