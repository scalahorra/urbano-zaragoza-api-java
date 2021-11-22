package com.example.transportezaragozaapi.bizi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.transportezaragozaapi.R;

public class BiziExtendidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizi_extendida);

        ImageView imageView = findViewById(R.id.iv_iconExtendido);
        TextView title = findViewById(R.id.tv_titleBiziExtendida);
        TextView id = findViewById(R.id.tv_idBiziExtendida);
        TextView bicisDisponibles = findViewById(R.id.tv_bDisponiblesBiziExtendida);
        TextView anclajesDisponibles = findViewById(R.id.tv_aBiziExtendida);

        Bundle bundle= getIntent().getExtras();

        String eTitle = bundle.getString("title");
        String eId = bundle.getString("id");
        String eIcon = bundle.getString("icon");
        int eBicisDisponibles = bundle.getInt("bicisDisponibles");
        int eAnclajesDisponibles = bundle.getInt("anclajesDisponibles");

        title.setText(eTitle);
        id.setText(eId);
        Glide.with(this).load(eIcon).into(imageView);
        bicisDisponibles.setText(Integer.toString(eBicisDisponibles));
        anclajesDisponibles.setText(Integer.toString(eAnclajesDisponibles));
    }
}