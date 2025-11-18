// Archivo: presentation/ui/ResumenPagoActivity.java
package com.example.emergenciapp.presentation.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.emergenciapp.data.model.Pago;
import com.example.emergenciapp.databinding.ActivityResumenPagoBinding;
import com.example.emergenciapp.presentation.viewmodel.PagoViewModel;

import java.text.NumberFormat;
import java.util.Locale;

public class ResumenPagoActivity extends AppCompatActivity {

    private ActivityResumenPagoBinding binding;
    private PagoViewModel viewModel;
    private Pago currentPago;

    // Usamos el formato de Argentina
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResumenPagoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(PagoViewModel.class);

        // 1. Configurar Toolbar
        setSupportActionBar(binding.toolbarResumen);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // 2. Iniciar el cálculo del resumen (Costo Base simulado)
        // En una app real, el costo vendría del profesional/API
        viewModel.calcularResumen(45000.00, "SOLICITUD_ID_MOCK");

        // 3. Observar el LiveData del resumen de Pago
        viewModel.resumenPago.observe(this, pago -> {
            if (pago != null) {
                currentPago = pago;
                updateUIPagos(pago);
            }
        });

        // 4. Observar la confirmación del pago
        viewModel.pagoConfirmado.observe(this, confirmado -> {
            if (confirmado != null && confirmado) {
                Toast.makeText(this, "¡Servicio Confirmado! Su profesional ya fue notificado.", Toast.LENGTH_LONG).show();
                // TODO: Navegar a la pantalla de tracking o finalizar
                finish();
            }
        });

        // 5. Manejar el click en "CONFIRMAR Y PAGAR"
        binding.btnConfirmarPagar.setOnClickListener(v -> attemptConfirmPayment());
    }

    private void updateUIPagos(Pago pago) {
        binding.tvCostoBase.setText(currencyFormatter.format(pago.getCostoBase()));
        binding.tvTotalEstimado.setText(currencyFormatter.format(pago.getTotalEstimado()));
        // NOTA: El IVA/Costo Visita se actualizaría aquí también
        // ...
    }

    private void attemptConfirmPayment() {
        if (currentPago == null) return;

        String metodoSeleccionado = "Tarjeta"; // Por defecto

        // Determinar el método de pago seleccionado por el RadioGroup
        if (binding.rgMetodoPago.getCheckedRadioButtonId() == binding.rbEfectivo.getId()) {
            metodoSeleccionado = "Efectivo";
        }

        // Llamar al ViewModel para simular la confirmación
        viewModel.confirmarPago(metodoSeleccionado);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}