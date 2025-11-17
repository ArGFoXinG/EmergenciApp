package com.example.emergenciapp.domain.repository;

import androidx.lifecycle.LiveData;
import com.example.emergenciapp.data.model.usuario;

// Interfaz para definir las operaciones de autenticación
public interface AuthRepository {

    /**
     * Intenta iniciar sesión y devuelve un LiveData con el objeto Usuario (o null si falla).
     */
    LiveData<usuario> login(String username, String password);

    // Método para obtener el estado de autenticación (solo esqueleto)
    LiveData<Boolean> getAuthStatus();
}