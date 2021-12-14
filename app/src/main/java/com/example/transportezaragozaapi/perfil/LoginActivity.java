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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private String email, password;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        Button signUp = (Button) findViewById(R.id.signUpButton);
        Button logIn = (Button) findViewById(R.id.logInButton);

        // Boton registro
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                // Verificar campos registro
                if(email.isEmpty()) {
                    emailEditText.setError("El email no debe estar vacio");
                    emailEditText.requestFocus();
                } else if(password.isEmpty()) {
                    passwordEditText.setError("La contraseña no debe estar vacia");
                    passwordEditText.requestFocus();
                } else {
                    if(password.length() >= 6) {
                        registroEmailPassword();
                    } else {
                        passwordEditText.setError("La contraseña debe tener al menos 6 carácteres");
                        passwordEditText.requestFocus();
                    }
                }
            }
        });

        // Boton logeo
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                // Verificar campos logeo
                if(email.isEmpty()) {
                    emailEditText.setError("El email no debe estar vacio");
                    emailEditText.requestFocus();
                } else if(password.isEmpty()) {
                    passwordEditText.setError("La contraseña no debe estar vacia");
                    passwordEditText.requestFocus();
                } else {
                    inicioSesion();
                }
            }
        });

    }


    // Registro con email y password
    private void registroEmailPassword() {
        // Creacion del usuario con el email y la password
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // Saca el id del usuario
                    String id = firebaseAuth.getCurrentUser().getUid();

                    // Guarda en un hashmap los datos a enviar a la bd
                    Map<String, Object> login = new HashMap<>();
                    login.put("id", id);
                    login.put("email", email);
                    login.put("password", password);

                    // Coloca en una coleccion de la bd los datos guardados en el hashmap
                    firebaseFirestore.collection("login")
                            .add(login)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(@NonNull DocumentReference documentReference) {
                                    Toast.makeText(LoginActivity.this, "Registro completado", Toast.LENGTH_SHORT).show();
                                    Intent irPerfil = new Intent(LoginActivity.this, PerfilActivity.class);
                                    startActivity(irPerfil);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this, "No se pudo guardar en la base de datos", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(LoginActivity.this, "No se consiguió mapear el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // Iniciar sesion con email y password
    private void inicioSesion() {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(@NonNull AuthResult authResult) {
                        Toast.makeText(LoginActivity.this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Email y/o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onBackPressed() {
        Intent irMenu = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(irMenu);
        finish();
    }
}