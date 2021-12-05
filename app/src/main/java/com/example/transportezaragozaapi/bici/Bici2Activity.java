package com.example.transportezaragozaapi.bici;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.transportezaragozaapi.R;

public class Bici2Activity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bici2);

        TextView tituloBici = findViewById(R.id.tituloBici2_tv);
        TextView idBici = findViewById(R.id.idBici2_tv);
        TextView ultActualizacionBici = findViewById(R.id.ultActualizacionBici2_tv);
        TextView bicisDisponibles = findViewById(R.id.bicisDisponiblesBici2_tv);
        TextView anclajesDisponibles = findViewById(R.id.anclajesDisponiblesBici2_tv);

        Bundle bundleBici = getIntent().getExtras();

        String eTitulo = bundleBici.getString("tituloBici");
        String eId = bundleBici.getString("idBici");
        String eUltimaActualizacion = bundleBici.getString("ultActualizacionBici");
        int eBicisDisponibles = bundleBici.getInt("bicisDisponibles");
        int eAnclajesDisponibles = bundleBici.getInt("anclajesDisponibles");

        tituloBici.setText(eTitulo);
        idBici.setText(eId);
        ultActualizacionBici.setText(eUltimaActualizacion);
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