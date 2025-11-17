// Archivo: data/model/Usuario.java
package com.example.emergenciapp.data.model;

public class usuario {
    private String id;
    private String username;
    private String password;
    private String role; // "Cliente" o "Profesional"
    private String email;

    // Constructor completo
    public usuario(String id, String username, String password, String role, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    // Constructor para Login (solo nombre y password)
    public usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters (para acceder a la información)
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getEmail() { return email; }

    // Setters (si necesitas modificar el objeto)
    public void setId(String id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
}