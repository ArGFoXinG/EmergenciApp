package com.example.emergenciapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.emergenciapp.data.model.usuario;
import com.example.emergenciapp.data.remote.AuthRepositoryImpl;
import com.example.emergenciapp.domain.repository.AuthRepository;

public class LoginViewModel extends ViewModel {

    private final AuthRepository authRepository;

    // Constructor: Inicializa el Repositorio
    public LoginViewModel() {
        // En un proyecto grande se usaría inyección de dependencias (Dagger/Hilt)
        // Por ahora, instanciamos directamente la implementación:
        this.authRepository = new AuthRepositoryImpl();
    }

    /**
     * Llama al Repositorio para iniciar sesión.
     */
    public LiveData<usuario> login(String username, String password) {
        return authRepository.login(username, password);
    }
}