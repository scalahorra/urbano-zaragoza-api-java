package com.example.transportezaragozaapi.bici;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.transportezaragozaapi.R;

public class BiciExtendidaActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bici_extendida);

        TextView titulo = findViewById(R.id.tv_titleBiciExtendida);
        TextView id = findViewById(R.id.tv_idBiciExtendida);
        TextView ultimaActualizacion = findViewById(R.id.tv_ultimaActualizacionExtendida);
        TextView bicisDisponibles = findViewById(R.id.tv_bDisponiblesBiziExtendida);
        TextView anclajesDisponibles = findViewById(R.id.tv_aBiciExtendida);

        Bundle bundle= getIntent().getExtras();

        String eTitulo = bundle.getString("titulo");
        String eId = bundle.getString("id");
        String eUltimaActualizacion = bundle.getString("ultimaActualizacion");
        int eBicisDisponibles = bundle.getInt("bicisDisponibles");
        int eAnclajesDisponibles = bundle.getInt("anclajesDisponibles");

        titulo.setText(eTitulo);
        id.setText(eId);
        ultimaActualizacion.setText(eUltimaActualizacion);
        bicisDisponibles.setText(Integer.toString(eBicisDisponibles));
        anclajesDisponibles.setText(Integer.toString(eAnclajesDisponibles));
    }

    @Override
    public void onBackPressed() {
        Intent irLista = new Intent(this, BiciActivity.class);
        startActivity(irLista);
        finish();
    }
}