// Archivo: presentation/ui/LoginActivity.java
package com.example.emergenciapp.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

// 1. EL IMPORT AHORA USA MINÚSCULAS:
import com.example.emergenciapp.data.model.usuario;
import com.example.emergenciapp.databinding.ActivityLoginBinding;
import com.example.emergenciapp.presentation.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        // 2. LA REFERENCIA EN EL OBSERVER AHORA ES MINÚSCULA:
        viewModel.login(null, null).observe(this, new androidx.lifecycle.Observer<usuario>() {
            @Override
            public void onChanged(usuario usuario) { // CLASE MINÚSCULA

                if (usuario == null) {
                    if (!binding.editUsername.getText().toString().isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                } else {
                    handleSuccessfulLogin(usuario);
                }
            }
        });
    }

    private void attemptLogin() {
        String username = binding.editUsername.getText().toString().trim();
        String password = binding.editPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Debe completar ambos campos", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.login(username, password);
    }

    private void handleSuccessfulLogin(usuario usuario) { // CLASE MINÚSCULA
        Toast.makeText(this, "Bienvenido, " + usuario.getUsername() + ". Rol: " + usuario.getRole(), Toast.LENGTH_LONG).show();

        // Lógica de Navegación implementada:
        Intent intent = new Intent(LoginActivity.this, SeleccionActivity.class);
        startActivity(intent);
        finish();
    }
}