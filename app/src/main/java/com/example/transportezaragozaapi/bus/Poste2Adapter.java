package com.example.transportezaragozaapi.bus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transportezaragozaapi.R;

import java.util.List;

public class Poste2Adapter extends RecyclerView.Adapter<Poste2Adapter.Poste2Holder> {


    private Context context;
    private List<Poste2> poste2List;


    public Poste2Adapter(Context context, List<Poste2> postes2) {
        this.context = context;
        poste2List = postes2;
    }


    @NonNull
    @Override
    public Poste2Adapter.Poste2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poste2, parent, false);
        return new Poste2Holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Poste2Adapter.Poste2Holder holder, int position) {
        Poste2 poste2 = poste2List.get(position);
        holder.lineaPoste2.setText(poste2.getLineaPoste2());
        holder.destinoPoste2.setText(poste2.getDestinoPoste2());
        holder.primeroPoste2.setText(poste2.getPrimeroPoste2());
        holder.segundoPoste2.setText(poste2.getSegundoPoste2());
    }


    @Override
    public int getItemCount() {
        return poste2List.size();
    }


    public class Poste2Holder extends RecyclerView.ViewHolder {

        TextView idPoste2, tituloPoste2, lineaPoste2, destinoPoste2, primeroPoste2, segundoPoste2, ultimaActPoste2;

        public Poste2Holder(@NonNull View itemView) {
            super(itemView);

            //idPoste2 = itemView.findViewById(R.id.tv_idPoste2);
            //tituloPoste2 = itemView.findViewById(R.id.tv_tituloPoste2);
            lineaPoste2 = itemView.findViewById(R.id.tv_lineaPoste2);
            destinoPoste2 = itemView.findViewById(R.id.tv_destinoPoste2);
            primeroPoste2 = itemView.findViewById(R.id.tv_primeroPoste2);
            segundoPoste2 = itemView.findViewById(R.id.tv_segundoPoste2);
        }
    }
}
