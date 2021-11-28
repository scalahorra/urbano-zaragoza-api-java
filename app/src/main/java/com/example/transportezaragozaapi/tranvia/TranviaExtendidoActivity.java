package com.example.transportezaragozaapi.tranvia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.transportezaragozaapi.R;

public class TranviaExtendidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranvia_extendido);

        TextView tituloTranvia = findViewById(R.id.tv_tituloTranviaExtend);
        TextView idTranvia = findViewById(R.id.tv_idTranviaExtend);

        Bundle bundle = getIntent().getExtras();

        String tTituloTranvia = bundle.getString("tituloTranvia");
        String tIdTranvia = bundle.getString("idTranvia");

        tituloTranvia.setText(tTituloTranvia);
        idTranvia.setText(tIdTranvia);
    }

    @Override
    public void onBackPressed() {
        Intent irTranvias = new Intent(this, TranviaActivity.class);
        startActivity(irTranvias);
        finish();
    }
}