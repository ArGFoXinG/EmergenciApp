package com.example.emergenciapp; // ajusta el package

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emergenciapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvForgot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.et_usuario);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvForgot = findViewById(R.id.tv_forgot);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acción simple: mostrar mensaje. Aquí podrías lanzar otra Activity para recuperar contraseña.
                Toast.makeText(LoginActivity.this, "Función recuperar contraseña (a implementar)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void attemptLogin() {
        String usuario = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString();

        // Validaciones básicas
        if (TextUtils.isEmpty(usuario)) {
            etUsuario.setError("Ingrese usuario");
            etUsuario.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Ingrese contraseña");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 4) {
            etPassword.setError("La contraseña debe tener al menos 4 caracteres");
            etPassword.requestFocus();
            return;
        }

        // Aquí pondrías la lógica real (API, base de datos). Por ahora simulamos éxito.
        Toast.makeText(this, "Login OK (simulado). Usuario: " + usuario, Toast.LENGTH_SHORT).show();

        // Ejemplo: abrir MainActivity al loguearse
        // Intent i = new Intent(LoginActivity.this, MainActivity.class);
        // startActivity(i);
        // finish();
    }
}
