package com.example.transportezaragozaapi.tranvia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.transportezaragozaapi.R;

public class TranviaExtendidoActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranvia2);

        TextView tituloTranvia2 = findViewById(R.id.tituloTranvia2_tv);
        TextView idTranvia2 = findViewById(R.id.idTranvia2);
        TextView destinoTranvia1_2 = findViewById(R.id.destinoTranvia1_2_tv);
        TextView minutosTranvia1_2 = findViewById(R.id.minutosTranvia1_2_tv);
        TextView destinoTranvia2_2 = findViewById(R.id.destinoTranvia2_2_tv);
        TextView minutosTranvia2_2 = findViewById(R.id.minutosTranvia2_2_tv);

        Bundle bundle = getIntent().getExtras();

        String tTituloTranvia = bundle.getString("tituloTranvia");
        String tIdTranvia = bundle.getString("idTranvia");
        String tDestinoTranvia1 = bundle.getString("destinoTranvia1").toLowerCase();
        int tMinutosTranvia1 = bundle.getInt("minutosTranvia1");
        String tDestinoTranvia2 = bundle.getString("destinoTranvia2").toLowerCase();
        int tMinutosTranvia2 = bundle.getInt("minutosTranvia2");


        tituloTranvia2.setText(tTituloTranvia);
        idTranvia2.setText(tIdTranvia);
        destinoTranvia1_2.setText(tDestinoTranvia1);
        minutosTranvia1_2.setText(Integer.toString(tMinutosTranvia1));
        destinoTranvia2_2.setText(tDestinoTranvia2);
        minutosTranvia2_2.setText(Integer.toString(tMinutosTranvia2));
    }

    @Override
    public void onBackPressed() {
        Intent irTranvias = new Intent(this, TranviaActivity.class);
        startActivity(irTranvias);
        finish();
    }
}