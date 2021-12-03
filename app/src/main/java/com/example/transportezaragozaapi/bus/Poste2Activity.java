package com.example.transportezaragozaapi.bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.transportezaragozaapi.R;

public class Poste2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poste2);

        TextView urlPoste2 = findViewById(R.id.idPosteExt2);

        Bundle bundle = getIntent().getExtras();

        String bUrlPoste2 = bundle.getString("idPoste");

        urlPoste2.setText(bUrlPoste2);
    }


    @Override
    public void onBackPressed() {
        Intent irParadas = new Intent(this, PosteActivity.class);
        startActivity(irParadas);
        finish();
    }
}