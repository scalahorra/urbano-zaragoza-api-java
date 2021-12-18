package com.example.transportezaragozaapi.perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transportezaragozaapi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

        // Email de referencia para buscar en la bd
        String emailRef = user.getEmail();

        // Busqueda de un documento especifico de la bd
        DocumentReference documentReference = firebaseFirestore.collection("usuarios").document(emailRef);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();

                // Comprobacion de que el documento existe
                if(documentSnapshot.exists()) {
                    Toast.makeText(PerfilActivity.this, "" + documentSnapshot.getData(), Toast.LENGTH_SHORT).show();

                    // Asignacion de datos a las variables
                    String nombre = documentSnapshot.get("nombre").toString();
                    String email = documentSnapshot.get("email").toString();
                    String codigoPostal = documentSnapshot.get("codigoPostal").toString();

                    // Asignacion de las variables a los campos de texto
                    tv_nombrePerfil.setText(nombre);
                    tv_emailPerfil.setText(email);
                    tv_codigoPostalPerfil.setText(codigoPostal);

                } else {
                    Toast.makeText(PerfilActivity.this, "No hay documentos", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PerfilActivity.this, "Ocurrió un problema", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent irIniciarSesion = new Intent(PerfilActivity.this, LoginActivity.class);
        startActivity(irIniciarSesion);
        finish();
    }
}