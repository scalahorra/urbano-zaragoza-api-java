package com.example.transportezaragozaapi.bus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transportezaragozaapi.R;
import com.example.transportezaragozaapi.tranvia.TranviaAdapter;

import java.util.List;

public class PosteAdapter extends RecyclerView.Adapter<PosteAdapter.PosteHolder> {


    private Context context;
    private List<Poste> posteList;


    public PosteAdapter(Context context, List<Poste> postes) {
        this.context = context;
        posteList = postes;
    }


    @NonNull
    @Override
    public PosteAdapter.PosteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poste, parent, false);
        return new PosteHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PosteAdapter.PosteHolder holder, int position) {

        Poste poste = posteList.get(position);
        holder.idPoste.setText(poste.getIdPoste());
        holder.tituloPoste.setText(poste.getTituloPoste());

        holder.mainLayoutPoste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ParadaExtendidaActivity.class);

                Bundle bundle = new Bundle();

                bundle.putString("idPoste", poste.getIdPoste());

                intent.putExtras(bundle);
                context.startActivity(intent);
                ((BusActivity) context).finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return posteList.size();
    }


    public class PosteHolder extends RecyclerView.ViewHolder {

        TextView idPoste, tituloPoste;
        ConstraintLayout mainLayoutPoste;

        public PosteHolder(@NonNull View itemView) {
            super(itemView);

            idPoste = itemView.findViewById(R.id.tv_idPoste);
            tituloPoste = itemView.findViewById(R.id.tv_tituloPoste);
            mainLayoutPoste = itemView.findViewById(R.id.mainLayoutPoste);
        }
    }
}
