package com.example.transportezaragozaapi.tranvia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
import com.example.transportezaragozaapi.bus.PosteActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TranviaActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTranvia;
    private RequestQueue peticionTranvia;
    private List<Tranvia> tranviaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranvia);

        //Cargador ini
        CargadorDialog cargadorDialog = new CargadorDialog(TranviaActivity.this);
        cargadorDialog.iniciarCargadorDialog();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cargadorDialog.cancelarCargadorDialog();
            }
        }, 500);
        //Cargador fin

        recyclerViewTranvia = findViewById(R.id.recyclerViewTranvia);
        recyclerViewTranvia.setHasFixedSize(true);
        recyclerViewTranvia.setLayoutManager(new LinearLayoutManager(this));

        peticionTranvia = VolleySingleton.getInstance(this).getRequestQueue();

        tranviaList = new ArrayList<>();

        buscarTranvias();
    }


    private void buscarTranvias() {

        String urlTranvia = "https://zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/parada-tranvia.json?sort=id%20desc";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                urlTranvia,
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
                                int minutosTranvia1;
                                String destinoTranvia2;
                                int minutosTranvia2;

                                Tranvia tranvia = new Tranvia(idTranvia, tituloTranvia, "", 0, "", 0);

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
                        recyclerViewTranvia.setAdapter(adapter);
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
        peticionTranvia.add(jsonObjectRequest);
    }


    public void irBici(View view) {
        Intent irBici = new Intent(this, BiciActivity.class);
        startActivity(irBici);
        finish();
    }

    public void irMenu(View view) {
        Intent irMenu = new Intent(this, MainActivity.class);
        startActivity(irMenu);
        finish();
    }

    public void irBus(View view) {
        Intent irBus = new Intent(this, PosteActivity.class);
        startActivity(irBus);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent irMenu = new Intent(this, MainActivity.class);
        startActivity(irMenu);
        finish();
    }
}