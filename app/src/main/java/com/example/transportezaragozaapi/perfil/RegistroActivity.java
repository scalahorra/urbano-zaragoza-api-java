package com.example.transportezaragozaapi.perfil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.transportezaragozaapi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch sw_esConductor;
    EditText et_nombreRegistro, et_emailRegistro, et_passwordRegistro, et_password2Registro;

    String nombre, email, password1, password2;
    Boolean esConductor;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicio de bd y autentificador
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        // Conexion de variables con el layout
        et_nombreRegistro = findViewById(R.id.et_nombreRegistro);
        et_emailRegistro = findViewById(R.id.et_emailRegistro);
        et_passwordRegistro = findViewById(R.id.et_passwordRegistro);
        et_password2Registro = findViewById(R.id.et_password2Registro);
        sw_esConductor = findViewById(R.id.sw_esConductor);

        // Boton de registrarse
        Button registrarRegistro = findViewById(R.id.btn_registrarLogeo);
        registrarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });

        Button iniciarSesionRegistro = findViewById(R.id.btn_iniciarSesionRegistro);
        iniciarSesionRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irIniciarSesion = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(irIniciarSesion);
                finish();
            }
        });
    }


    // Metodo para registrarse
    private void registro() {

        // Asignacion del texto a las variables
        nombre = et_nombreRegistro.getText().toString();
        email = et_emailRegistro.getText().toString();
        password1 = et_passwordRegistro.getText().toString();
        password2 = et_password2Registro.getText().toString();
        esConductor = sw_esConductor.isChecked();

        // Verificadores de datos
        if(nombre.isEmpty()) {
            et_nombreRegistro.setError("Escriba su nombre");
            et_nombreRegistro.requestFocus();
        } else if(email.isEmpty()) {
            et_emailRegistro.setError("Escriba su correo electrónico");
            et_emailRegistro.requestFocus();
        } else if(password1.isEmpty()) {
            et_passwordRegistro.setError("Escriba una contraseña");
            et_passwordRegistro.requestFocus();
        } else if(password1.length() < 6) {
            et_passwordRegistro.setError("La contraseña debe tener al menos 6 carácteres");
            et_passwordRegistro.requestFocus();
        } else if(password2.isEmpty()) {
            et_password2Registro.setError("Vuelva a escribir la contraseña");
            et_password2Registro.requestFocus();
        } else if(!password1.equals(password2)) {
            et_passwordRegistro.setError("Las contraseñas deben ser iguales");
            et_passwordRegistro.requestFocus();
            et_password2Registro.setError("Las contraseñas deben ser iguales");
            et_password2Registro.requestFocus();
        } else {

            // Creacion de la cuenta con el email y la contrasena
            firebaseAuth.createUserWithEmailAndPassword(email, password1)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(@NonNull AuthResult authResult) {

                            // Saca el id del usuario
                            String id = firebaseAuth.getCurrentUser().getUid();

                            // Guarda en un hashmap los datos a enviar a la bd
                            Map<String, Object> registro = new HashMap<>();
                            registro.put("id", id);
                            registro.put("nombre", nombre);
                            registro.put("email", email);
                            registro.put("password", password1);
                            registro.put("esConductor", esConductor);

                            // Crea una coleccion y coloca los datos del hashmap
                            firebaseFirestore.collection("usuarios").document(email)
                                    .set(registro)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(@NonNull Void unused) {
                                            Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                            Intent irPerfil = new Intent(RegistroActivity.this, PerfilActivity.class);
                                            startActivity(irPerfil);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegistroActivity.this, "Error al guardar en la base de datos", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            et_emailRegistro.setError("Este correo ya está registrado");
                            et_emailRegistro.requestFocus();
                        }
                    });
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent irLogin = new Intent(RegistroActivity.this, LoginActivity.class);
        startActivity(irLogin);
        finish();
    }
}