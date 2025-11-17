// Archivo: presentation/ui/LoginActivity.java
package com.example.emergenciapp.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergenciapp.data.model.usuario;
import com.example.emergenciapp.databinding.ActivityLoginBinding;
import com.example.emergenciapp.presentation.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    // View Binding: Clase generada automáticamente para acceder a las vistas
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Inicializar View Binding (Reemplaza findViewById)
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 2. Inicializar ViewModel
        // Usamos this.getLifecycleOwner() si usáramos Fragmentos
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // 3. Manejo del Click en el Botón Entrar
        binding.btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        // 4. Observar el Resultado del Login
        // Cuando el Repositorio (vía ViewModel) termina la simulación de login,
        // este observador se activa.
        viewModel.login(null, null).observe(this, new androidx.lifecycle.Observer<usuario>() {
            @Override
            public void onChanged(usuario usuario) {
                // NOTA: Para evitar que se ejecute la primera vez con null,
                // hacemos la verificación solo después de intentar el login.

                // Si el usuario es nulo, significa que el login falló
                if (usuario == null) {
                    // Solo mostramos el error si ya se intentó un login
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

        // Llamar al método login del ViewModel con las credenciales
        viewModel.login(username, password);
    }

    private void handleSuccessfulLogin(usuario usuario) {
        Toast.makeText(this, "Bienvenido, " + usuario.getUsername() + ". Rol: " + usuario.getRole(), Toast.LENGTH_LONG).show();

        // TODO: Aquí debes decidir a dónde navegar según el rol (Cliente o Profesional)
        // Por ahora, solo cerramos la actividad.
        // startActivity(new Intent(LoginActivity.this, SelectServiceActivity.class));
        // finish();
    }
}