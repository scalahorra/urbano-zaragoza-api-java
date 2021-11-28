package com.example.transportezaragozaapi.tranvia;

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
import java.util.Objects;

public class TranviaAdapter extends RecyclerView.Adapter<TranviaAdapter.TranviaHolder> {

    private Context context;
    private List<Tranvia> tranviaList;

    public TranviaAdapter(Context context, List<Tranvia> tranvias) {
        this.context = context;
        tranviaList = tranvias;
    }

    @NonNull
    @Override
    public TranviaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tranvia, parent, false);
        return new TranviaHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TranviaHolder holder, int position) {

        Tranvia tranvia = tranviaList.get(position);
        Glide.with(context).load(tranvia.getIconoTranvia()).into(holder.iconoTranvia);
        holder.idTranvia.setText(tranvia.getIdTranvia());
        holder.tituloTranvia.setText(tranvia.getTituloTranvia());
        holder.destinoTranvia1.setText(tranvia.getDestinoTranvia1());
        holder.minutosTranvia1.setText(tranvia.getMinutosTranvia1().toString());
        holder.destinoTranvia2.setText(tranvia.getDestinoTranvia2());
        holder.minutosTranvia2.setText(tranvia.getMinutosTranvia2().toString());

        holder.mainLayoutTranvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, TranviaExtendidoActivity.class);

                Bundle bundle = new Bundle();

                bundle.putString("tituloTranvia", tranvia.getTituloTranvia());
                bundle.putString("idTranvia", tranvia.getIdTranvia());
                bundle.putString("destinoTranvia1", tranvia.getDestinoTranvia1());
                bundle.putInt("minutosTranvia1", tranvia.getMinutosTranvia1());
                bundle.putString("destinoTranvia2", tranvia.getDestinoTranvia2());
                bundle.putInt("minutosTranvia2", tranvia.getMinutosTranvia2());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tranviaList.size();
    }

    public class TranviaHolder extends RecyclerView.ViewHolder {

        ImageView iconoTranvia;
        TextView idTranvia, tituloTranvia, destinoTranvia1, minutosTranvia1, destinoTranvia2, minutosTranvia2;
        ConstraintLayout mainLayoutTranvia;


        public TranviaHolder(@NonNull View itemView) {
            super(itemView);

            iconoTranvia = itemView.findViewById(R.id.iv_iconoTranvia);
            idTranvia = itemView.findViewById(R.id.tv_idTranvia);
            tituloTranvia = itemView.findViewById(R.id.tv_tituloTranvia);
            destinoTranvia1 = itemView.findViewById(R.id.tv_destinoTranvia1);
            minutosTranvia1 = itemView.findViewById(R.id.tv_minutosTranvia1);
            destinoTranvia2 = itemView.findViewById(R.id.tv_destinoTranvia2);
            minutosTranvia2 = itemView.findViewById(R.id.tv_minutosTranvia2);
            mainLayoutTranvia = itemView.findViewById(R.id.mainLayoutTranvia);
        }
    }
}
