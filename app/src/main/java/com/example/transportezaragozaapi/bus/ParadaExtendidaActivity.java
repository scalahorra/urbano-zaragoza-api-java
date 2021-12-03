package com.example.transportezaragozaapi.bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.transportezaragozaapi.R;
import com.example.transportezaragozaapi.tranvia.TranviaActivity;

public class ParadaExtendidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parada_extendida);

        //hacer el set de la id aqui

        TextView idPoste2 = findViewById(R.id.idPosteExt2);

        Bundle bundle = getIntent().getExtras();

        String eIdPoste2 = bundle.getString("idPoste");

        idPoste2.setText(eIdPoste2);
    }


    @Override
    public void onBackPressed() {
        Intent irParadas = new Intent(this, BusActivity.class);
        startActivity(irParadas);
        finish();
    }
}