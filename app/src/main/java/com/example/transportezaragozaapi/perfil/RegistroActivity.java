package com.example.transportezaragozaapi.perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegistroActivity extends AppCompatActivity {

    EditText et_nombreRegistro, et_emailRegistro, et_passwordRegistro,
            et_password2Registro, et_codigoPostalRegistro;

    String nombre, email, password1, password2, codigoPostal;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        et_nombreRegistro = findViewById(R.id.et_nombreRegistro);
        et_emailRegistro = findViewById(R.id.et_emailRegistro);
        et_passwordRegistro = findViewById(R.id.et_passwordRegistro);
        et_password2Registro = findViewById(R.id.et_password2Registro);
        et_codigoPostalRegistro = findViewById(R.id.et_codigoPostalRegistro);



        Button registrarRegistro = findViewById(R.id.btn_registrarRegistro);
        registrarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });
    }


    // Metodo para registrarse
    private void registro() {

        nombre = et_nombreRegistro.getText().toString();
        email = et_emailRegistro.getText().toString();
        password1 = et_passwordRegistro.getText().toString();
        password2 = et_password2Registro.getText().toString();
        codigoPostal = et_codigoPostalRegistro.getText().toString();

        if(nombre.isEmpty()) {
            et_nombreRegistro.setError("Escriba su nombre");
            et_nombreRegistro.requestFocus();
        } else if(email.isEmpty()) {
            et_emailRegistro.setError("Escriba su correo electrónico");
            et_emailRegistro.requestFocus();
        } else if(password1.isEmpty()) {
            et_passwordRegistro.setError("Escriba una contraseña");
            et_passwordRegistro.requestFocus();
        }else if(password1.length() < 6) {
            et_passwordRegistro.setError("La contraseña debe tener al menos 6 carácteres");
            et_passwordRegistro.requestFocus();
        } else if(password2.isEmpty()) {
            et_password2Registro.setError("Vuelva a escribir la contraseña");
            et_password2Registro.requestFocus();
        }  else if(!password1.equals(password2)) {
            et_passwordRegistro.setError("Las contraseñas deben ser iguales");
            et_passwordRegistro.requestFocus();
            et_password2Registro.setError("Las contraseñas deben ser iguales");
            et_password2Registro.requestFocus();
        } else if(codigoPostal.isEmpty()) {
            et_codigoPostalRegistro.setError("Debe poner su código postal");
            et_codigoPostalRegistro.requestFocus();
        } else {

            // Creacion del usuario con el email y la contraseña
            firebaseAuth.createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            // Saca el id del usuario
                            String id = firebaseAuth.getCurrentUser().getUid();

                            // Guarda en un hashmap los datos a enviar a la bd
                            Map<String, Object> registro = new HashMap<>();
                            registro.put("id", id);
                            registro.put("nombre", nombre);
                            registro.put("email", email);
                            registro.put("codigoPostal", codigoPostal);
                            registro.put("password", password1);

                            // Crea una coleccion y coloca los datos del hashmap
                            firebaseFirestore.collection("usuarios")
                                    .add(registro)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(@NonNull DocumentReference documentReference) {
                                            Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                            Intent irPerfil = new Intent(RegistroActivity.this, PerfilActivity.class);
                                            startActivity(irPerfil);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegistroActivity.this, "Ha ocurrido un problema en el registro", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegistroActivity.this, "No se ha podido mapear los datos", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}