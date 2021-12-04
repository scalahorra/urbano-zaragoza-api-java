package com.example.transportezaragozaapi.bus;

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

public class PosteActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Poste> posteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poste);

        recyclerView = findViewById(R.id.recyclerViewBus);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        posteList = new ArrayList<>();

        buscarPoste();
    }


    private void buscarPoste() {

        String url = "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/linea-autobus/41.json";

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

                                try {

                                    JSONObject result = jsonArray.getJSONObject(i);

                                    String urlPoste = result.getString("about");
                                    String titulo = result.getString("title");

                                    Poste poste = new Poste(urlPoste, titulo);
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