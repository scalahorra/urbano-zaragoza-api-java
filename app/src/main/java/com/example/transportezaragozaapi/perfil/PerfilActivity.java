package com.example.transportezaragozaapi.perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.transportezaragozaapi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilActivity extends AppCompatActivity {

    TextView tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        tv_email = findViewById(R.id.tv_email);

        String email = "email: " + user.getEmail();

        tv_email.setText(email);
    }
}