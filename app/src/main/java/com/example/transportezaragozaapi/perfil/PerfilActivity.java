package com.example.transportezaragozaapi.perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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
    ImageView iv_fotoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        tv_nombrePerfil = findViewById(R.id.tv_nombrePerfil);
        tv_emailPerfil = findViewById(R.id.tv_emailPerfil);
        tv_esConductor = findViewById(R.id.tv_esConductor);
        iv_fotoPerfil = findViewById(R.id.iv_fotoPerfil);

        // Email de referencia para buscar en la bd
        String emailRef = user.getEmail();

        // Busqueda de un documento especifico de la bd
        DocumentReference documentReference = firebaseFirestore.collection("usuarios").document(emailRef);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {

                    // Asignacion datos a las variables
                    String nombre = documentSnapshot.get("nombre").toString();
                    String email = documentSnapshot.get("email").toString();
                    String textoConductor;
                    boolean esConductor = documentSnapshot.get("esConductor").equals(true);

                    // Asignacion variables a los datos
                    tv_nombrePerfil.setText(nombre);
                    tv_emailPerfil.setText(email);
                    if(esConductor){
                        textoConductor = "Conductor";
                        iv_fotoPerfil.setImageResource(R.drawable.chofer_imagen);
                    } else{
                        textoConductor = "Pasajero";
                        iv_fotoPerfil.setImageResource(R.drawable.pasajero_imagen);
                    }
                    tv_esConductor.setText(textoConductor);

                    Toast.makeText(PerfilActivity.this, "Carga de datos correcta", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PerfilActivity.this, "No se pudo cargar los datos", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PerfilActivity.this, "No se ha podido obtener el documento de la base de datos", Toast.LENGTH_SHORT).show();
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