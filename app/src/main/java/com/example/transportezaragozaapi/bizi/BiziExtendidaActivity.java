package com.example.transportezaragozaapi.bizi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.transportezaragozaapi.R;
import com.example.transportezaragozaapi.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BiziExtendidaActivity extends AppCompatActivity {

    TextView id, title, bizisDisponibles, anclajesDisponibles;
    private RequestQueue requestQueue;
    private Bizi bizi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizi_extendida);

        id = findViewById(R.id.tv_idBiziExtendida);
        Bundle bundle = getIntent().getExtras();
        String eId = bundle.getString("id");
        id.setText(eId);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        buscarBiziExtendida();

        title = findViewById(R.id.tv_titleBiziExtendida);
        bizisDisponibles = findViewById(R.id.tv_bDisponiblesBiziExtendida);
        anclajesDisponibles = findViewById(R.id.tv_aBiziExtendida);

        String bTitle = bizi.getTitle();
        title.setText(bTitle);

    }

    private void buscarBiziExtendida() {

        String url = "https://zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta/" + id.getText().toString() + ".json";
        System.out.print(url);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String titulo = jsonObject.getString("title");
                                Integer bicisDisponibles = jsonObject.getInt("bicisDisponibles");
                                Integer anclajesDisponibles = jsonObject.getInt("anclajesDisponibles");
                                String lastUpdated = jsonObject.getString("lastUpdated");


                                bizi = new Bizi(titulo, id.getText().toString(), bicisDisponibles, anclajesDisponibles, lastUpdated);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}