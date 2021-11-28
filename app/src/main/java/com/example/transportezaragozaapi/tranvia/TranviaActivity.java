package com.example.transportezaragozaapi.tranvia;

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

public class TranviaActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTranvias;
    private RequestQueue requestQueue;
    private List<Tranvia> tranviaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranvia);

        recyclerViewTranvias = findViewById(R.id.recyclerViewTranvias);
        recyclerViewTranvias.setHasFixedSize(true);
        recyclerViewTranvias.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        tranviaList = new ArrayList<>();

        buscarTranvias();
    }


    private void buscarTranvias() {

        String url = "https://zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/parada-tranvia.json";

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

                                String idTranvia = result.getString("id");
                                String tituloTranvia = result.getString("title");
                                String iconoTranvia = result.getString("icon");

                                try {

                                    JSONArray jsonArray1 = result.getJSONArray("destinos");

                                    for (int x = 0; x < jsonArray1.length(); x++) {

                                        JSONObject destinos = jsonArray1.getJSONObject(x);

                                        String destino = destinos.getString("destino");
                                        String minutos = destinos.getString("minutos");

                                        System.out.println("Destino: " + destino);
                                        System.out.println("Minutos: " + minutos);
                                    }
                                } catch (JSONException exception){

                                }

                                Tranvia tranvia = new Tranvia(idTranvia, tituloTranvia, iconoTranvia);
                                tranviaList.add(tranvia);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        TranviaAdapter adapter = new TranviaAdapter(TranviaActivity.this, tranviaList);
                        recyclerViewTranvias.setAdapter(adapter);
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