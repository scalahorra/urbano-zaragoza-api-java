package com.example.transportezaragozaapi.perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button btn_borrarUsuario;

    FirebaseUser usuario;
    FirebaseFirestore firebaseFirestore;

    String emailRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        usuario = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        tv_nombrePerfil = findViewById(R.id.tv_nombrePerfil);
        tv_emailPerfil = findViewById(R.id.tv_emailPerfil);
        tv_esConductor = findViewById(R.id.tv_esConductor);
        iv_fotoPerfil = findViewById(R.id.iv_fotoPerfil);
        btn_borrarUsuario = findViewById(R.id.btn_borrarUsuario);
        btn_borrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarUsuario();
            }
        });

        // Email de referencia para buscar en la bd
        emailRef = usuario.getEmail();

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


    // Metodo borrar cuenta de autentificacion y bd
    private void borrarUsuario() {
        usuario.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Intent irLogin = new Intent(PerfilActivity.this, LoginActivity.class);
                            startActivity(irLogin);
                            Toast.makeText(PerfilActivity.this, "Usuario borrado del sistema", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else Toast.makeText(PerfilActivity.this, "No se pudo borrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                });

        firebaseFirestore.collection("usuarios").document(emailRef)
                .delete();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent irIniciarSesion = new Intent(PerfilActivity.this, LoginActivity.class);
        startActivity(irIniciarSesion);
        finish();
    }
}