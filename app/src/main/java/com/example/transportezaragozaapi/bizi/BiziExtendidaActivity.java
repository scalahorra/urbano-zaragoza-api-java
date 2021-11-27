package com.example.transportezaragozaapi.bizi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.transportezaragozaapi.R;
import com.example.transportezaragozaapi.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BiziExtendidaActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizi_extendida);

        ImageView imageView = findViewById(R.id.iv_iconExtendido);
        TextView titulo = findViewById(R.id.tv_titleBiziExtendida);
        TextView id = findViewById(R.id.tv_idBiziExtendida);
        TextView ultimaActualizacion = findViewById(R.id.tv_ultimaActualizacionExtendida);
        TextView bicisDisponibles = findViewById(R.id.tv_bDisponiblesBiziExtendida);
        TextView anclajesDisponibles = findViewById(R.id.tv_aBiziExtendida);

        Bundle bundle= getIntent().getExtras();

        String eTitulo = bundle.getString("titulo");
        String eId = bundle.getString("id");
        String eIcono = bundle.getString("icono");
        String eUltimaActualizacion = bundle.getString("ultimaActualizacion");
        int eBicisDisponibles = bundle.getInt("bicisDisponibles");
        int eAnclajesDisponibles = bundle.getInt("anclajesDisponibles");

        titulo.setText(eTitulo);
        id.setText(eId);
        Glide.with(this).load(eIcono).into(imageView);
        ultimaActualizacion.setText(eUltimaActualizacion);
        bicisDisponibles.setText(Integer.toString(eBicisDisponibles));
        anclajesDisponibles.setText(Integer.toString(eAnclajesDisponibles));
    }

    @Override
    public void onBackPressed() {
        Intent irLista = new Intent(this, BiziActivity.class);
        startActivity(irLista);
        finish();
    }
}