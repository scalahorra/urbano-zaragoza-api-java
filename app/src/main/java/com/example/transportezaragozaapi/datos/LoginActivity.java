package com.example.transportezaragozaapi.datos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private String email, password;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        mEmail = (EditText) findViewById(R.id.emailEditText);
        mPassword = (EditText) findViewById(R.id.passwordEditText);
        Button signUp = (Button) findViewById(R.id.signUpButton);
        Button logIn = (Button) findViewById(R.id.logInButton);

        // Boton registro
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = mEmail.getText().toString();
                password = mPassword.getText().toString();

                // Comprobador de datos
                if(!email.isEmpty() && !password.isEmpty()) {
                    if(password.length() >= 6) {
                        registroEmailPassword();
                    } else {
                        Toast.makeText(LoginActivity.this, "La contraseña debe tener al menos 6 carácteres", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "No debe dejar ningún campo vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Boton logeo
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    private void registroEmailPassword() {
        // Creacion del usuario con el email y la password
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
                                    Intent irPerfil = new Intent(LoginActivity.this, ProfileActivity.class);
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


    @Override
    public void onBackPressed() {
        Intent irMenu = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(irMenu);
        finish();
    }
}