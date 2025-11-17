// Archivo: presentation/adapter/OficioAdapter.java
package com.example.emergenciapp.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.emergenciapp.data.model.Oficio;
import com.example.emergenciapp.databinding.ItemOficioBinding;
import java.util.ArrayList;
import java.util.List;

public class OficioAdapter extends RecyclerView.Adapter<OficioAdapter.OficioViewHolder> {

    private List<Oficio> oficios = new ArrayList<>();
    private OnOficioClickListener listener;
    private int selectedPosition = -1;

    public interface OnOficioClickListener {
        void onOficioSelected(Oficio oficio);
    }

    public OficioAdapter(OnOficioClickListener listener) {
        this.listener = listener;
    }

    public void setOficios(List<Oficio> newOficios) {
        this.oficios = newOficios;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OficioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOficioBinding binding = ItemOf oficioBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new OficioViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OficioViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Oficio currentOficio = oficios.get(position);
        holder.bind(currentOficio, position == selectedPosition);

        holder.binding.oficioContainer.setOnClickListener(v -> {
            int oldSelected = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(oldSelected);
            notifyItemChanged(selectedPosition);

            listener.onOficioSelected(currentOficio);
        });
    }

    @Override
    public int getItemCount() {
        return oficios.size();
    }

    // ViewHolder: Mantiene la referencia a las vistas
    static class OficioViewHolder extends RecyclerView.ViewHolder {
        private final ItemOficioBinding binding;

        public OficioViewHolder(ItemOficioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Oficio oficio, boolean isSelected) {
            binding.tvOficioNombre.setText(oficio.getNombre());
            binding.ivOficioIcon.setImageResource(oficio.getIconResId());
            binding.cbOficioSeleccionado.setChecked(isSelected);
        }
    }
}