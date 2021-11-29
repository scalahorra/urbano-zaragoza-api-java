package com.example.transportezaragozaapi.tranvia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
                                String destinoTranvia1;
                                Integer minutosTranvia1;
                                String destinoTranvia2;
                                Integer minutosTranvia2;

                                Tranvia tranvia = new Tranvia(idTranvia, tituloTranvia, "null", 0, "null", 0);

                                try {
                                    JSONArray jsonArray1 = result.getJSONArray("destinos");

                                    for (int x = 0; x < jsonArray1.length(); x++) {

                                        JSONObject destinos = jsonArray1.getJSONObject(x);

                                        if(x==0) {
                                            destinoTranvia1 = destinos.getString("destino");
                                            minutosTranvia1 = destinos.getInt("minutos");

                                            tranvia.setDestinoTranvia1(destinoTranvia1);
                                            tranvia.setMinutosTranvia1(minutosTranvia1);

                                        } else {
                                            destinoTranvia2 = destinos.getString("destino");
                                            minutosTranvia2 = destinos.getInt("minutos");

                                            tranvia.setDestinoTranvia2(destinoTranvia2);
                                            tranvia.setMinutosTranvia2(minutosTranvia2);
                                        }
                                    }
                                } catch (JSONException exception){
                                    exception.printStackTrace();
                                }
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