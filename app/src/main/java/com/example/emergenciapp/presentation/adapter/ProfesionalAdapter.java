// Archivo: presentation/adapter/ProfesionalAdapter.java
package com.example.emergenciapp.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.emergenciapp.data.model.Profesional;
import com.example.emergenciapp.databinding.ItemProfesionalBinding;

import java.util.ArrayList;
import java.util.List;

public class ProfesionalAdapter extends RecyclerView.Adapter<ProfesionalAdapter.ProfesionalViewHolder> {

    private List<Profesional> profesionales = new ArrayList<>();
    private OnProfesionalClickListener listener;

    // Interfaz para manejar los clicks
    public interface OnProfesionalClickListener {
        void onVerPerfilClick(Profesional profesional);
    }

    public ProfesionalAdapter(OnProfesionalClickListener listener) {
        this.listener = listener;
    }

    public void setProfesionales(List<Profesional> newProfesionales) {
        this.profesionales = newProfesionales;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProfesionalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProfesionalBinding binding = ItemProfesionalBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ProfesionalViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfesionalViewHolder holder, int position) {
        Profesional profesional = profesionales.get(position);
        holder.bind(profesional);

        // Manejar click en el botón "Ver Perfil"
        holder.binding.btnVerPerfil.setOnClickListener(v -> {
            if (listener != null) {
                listener.onVerPerfilClick(profesional);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profesionales.size();
    }

    // ViewHolder
    static class ProfesionalViewHolder extends RecyclerView.ViewHolder {
        private final ItemProfesionalBinding binding;

        public ProfesionalViewHolder(ItemProfesionalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Profesional profesional) {
            binding.tvNombre.setText(profesional.getNombre());
            binding.tvOficio.setText(profesional.getOficio());
            binding.tvCalificacion.setText(String.valueOf(profesional.getCalificacionPromedio()));
            binding.tvOpiniones.setText("(" + profesional.getCantidadOpiniones() + " opiniones)");
            binding.tvDistancia.setText(profesional.getDistanciaKm() + " km");
            binding.tvTiempo.setText(profesional.getTiempoEstimadoMin() + " min");

            // La imagen se configurará cuando agregues los recursos
            binding.ivPerfil.setImageResource(profesional.getPerfilImageUrl() != null ?
                    profesional.getPerfilImageUrl().hashCode() : 0);
        }
    }
}