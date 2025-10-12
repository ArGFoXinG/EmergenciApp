package com.ejemplo.miapp; // ¡Asegúrate de que este sea el nombre de tu paquete!

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
// import android.content.Intent; // Descomenta esta línea si quieres navegar a otra actividad

public class MainActivity extends AppCompatActivity {

    // Declara las vistas como variables miembro de la clase
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Obtener referencias a las vistas del XML y asignarlas a las variables
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // 2. Configurar el listener para el clic del botón
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. Obtener el texto de los campos cuando se presiona el botón
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // 4. Lógica de validación (ejemplo simple)
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Aquí iría tu lógica de autenticación real (llamar a una API, verificar en una base de datos, etc.)
                    // Por ahora, solo mostramos un mensaje de éxito.
                    Toast.makeText(MainActivity.this, "Login exitoso para: " + username, Toast.LENGTH_SHORT).show();

                    // Opcional: Navegar a otra pantalla después del login
                    // Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    // startActivity(intent);

                } else {
                    // Mostrar un mensaje de error si los campos están vacíos
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
