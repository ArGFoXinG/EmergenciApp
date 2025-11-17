// Archivo: presentation/ui/SeleccionActivity.java
package com.example.emergenciapp.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.emergenciapp.data.model.Oficio;
import com.example.emergenciapp.databinding.ActivitySeleccionBinding;
import com.example.emergenciapp.presentation.adapter.OficioAdapter;
import com.example.emergenciapp.presentation.viewmodel.SeleccionViewModel;

public class SeleccionActivity extends AppCompatActivity implements OficioAdapter.OnOficioClickListener {

    private ActivitySeleccionBinding binding;
    private SeleccionViewModel viewModel;
    private OficioAdapter adapter;
    private Oficio selectedOficio; // Objeto para guardar el oficio elegido

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Inicializar View Binding (asumiendo que activity_seleccion.xml ya existe)
        binding = ActivitySeleccionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 2. Inicializar ViewModel
        viewModel = new ViewModelProvider(this).get(SeleccionViewModel.class);

        // 3. Configurar RecyclerView y Adapter
        adapter = new OficioAdapter(this); // 'this' pasa la referencia de la Activity para manejar el click
        binding.rvOficios.setLayoutManager(new LinearLayoutManager(this));
        binding.rvOficios.setAdapter(adapter);

        // 4. Observar la lista de oficios desde el ViewModel
        viewModel.getOficios().observe(this, oficios -> {
            adapter.setOficios(oficios);
        });

        // 5. Manejar el click en el botón Siguiente
        binding.nextButton.setOnClickListener(v -> {
            if (selectedOficio != null) {
                // Navegar a la siguiente pantalla, pasando el oficio seleccionado
                Intent intent = new Intent(SeleccionActivity.this, DetallesServicioActivity.class);
                intent.putExtra("OFICIO_SELECCIONADO", selectedOficio.getNombre());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Debe seleccionar un oficio", Toast.LENGTH_SHORT).show();
            }
        });

        // TODO: Enlazar el botón de retroceso de la Toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // 6. Implementación del método de la Interfaz del Adapter
    // Este método se llama cuando el usuario hace clic en un item de la lista
    @Override
    public void onOficioSelected(Oficio oficio) {
        this.selectedOficio = oficio;
        Toast.makeText(this, "Seleccionado: " + oficio.getNombre(), Toast.LENGTH_SHORT).show();
        // Puedes cambiar el texto del botón aquí si quieres
    }
}