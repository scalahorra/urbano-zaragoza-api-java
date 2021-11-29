package com.example.transportezaragozaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.transportezaragozaapi.bici.BiciActivity;
import com.example.transportezaragozaapi.tranvia.TranviaActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Metodos para ir a las actividades
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

    @Override
    public void onBackPressed() {
        finish();
    }
}