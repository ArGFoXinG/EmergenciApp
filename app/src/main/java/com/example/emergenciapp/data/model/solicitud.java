package com.example.emergenciapp.data.model;

public class solicitud {
    private String id;
    private String clienteId;
    private String profesionalId; // Nullable hasta que sea aceptada
    private String oficioNombre; // Ej: "Carpintero"
    private String descripcion;
    private String direccionConfirmada;
    private double latitud;
    private double longitud;
    private String fechaHoraPreferida;
    private long fechaCreacion;
    private String estado; // "PENDIENTE", "ACEPTADA", "COMPLETADA"

    public solicitud(Object o, String clienteIdMock, Object o1, String oficioSeleccionado, String descripcion, String ubicacion, double v, double v1, String fechaHora, long l, String pendiente) {
    }

    // Constructor (Puedes usar un constructor más corto si lo deseas)
    // ...

    // Getters y Setters...
    public String getId() { return id; }
    // ... (Añadir el resto de los Getters)
}