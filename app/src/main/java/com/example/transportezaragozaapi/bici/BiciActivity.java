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

    private RecyclerView recyclerViewBici;
    private RequestQueue peticionBici;
    private List<Bici> biciList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bici);

        recyclerViewBici = findViewById(R.id.recyclerViewBicis);
        recyclerViewBici.setHasFixedSize(true);
        recyclerViewBici.setLayoutManager(new LinearLayoutManager(this));

        peticionBici = VolleySingleton.getInstance(this).getRequestQueue();

        biciList = new ArrayList<>();

        buscarBici();
    }


    // Metodo sacar todas las
    private void buscarBici() {

        String urlBici = "https://zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta.json?sort=id%20asc";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                urlBici,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("result");

                            for(int i=0; i<jsonArray.length(); i++) {

                                JSONObject result = jsonArray.getJSONObject(i);

                                String tituloBici = result.getString("title");
                                String idBici = result.getString("id");
                                String ultActualizacionBici = result.getString("lastUpdated");
                                int bicisDisponibles = result.getInt("bicisDisponibles");
                                int anclajesDisponibles = result.getInt("anclajesDisponibles");

                                Bici bici = new Bici(tituloBici, idBici, ultActualizacionBici, bicisDisponibles, anclajesDisponibles);
                                biciList.add(bici);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        BiciAdapter adapter = new BiciAdapter(BiciActivity.this, biciList);
                        recyclerViewBici.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        peticionBici.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        Intent irMenu = new Intent(this, MainActivity.class);
        startActivity(irMenu);
        finish();
    }
}