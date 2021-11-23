package com.example.transportezaragozaapi.bizi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.transportezaragozaapi.R;
import com.example.transportezaragozaapi.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BiziActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Bizi> biziList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizi);

        recyclerView = findViewById(R.id.recyclerViewBizi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        biziList = new ArrayList<>();

        buscarBizi();
    }


    // Metodo sacar todas las
    private void buscarBizi() {

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
                                String title = result.getString("title");
                                String iconProvisional = result.getString("icon");
                                String icon = "https:" + iconProvisional;

                                String id = result.getString("id");
                                Integer bicisDisponibles = result.getInt("bicisDisponibles");
                                Integer anclajesDisponibles = result.getInt("anclajesDisponibles");
                                String lastUpdated = result.getString("lastUpdated");

                                //Bizi bizi = new Bizi(title, id, icon);
                                Bizi bizi = new Bizi(title, id, bicisDisponibles, anclajesDisponibles, icon, lastUpdated);
                                biziList.add(bizi);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        BiziAdapter adapter = new BiziAdapter(BiziActivity.this, biziList);
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
}