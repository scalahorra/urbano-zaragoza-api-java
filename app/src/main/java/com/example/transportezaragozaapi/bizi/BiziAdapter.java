package com.example.transportezaragozaapi.bizi;

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

public class BiziAdapter extends RecyclerView.Adapter<BiziAdapter.BiziHolder> {

    private Context context;
    private List<Bizi> biziList;

    public BiziAdapter(Context context, List<Bizi> bizis) {
        this.context = context;
        biziList = bizis;
    }


    // Implementaciones del RecyclerView
    @NonNull
    @Override
    public BiziHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bizi, parent, false);
        return new BiziHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BiziHolder holder, int position) {
        Bizi bizi = biziList.get(position);
        holder.title.setText(bizi.getTitle());
        holder.id.setText(bizi.getId());
        holder.bicisDisponibles.setText(bizi.getBicisDisponibles().toString());
        holder.anclajesDisponibles.setText(bizi.getAnclajesDisponibles().toString());
        Glide.with(context).load(bizi.getIcon()).into(holder.icon);


        holder.mainLayoutBizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, BiziExtendidaActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", bizi.getTitle());
                bundle.putString("id", bizi.getId());
                bundle.putString("icon", bizi.getIcon());
                bundle.putInt("bicisDisponibles", bizi.getBicisDisponibles());
                bundle.putInt("anclajesDisponibles", bizi.getAnclajesDisponibles());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return biziList.size();
    }

    public class BiziHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title, id, bicisDisponibles, anclajesDisponibles;
        ConstraintLayout mainLayoutBizi;

        public BiziHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.tv_titleBizi);
            id = itemView.findViewById(R.id.tv_idBizi);
            bicisDisponibles = itemView.findViewById(R.id.tv_bicisBizi);
            anclajesDisponibles = itemView.findViewById(R.id.tv_anclajesBizi);

            mainLayoutBizi = itemView.findViewById(R.id.mainLayoutBizi);
        }
    }
}