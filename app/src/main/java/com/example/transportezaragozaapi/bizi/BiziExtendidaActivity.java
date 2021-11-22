package com.example.transportezaragozaapi.bizi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.transportezaragozaapi.R;

public class BiziExtendidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizi_extendida);

        TextView title = findViewById(R.id.tv_titleBizi);
        TextView id = findViewById(R.id.tv_idBiziExtendida);
        TextView bicisDisponibles = findViewById(R.id.tv_bDisponiblesBiziExtendida);
        TextView anclajesDisponibles = findViewById(R.id.tv_aBiziExtendida);

        Bundle bundle= getIntent().getExtras();

        String eTitle = bundle.getString("title");
        String eId = bundle.getString("id");
        Integer eBicisDisponibles = bundle.getInt("bicisDisponibles");
        Integer eAnclajesDisponibles = bundle.getInt("anclajesDisponibles");

        title.setText(eTitle);
        id.setText(eId);
        bicisDisponibles.setText(Integer.toString(eBicisDisponibles));
        anclajesDisponibles.setText(Integer.toString(eAnclajesDisponibles));
    }
}