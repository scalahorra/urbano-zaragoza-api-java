package com.example.transportezaragozaapi.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.transportezaragozaapi.CargadorDialog;
import com.example.transportezaragozaapi.MainActivity;
import com.example.transportezaragozaapi.R;
import com.example.transportezaragozaapi.VolleySingleton;
import com.example.transportezaragozaapi.bici.BiciActivity;
import com.example.transportezaragozaapi.tranvia.TranviaActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PosteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Poste> posteList;

    EditText inputLinea;
    Button btnLinea;
    String numeroLinea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poste);

        recyclerView = findViewById(R.id.recyclerViewBus);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        posteList = new ArrayList<>();

        inputLinea = findViewById(R.id.inputLinea);
        btnLinea = findViewById(R.id.btnLinea);

        btnLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Cargador ini
                CargadorDialog cargadorDialog = new CargadorDialog(PosteActivity.this);
                cargadorDialog.iniciarCargadorDialog();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cargadorDialog.cancelarCargadorDialog();
                    }
                }, 500);
                //Cargador fin

                numeroLinea = inputLinea.getText().toString();
                posteList.clear();
                buscarPoste(numeroLinea);
            }
        });
    }


    private void buscarPoste(String numeroLinea) {

        String urlPoste = "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/linea-autobus/" + numeroLinea + ".json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                urlPoste,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("result");

                            for(int i=0; i<jsonArray.length(); i++) {

                                try {

                                    JSONObject result = jsonArray.getJSONObject(i);

                                    String urlPoste = result.getString("about");
                                    String idPoste = result.getString("description");
                                    String titulo = result.getString("title");

                                    Poste poste = new Poste(urlPoste, idPoste, titulo);
                                    posteList.add(poste);

                                }catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                        PosteAdapter adapter = new PosteAdapter(PosteActivity.this, posteList);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }


    public void irBici(View view) {
        Intent irBici = new Intent(this, BiciActivity.class);
        startActivity(irBici);
        finish();
    }

    public void irTranvia(View view) {
        Intent irTranvia = new Intent(this, TranviaActivity.class);
        startActivity(irTranvia);
        finish();
    }

    public void irMenu(View view) {
        Intent irMenu = new Intent(this, MainActivity.class);
        startActivity(irMenu);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent irMenu = new Intent(this, MainActivity.class);
        startActivity(irMenu);
        finish();
    }
}