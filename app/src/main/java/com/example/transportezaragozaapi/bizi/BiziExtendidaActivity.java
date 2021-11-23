package com.example.transportezaragozaapi.bizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.transportezaragozaapi.MainActivity;
import com.example.transportezaragozaapi.R;

public class BiziExtendidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizi_extendida);

        TextView id = findViewById(R.id.tv_idBiziExtendida);
        TextView title = findViewById(R.id.tv_titleBiziExtendida);
        TextView lastUpdate = findViewById(R.id.tv_lastUpdateBiziExtendida);
        TextView bicisDisponibles = findViewById(R.id.tv_bDisponiblesBiziExtendida);
        TextView anclajesDisponibles = findViewById(R.id.tv_aBiziExtendida);

        Bundle bundle= getIntent().getExtras();

        String eId = bundle.getString("id");
        String eTitle = bundle.getString("title");
        String eLastUpdate = bundle.getString("lastUpdated");
        int eBicisDisponibles = bundle.getInt("bicisDisponibles");
        int eAnclajesDisponibles = bundle.getInt("anclajesDisponibles");

        id.setText(eId);
        title.setText(eTitle);
        lastUpdate.setText(eLastUpdate);
        bicisDisponibles.setText(Integer.toString(eBicisDisponibles));
        anclajesDisponibles.setText(Integer.toString(eAnclajesDisponibles));
    }
}