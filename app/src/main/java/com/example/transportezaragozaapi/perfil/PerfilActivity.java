package com.example.transportezaragozaapi.perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transportezaragozaapi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PerfilActivity extends AppCompatActivity {

    TextView tv_nombrePerfil, tv_emailPerfil, tv_esConductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        tv_nombrePerfil = findViewById(R.id.tv_nombrePerfil);
        tv_emailPerfil = findViewById(R.id.tv_emailPerfil);
        tv_esConductor = findViewById(R.id.tv_esConductor);

        // Email de referencia para buscar en la bd
        String emailRef = user.getEmail();

        // Busqueda de un documento especifico de la bd
        DocumentReference documentReference = firebaseFirestore.collection("usuarios").document(emailRef);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    Toast.makeText(PerfilActivity.this, "Carga de datos correcta", Toast.LENGTH_SHORT).show();

                    // Asignacion datos a las variables
                    String nombre = documentSnapshot.get("nombre").toString();
                    String email = documentSnapshot.get("email").toString();
                    boolean esConductor = documentSnapshot.get("esConductor").equals(true);

                    // Asignacion variables a los datos
                    tv_nombrePerfil.setText(nombre);
                    tv_emailPerfil.setText(email);
                    if(esConductor) {
                        tv_esConductor.setText("Es conductor");
                    } else {
                        tv_esConductor.setText("Es pasajero");
                    }

                } else {
                    Toast.makeText(PerfilActivity.this, "No se pudo cargar los datos", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

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