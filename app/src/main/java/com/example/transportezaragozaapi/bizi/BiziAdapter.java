package com.example.transportezaragozaapi.bizi;

import android.content.Context;
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
        Glide.with(context).load(bizi.getIcon()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return biziList.size();
    }

    public class BiziHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;

        public BiziHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.tv_titleBizi);
        }
    }
}

