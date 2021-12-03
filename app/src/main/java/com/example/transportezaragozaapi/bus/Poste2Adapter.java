package com.example.transportezaragozaapi.bus;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Poste2Adapter extends RecyclerView.Adapter<Poste2Adapter.Poste2Holder> {

    @NonNull
    @Override
    public Poste2Adapter.Poste2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Poste2Adapter.Poste2Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Poste2Holder extends RecyclerView.ViewHolder {

        TextView tituloPoste2, lineaPoste2, destinoPoste2, primeroPoste2, segundoPoste2, ultimaActPoste2;

        public Poste2Holder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
