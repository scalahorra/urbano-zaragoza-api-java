package com.example.transportezaragozaapi.bici;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.transportezaragozaapi.R;

import java.util.List;

public class BiciAdapter extends RecyclerView.Adapter<BiciAdapter.BiciHolder> {

    private Context context;
    private List<Bici> biciList;

    public BiciAdapter(Context context, List<Bici> bicis) {
        this.context = context;
        biciList = bicis;
    }


    // Implementaciones del RecyclerView
    @NonNull
    @Override
    public BiciHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bici, parent, false);
        return new BiciHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BiciHolder holder, int position) {
        Bici bici = biciList.get(position);
        holder.titulo.setText(bici.getTituloBici());
        holder.id.setText(bici.getIdBici());
        holder.ultimaActualizacion.setText(bici.getUltActualizacionBici());
        holder.bicisDisponibles.setText(bici.getBicisDisponibles().toString());
        holder.anclajesDisponibles.setText(bici.getAnclajesDisponibles().toString());
        Glide.with(context).load(bici.getIconoBici()).into(holder.icono);


        holder.mainLayoutBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, BiciExtendidaActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("titulo", bici.getTituloBici());
                bundle.putString("id", bici.getIdBici());
                bundle.putString("icono", bici.getIconoBici());
                bundle.putString("ultimaActualizacion", bici.getUltActualizacionBici());
                bundle.putInt("bicisDisponibles", bici.getBicisDisponibles());
                bundle.putInt("anclajesDisponibles", bici.getAnclajesDisponibles());

                intent.putExtras(bundle);
                context.startActivity(intent);
                ((BiciActivity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return biciList.size();
    }

    public class BiciHolder extends RecyclerView.ViewHolder {

        ImageView icono;
        TextView titulo, id, bicisDisponibles, anclajesDisponibles, ultimaActualizacion;
        ConstraintLayout mainLayoutBici;

        public BiciHolder(@NonNull View itemView) {
            super(itemView);

            icono = itemView.findViewById(R.id.imageView);
            titulo = itemView.findViewById(R.id.tv_titleBici);
            id = itemView.findViewById(R.id.tv_idBici);
            ultimaActualizacion = itemView.findViewById(R.id.tv_ultimaActualizacionBici);
            bicisDisponibles = itemView.findViewById(R.id.tv_bicisBici);
            anclajesDisponibles = itemView.findViewById(R.id.tv_anclajesBici);

            mainLayoutBici = itemView.findViewById(R.id.mainLayoutBici);
        }
    }
}