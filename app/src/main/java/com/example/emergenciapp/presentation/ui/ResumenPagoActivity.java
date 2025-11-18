// Archivo: presentation/ui/ResumenPagoActivity.java
package com.example.emergenciapp.presentation.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergenciapp.R;
import com.example.emergenciapp.data.model.Pago;
import com.example.emergenciapp.data.model.Profesional;
import com.example.emergenciapp.databinding.ActivityResumenPagoBinding;
import com.example.emergenciapp.presentation.viewmodel.PagoViewModel;

import java.text.NumberFormat;
import java.util.Locale;

public class ResumenPagoActivity extends AppCompatActivity {

    private ActivityResumenPagoBinding binding;
    private PagoViewModel viewModel;
    private ProgressDialog progressDialog;

    // Datos recibidos del Intent
    private Profesional profesionalSeleccionado;
    private String oficioSeleccionado;
    private String descripcionTrabajo;
    private String ubicacion;
    private String fechaHora;
    private String solicitudId;

    private Pago pagoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar View Binding
        binding = ActivityResumenPagoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar ViewModel
        viewModel = new ViewModelProvider(this).get(PagoViewModel.class);

        // Configurar Toolbar
        setSupportActionBar(binding.toolbarResumen);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Resumen del Servicio");
        }

        // Obtener datos del Intent
        obtenerDatosIntent();

        // Mostrar datos en la UI
        mostrarDatosServicio();

        // Observar el resumen de pago
        viewModel.resumenPago.observe(this, pago -> {
            if (pago != null) {
                pagoActual = pago;
                mostrarResumenPago(pago);
            }
        });

        // Calcular resumen inicial
        viewModel.calcularResumen(solicitudId, obtenerCostoBase());

        // Observar el resultado del pago
        viewModel.pagoExitoso.observe(this, exitoso -> {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if (exitoso != null && exitoso) {
                mostrarConfirmacionExitosa();
            } else if (exitoso != null && !exitoso) {
                Toast.makeText(this, "Error al procesar el pago. Intente nuevamente.",
                        Toast.LENGTH_LONG).show();
            }
        });

        // Configurar listener del botón de confirmación
        binding.btnConfirmarPago.setOnClickListener(v -> confirmarPago());

        // Listener para cambios en método de pago
        binding.rgMetodoPago.setOnCheckedChangeListener((group, checkedId) -> {
            actualizarMetodoPago(checkedId);
        });
    }

    /**
     * Obtiene todos los datos pasados desde las Activities anteriores
     */
    private void obtenerDatosIntent() {
        if (getIntent().getExtras() != null) {
            // Datos básicos
            oficioSeleccionado = getIntent().getStringExtra("OFICIO_SELECCIONADO");
            descripcionTrabajo = getIntent().getStringExtra("DESCRIPCION_TRABAJO");
            ubicacion = getIntent().getStringExtra("UBICACION");
            fechaHora = getIntent().getStringExtra("FECHA_HORA");
            solicitudId = getIntent().getStringExtra("SOLICITUD_ID");

            // Datos del profesional (simulados por ahora)
            // En producción, recibirías el objeto Profesional completo
            String profesionalId = getIntent().getStringExtra("PROFESIONAL_ID");
            String profesionalNombre = getIntent().getStringExtra("PROFESIONAL_NOMBRE");
            double calificacion = getIntent().getDoubleExtra("PROFESIONAL_CALIFICACION", 4.5);
            int opiniones = getIntent().getIntExtra("PROFESIONAL_OPINIONES", 0);

            // Crear objeto Profesional
            profesionalSeleccionado = new Profesional(
                    profesionalId != null ? profesionalId : "p1",
                    profesionalNombre != null ? profesionalNombre : "Juan Perez",
                    oficioSeleccionado != null ? oficioSeleccionado : "Carpintero",
                    calificacion,
                    opiniones,
                    2.5,
                    15,
                    null
            );

            // Si no hay solicitud ID, generar uno temporal
            if (solicitudId == null || solicitudId.isEmpty()) {
                solicitudId = "SOL_" + System.currentTimeMillis();
            }
        }
    }

    /**
     * Muestra los datos del servicio en la UI
     */
    private void mostrarDatosServicio() {
        // Datos del profesional
        binding.tvProfesionalNombre.setText(profesionalSeleccionado.getNombre());
        binding.tvProfesionalOficio.setText(profesionalSeleccionado.getOficio());
        binding.tvProfesionalCalificacion.setText(String.format(Locale.getDefault(),
                "⭐ %.1f (%d opiniones)",
                profesionalSeleccionado.getCalificacionPromedio(),
                profesionalSeleccionado.getCantidadOpiniones()));

        // Detalles del servicio
        binding.tvServicioTipo.setText(oficioSeleccionado != null ? oficioSeleccionado : "Servicio General");
        binding.tvUbicacion.setText(ubicacion != null ? ubicacion : "Ubicación no especificada");
        binding.tvFechaHora.setText(fechaHora != null ? fechaHora : "Por coordinar");

        // Descripción del trabajo
        binding.tvDescripcionTrabajo.setText(descripcionTrabajo != null ? descripcionTrabajo :
                "Descripción no disponible");
    }

    /**
     * Muestra el resumen de pago calculado
     */
    private void mostrarResumenPago(Pago pago) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));

        binding.tvCostoBase.setText(currencyFormat.format(pago.getCostoBase()));
        binding.tvCostoVisita.setText(currencyFormat.format(pago.getCostoVisita()));
        binding.tvIva.setText(currencyFormat.format(pago.getIvaMonto()));
        binding.tvTotal.setText(currencyFormat.format(pago.getTotalEstimado()));
    }

    /**
     * Obtiene el costo base según el oficio
     */
    private double obtenerCostoBase() {
        // Costos base por oficio (simulados)
        if (oficioSeleccionado == null) return 40000.0;

        switch (oficioSeleccionado.toUpperCase()) {
            case "CARPINTERO":
                return 45000.0;
            case "PLOMERO":
                return 35000.0;
            case "ELECTRICISTA":
                return 40000.0;
            case "GASISTA":
                return 50000.0;
            case "ALBAÑIL":
                return 55000.0;
            case "CERRAJERO":
                return 30000.0;
            default:
                return 40000.0;
        }
    }

    /**
     * Actualiza el método de pago seleccionado
     */
    private void actualizarMetodoPago(int checkedId) {
        if (pagoActual == null) return;

        String metodoPago;
        if (checkedId == R.id.rb_tarjeta_credito) {
            metodoPago = "Tarjeta de Crédito";
        } else if (checkedId == R.id.rb_tarjeta_debito) {
            metodoPago = "Tarjeta de Débito";
        } else if (checkedId == R.id.rb_efectivo) {
            metodoPago = "Efectivo";
        } else if (checkedId == R.id.rb_transferencia) {
            metodoPago = "Transferencia Bancaria";
        } else {
            metodoPago = "Tarjeta de Crédito";
        }

        pagoActual.setMetodoPago(metodoPago);
    }

    /**
     * Confirma el pago y procesa la solicitud
     */
    private void confirmarPago() {
        if (pagoActual == null) {
            Toast.makeText(this, "Error: No se pudo calcular el resumen de pago",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Mostrar diálogo de progreso
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Procesando pago...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Procesar pago en segundo plano
        new Thread(() -> {
            viewModel.procesarPago(pagoActual);
        }).start();
    }

    /**
     * Muestra la confirmación exitosa y navega a la pantalla final
     */
    private void mostrarConfirmacionExitosa() {
        Toast.makeText(this, "¡Pago procesado exitosamente! El profesional fue notificado.",
                Toast.LENGTH_LONG).show();

        // TODO: Navegar a una pantalla de confirmación final o al home
        // Por ahora, finalizamos todas las activities y volvemos al login
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    // Manejar el botón de retroceso
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}