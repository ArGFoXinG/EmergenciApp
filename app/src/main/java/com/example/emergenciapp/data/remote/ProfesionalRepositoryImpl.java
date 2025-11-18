// Archivo: data/remote/ProfesionalRepositoryImpl.java
package com.example.emergenciapp.data.remote;

import com.example.emergenciapp.data.model.Profesional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfesionalRepositoryImpl {

    /**
     * Simula la búsqueda de profesionales desde una API
     * En producción, esto usaría Retrofit para hacer una petición GET
     */
    public List<Profesional> buscarProfesionales(String oficio, double lat, double lng) {

        // --- DATOS MOCKEADOS (Simulación) ---
        List<Profesional> profesionalesMock = new ArrayList<>();

        // Profesional 1
        profesionalesMock.add(new Profesional(
                "p1",
                "Juan Perez",
                oficio,
                4.9,
                35,
                2.5,
                15,
                null // URL de imagen (por ahora null)
        ));

        // Profesional 2
        profesionalesMock.add(new Profesional(
                "p2",
                "Maria Rodriguez",
                oficio,
                4.7,
                28,
                3.2,
                20,
                null
        ));

        // Profesional 3
        profesionalesMock.add(new Profesional(
                "p3",
                "Carlos Gomez",
                oficio,
                4.8,
                42,
                1.8,
                12,
                null
        ));

        // Profesional 4
        profesionalesMock.add(new Profesional(
                "p4",
                "Ana Martinez",
                oficio,
                4.6,
                19,
                4.1,
                25,
                null
        ));

        // Profesional 5
        profesionalesMock.add(new Profesional(
                "p5",
                "Luis Fernandez",
                oficio,
                4.9,
                51,
                2.9,
                18,
                null
        ));

        // En producción, aquí filtrarías por lat/lng y oficio
        System.out.println("Buscando profesionales de: " + oficio);
        System.out.println("Cerca de: Lat=" + lat + ", Lng=" + lng);

        return profesionalesMock;
    }
}