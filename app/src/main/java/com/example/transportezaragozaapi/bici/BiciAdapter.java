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

import com.example.transportezaragozaapi.CargadorDialog;
import com.example.transportezaragozaapi.R;

import java.util.List;

public class BiciAdapter extends RecyclerView.Adapter<BiciAdapter.BiciHolder> {

    private final Context context;
    private final List<Bici> biciList;


    public BiciAdapter(Context context, List<Bici> bicis) {
        this.context = context;
        biciList = bicis;
    }


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
        holder.tituloBici.setText(bici.getTituloBici());
        holder.idBici.setText(bici.getIdBici());
        holder.ultActualizacionBici.setText(bici.getUltActualizacionBici());
        holder.bicisDisponibles.setText(bici.getBicisDisponibles().toString());
        holder.anclajesDisponibles.setText(bici.getAnclajesDisponibles().toString());

        holder.mainLayoutBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent irBici2 = new Intent(context, Bici2Activity.class);
                Bundle bundleBici = new Bundle();

                bundleBici.putString("tituloBici", bici.getTituloBici());
                bundleBici.putString("idBici", bici.getIdBici());
                bundleBici.putString("ultActualizacionBici", bici.getUltActualizacionBici());
                bundleBici.putInt("bicisDisponibles", bici.getBicisDisponibles());
                bundleBici.putInt("anclajesDisponibles", bici.getAnclajesDisponibles());

                irBici2.putExtras(bundleBici);
                context.startActivity(irBici2);
                ((BiciActivity) context).finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return biciList.size();
    }

    public class BiciHolder extends RecyclerView.ViewHolder {

        ImageView iconoBici;
        TextView tituloBici, idBici, bicisDisponibles, anclajesDisponibles, ultActualizacionBici;
        ConstraintLayout mainLayoutBici;

        public BiciHolder(@NonNull View itemView) {
            super(itemView);

            iconoBici = itemView.findViewById(R.id.iconoBici_iv);
            tituloBici = itemView.findViewById(R.id.tituloBici_tv);
            idBici = itemView.findViewById(R.id.idBici_tv);
            ultActualizacionBici = itemView.findViewById(R.id.ultActualizacionBici_tv);
            bicisDisponibles = itemView.findViewById(R.id.bicisDisponibles_tv);
            anclajesDisponibles = itemView.findViewById(R.id.anclajesDisponibles_tv);
            mainLayoutBici = itemView.findViewById(R.id.mainLayoutBici);
        }
    }
}