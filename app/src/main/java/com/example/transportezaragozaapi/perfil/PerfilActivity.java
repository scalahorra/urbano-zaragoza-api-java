package com.example.transportezaragozaapi.perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.transportezaragozaapi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class PerfilActivity extends AppCompatActivity {

    TextView tv_nombrePerfil, tv_emailPerfil, tv_codigoPostalPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        tv_nombrePerfil = findViewById(R.id.tv_nombrePerfil);
        tv_emailPerfil = findViewById(R.id.tv_emailPerfil);
        tv_codigoPostalPerfil = findViewById(R.id.tv_codigoPostalPerfil);

        String nombre = user.getDisplayName();
        String email = user.getEmail();

        tv_nombrePerfil.setText(nombre);
        tv_emailPerfil.setText(email);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent irIniciarSesion = new Intent(PerfilActivity.this, LoginActivity.class);
        startActivity(irIniciarSesion);
        finish();
    }
}