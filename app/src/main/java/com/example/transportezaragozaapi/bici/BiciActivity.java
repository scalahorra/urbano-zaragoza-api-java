package com.example.transportezaragozaapi.bici;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.transportezaragozaapi.MainActivity;
import com.example.transportezaragozaapi.R;
import com.example.transportezaragozaapi.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BiciActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Bici> biciList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bici);

        recyclerView = findViewById(R.id.recyclerViewBicis);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        biciList = new ArrayList<>();

        buscarBici();
    }


    // Metodo sacar todas las
    private void buscarBici() {

        String url = "https://zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("result");

                            for(int i=0; i<jsonArray.length(); i++) {

                                JSONObject result = jsonArray.getJSONObject(i);

                                String titulo = result.getString("title");
                                String id = result.getString("id");
                                String ultimaActualizacion = result.getString("lastUpdated");
                                Integer bicisDisponibles = result.getInt("bicisDisponibles");
                                Integer anclajesDisponibles = result.getInt("anclajesDisponibles");

                                Bici bici = new Bici(titulo, id, ultimaActualizacion, bicisDisponibles, anclajesDisponibles);
                                biciList.add(bici);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        BiciAdapter adapter = new BiciAdapter(BiciActivity.this, biciList);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        Intent irMenu = new Intent(this, MainActivity.class);
        startActivity(irMenu);
        finish();
    }
}