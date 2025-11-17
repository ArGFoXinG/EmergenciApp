// Archivo: presentation/ui/DetallesServicioActivity.java
package com.example.emergenciapp.presentation.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergenciapp.data.model.solicitud;
import com.example.emergenciapp.databinding.ActivityDetallesServicioBinding;
import com.example.emergenciapp.presentation.viewmodel.SolicitudViewModel;

import java.util.Calendar;

public class DetallesServicioActivity extends AppCompatActivity {

    private ActivityDetallesServicioBinding binding;
    private SolicitudViewModel viewModel;
    private String oficioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar View Binding
        binding = ActivityDetallesServicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SolicitudViewModel.class);

        // 1. Obtener el oficio seleccionado de la Activity anterior
        if (getIntent().getExtras() != null) {
            oficioSeleccionado = getIntent().getStringExtra("OFICIO_SELECCIONADO");
            binding.tvOficioSeleccionado.setText("Oficio seleccionado: " + oficioSeleccionado);
        }

        // 2. Configurar Toolbar para retroceso
        setSupportActionBar(binding.toolbarDetalles);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // 3. Manejar el selector de Fecha y Hora (al hacer click en el campo)
        binding.editFechaHora.setOnClickListener(v -> showDateTimePicker());

        // 4. Manejar el botón Solicitar Servicio
        binding.btnSolicitarServicio.setOnClickListener(v -> attemptSolicitud());

        // 5. Observar el resultado de la creación de la solicitud
        viewModel.creacionExitosa.observe(this, isExitoso -> {
            if (isExitoso != null && isExitoso) {
                // ÉXITO: Navegar a la pantalla de Confirmación de Ubicación
                Toast.makeText(this, "Detalles guardados. Confirme ubicación.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DetallesServicioActivity.this, ConfirmarUbicacionActivity.class);
                intent.putExtra("OFICIO_SELECCIONADO", oficioSeleccionado);
                // Aquí deberías pasar también la descripción y fecha/hora si la necesitas después.
                startActivity(intent);
            } else if (isExitoso != null && !isExitoso) {
                Toast.makeText(this, "Error al guardar la solicitud.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void attemptSolicitud() {
        String descripcion = binding.editDescripcion.getText().toString().trim();
        String ubicacion = binding.editUbicacion.getText().toString().trim();
        String fechaHora = binding.editFechaHora.getText().toString().trim();

        if (descripcion.isEmpty() || ubicacion.isEmpty() || fechaHora.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 1. Crear el objeto Solicitud (Usaremos los datos de la UI)
        solicitud nuevaSolicitud = new solicitud(
                null, // ID (null, lo genera el backend)
                "CLIENTE_ID_MOCK", // ID de usuario mockeado
                null, // Profesional ID null
                oficioSeleccionado,
                descripcion,
                ubicacion, // Dirección manual
                0.0, 0.0, // Lat/Lng 0
                fechaHora,
                System.currentTimeMillis(),
                "PENDIENTE"
        );

        // 2. Llamar al ViewModel para guardar la solicitud
        viewModel.crearNuevaSolicitud(nuevaSolicitud);
    }

    // Función para mostrar el selector de Fecha y Hora
    private void showDateTimePicker() {
        final Calendar calendar = Calendar.getInstance();

        // Selector de Fecha
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);

            // Selector de Hora después de la fecha
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                String selectedDateTime = String.format("%d-%02d-%02d %02d:%02d",
                        year, month + 1, dayOfMonth, hourOfDay, minute);

                binding.editFechaHora.setText(selectedDateTime);
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    // Manejar el botón de retroceso de la barra
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}