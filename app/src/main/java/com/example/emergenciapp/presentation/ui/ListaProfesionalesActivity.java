// Archivo: presentation/ui/ListaProfesionalesActivity.java
package com.example.emergenciapp.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.emergenciapp.data.model.Profesional;
import com.example.emergenciapp.databinding.ActivityListaProfesionalesBinding;
import com.example.emergenciapp.presentation.adapter.ProfesionalAdapter;
import com.example.emergenciapp.presentation.viewmodel.ProfesionalesViewModel;

public class ListaProfesionalesActivity extends AppCompatActivity
        implements ProfesionalAdapter.OnProfesionalClickListener {

    private ActivityListaProfesionalesBinding binding;
    private ProfesionalesViewModel viewModel;
    private ProfesionalAdapter adapter;

    private String oficioSeleccionado;
    private double latitud;
    private double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar View Binding
        binding = ActivityListaProfesionalesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar ViewModel
        viewModel = new ViewModelProvider(this).get(ProfesionalesViewModel.class);

        // Obtener datos del Intent (de la Activity anterior)
        if (getIntent().getExtras() != null) {
            oficioSeleccionado = getIntent().getStringExtra("OFICIO_SELECCIONADO");
            latitud = getIntent().getDoubleExtra("LAT", -34.6037);
            longitud = getIntent().getDoubleExtra("LNG", -58.3816);
        }

        // Configurar Toolbar
        setSupportActionBar(binding.toolbarProfesionales);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(oficioSeleccionado + "s Disponibles");
        }

        // Configurar RecyclerView y Adapter
        adapter = new ProfesionalAdapter(this);
        binding.rvProfesionales.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProfesionales.setAdapter(adapter);

        // Observar los cambios en la lista de profesionales
        viewModel.profesionales.observe(this, profesionales -> {
            adapter.setProfesionales(profesionales);
        });

        // Cargar profesionales inicialmente
        viewModel.cargarProfesionales(oficioSeleccionado, latitud, longitud);

        // Configurar botones de filtro
        binding.btnMejorCalificado.setOnClickListener(v -> {
            viewModel.filtrarPorCalificacion();
            Toast.makeText(this, "Ordenado por calificación", Toast.LENGTH_SHORT).show();
        });

        binding.btnMasCercano.setOnClickListener(v -> {
            viewModel.filtrarPorDistancia();
            Toast.makeText(this, "Ordenado por distancia", Toast.LENGTH_SHORT).show();
        });

        binding.btnPrecio.setOnClickListener(v -> {
            viewModel.filtrarPorPrecio();
            Toast.makeText(this, "Ordenado por precio", Toast.LENGTH_SHORT).show();
        });
    }

    // Implementación de la interfaz del Adapter
    @Override
    public void onVerPerfilClick(Profesional profesional) {
        Toast.makeText(this, "Ver perfil de: " + profesional.getNombre(),
                Toast.LENGTH_SHORT).show();

        // TODO: Navegar a la pantalla de Perfil del Profesional
        // Intent intent = new Intent(this, PerfilProfesionalActivity.class);
        // intent.putExtra("PROFESIONAL_ID", profesional.getId());
        // startActivity(intent);
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