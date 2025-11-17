// Archivo: data/remote/AuthRepositoryImpl.java
package com.example.emergenciapp.data.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.emergenciapp.data.model.usuario;
import com.example.emergenciapp.domain.repository.AuthRepository;

// Implementación del Repositorio de Autenticación
public class AuthRepositoryImpl implements AuthRepository {

    // LiveData que contendrá el resultado del login para notificar a la Activity
    private final MutableLiveData<usuario> loginResult = new MutableLiveData<>();

    @Override
    public LiveData<usuario> login(String username, String password) {
        // En una aplicación real, aquí llamarías a la API con Retrofit.

        // --- SIMULACIÓN (Mockeada para la entrega H1) ---
        if ("cliente1".equals(username) && "1234".equals(password)) {
            // Usuario de prueba exitoso (Cliente)
            usuario user = new usuario("c1", "cliente1", password, "Cliente", "cliente@mail.com");
            loginResult.postValue(user);
        } else if ("pro1".equals(username) && "1234".equals(password)) {
            // Usuario de prueba exitoso (Profesional)
            usuario user = new usuario("p1", "pro1", password, "Profesional", "pro@mail.com");
            loginResult.postValue(user);
        } else {
            // Falla la autenticación
            loginResult.postValue(null);
        }
        // ------------------------------------------------

        return loginResult;
    }

    // Esqueleto del método requerido por la interfaz
    @Override
    public LiveData<Boolean> getAuthStatus() {
        return new MutableLiveData<>(false);
    }
}