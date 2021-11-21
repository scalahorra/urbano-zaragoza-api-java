package com.example.transportezaragozaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.transportezaragozaapi.bizi.BiziActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Metodos para ir a las actividades
    public void irBizi(View view) {
        Intent irBizi = new Intent(this, BiziActivity.class);
        startActivity(irBizi);
    }
}