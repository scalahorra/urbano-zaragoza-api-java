package com.example.transportezaragozaapi.perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transportezaragozaapi.MainActivity;
import com.example.transportezaragozaapi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    String email, password;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.et_emailRegistro);
        passwordEditText = findViewById(R.id.et_passwordRegistro);
        Button signUp = findViewById(R.id.btn_registrarLogeo);
        Button logIn = findViewById(R.id.btn_iniciarSesionLogeo);

        // Boton registro
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irRegistro = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(irRegistro);
                finish();
            }
        });

        // Boton iniciar sesion
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inicioSesion();
            }
        });

    }


    // Iniciar sesion con email y password
    private void inicioSesion() {

        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        // Verificadores de los campos
        if(email.isEmpty()){
            emailEditText.setError("El email no debe estar vacio");
            emailEditText.requestFocus();
        } else if(password.isEmpty()) {
            passwordEditText.setError("La contraseña no debe estar vacia");
            passwordEditText.requestFocus();
        } else {

            // Autentificacion con el email y la password
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(@NonNull AuthResult authResult) {
                            Toast.makeText(LoginActivity.this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
                            Intent irPerfil = new Intent(LoginActivity.this, PerfilActivity.class);
                            startActivity(irPerfil);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, "Email y/o contraseña incorrecto", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }


    @Override
    public void onBackPressed() {
        Intent irMenu = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(irMenu);
        finish();
    }
}