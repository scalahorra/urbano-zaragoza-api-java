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
        setContentView(R.layout.activity_tranvia_extendido);

        TextView tituloTranvia = findViewById(R.id.tv_tituloTranviaExtend);
        TextView idTranvia = findViewById(R.id.tv_idTranviaExtend);
        TextView destinoTranvia1 = findViewById(R.id.tv_destinoTranvia1Extend);
        TextView minutosTranvia1 = findViewById(R.id.tv_minutosTranvia1Extend);
        TextView destinoTranvia2 = findViewById(R.id.tv_destinoTranvia2Extend);
        TextView minutosTranvia2 = findViewById(R.id.tv_minutosTranvia2Extend);

        Bundle bundle = getIntent().getExtras();

        String tTituloTranvia = bundle.getString("tituloTranvia");
        String tIdTranvia = bundle.getString("idTranvia");
        String tDestinoTranvia1 = bundle.getString("destinoTranvia1");
        int tMinutosTranvia1 = bundle.getInt("minutosTranvia1");
        String tDestinoTranvia2 = bundle.getString("destinoTranvia2");
        int tMinutosTranvia2 = bundle.getInt("minutosTranvia2");


        tituloTranvia.setText(tTituloTranvia);
        idTranvia.setText(tIdTranvia);
        destinoTranvia1.setText(tDestinoTranvia1);
        minutosTranvia1.setText(Integer.toString(tMinutosTranvia1));
        destinoTranvia2.setText(tDestinoTranvia2);
        minutosTranvia2.setText(Integer.toString(tMinutosTranvia2));
    }

    @Override
    public void onBackPressed() {
        Intent irTranvias = new Intent(this, TranviaActivity.class);
        startActivity(irTranvias);
        finish();
    }
}