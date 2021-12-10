package com.example.transportezaragozaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.transportezaragozaapi.bici.BiciActivity;
import com.example.transportezaragozaapi.bus.PosteActivity;
import com.example.transportezaragozaapi.tranvia.TranviaActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.CargaTheme);
        setContentView(R.layout.activity_main);
    }


    public void irBici(View view) {
        Intent irBici = new Intent(this, BiciActivity.class);
        startActivity(irBici);
        finish();
    }

    public void irTranvia(View view) {
        Intent irTranvia = new Intent(this, TranviaActivity.class);
        startActivity(irTranvia);
        finish();
    }

    public void irBus(View view) {
        Intent irBus = new Intent(this, PosteActivity.class);
        startActivity(irBus);
        finish();
    }

    public void irDatos(View view) {
        Intent irDatos = new Intent(this, DatosActivity.class);
        startActivity(irDatos);
        finish();
    }

    public void irTwitter(View view) {
        String urlTwitter = "https://twitter.com/buszaragoza";
        parseUrl(urlTwitter);
    }

    public void irGithub(View view) {
        String urlGithub = "https://github.com/scalahorra";
        parseUrl(urlGithub);
    }

    public void irWeb(View view) {
        String urlWeb = "https://zaragoza.avanzagrupo.com/";
        parseUrl(urlWeb);
    }

    public void parseUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent lanzarNavegador = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(lanzarNavegador);
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}