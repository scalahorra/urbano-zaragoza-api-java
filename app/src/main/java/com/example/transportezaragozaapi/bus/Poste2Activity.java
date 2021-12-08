package com.example.transportezaragozaapi.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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

public class Poste2Activity extends AppCompatActivity {

    private Poste2 poste2;
    private Poste poste;

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Poste2> poste2List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poste2);

        //Cargador ini
        CargadorDialog cargadorDialog = new CargadorDialog(Poste2Activity.this);
        cargadorDialog.iniciarCargadorDialog();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cargadorDialog.cancelarCargadorDialog();
            }
        }, 500);
        //Cargador fin

        TextView idPoste2 = findViewById(R.id.tv_idPoste2);
        TextView tituloPoste2 = findViewById(R.id.tv_tituloPoste2);


        Bundle bundle = getIntent().getExtras();

        String bUrlPoste = bundle.getString("urlPoste");
        String bIdPoste = bundle.getString("idPoste");
        String bTituloPoste = bundle.getString("tituloPoste");

        idPoste2.setText(bIdPoste);
        tituloPoste2.setText(bTituloPoste);

        poste = new Poste("", "", "");

        poste.setUrlPoste(bUrlPoste + ".json");
        System.out.println(poste.getUrlPoste());


        recyclerView = findViewById(R.id.recyclerViewPoste2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        poste2List = new ArrayList<>();
        buscarLineas();

    }


    private void buscarLineas() {

        String url = poste.getUrlPoste();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("destinos");

                            for(int i=0; i<jsonArray.length(); i++) {

                                JSONObject result = jsonArray.getJSONObject(i);

                                poste2 = new Poste2("", "", "", "", "", "", "", "");

                                String lineaPoste2 = result.getString("linea");
                                String destinoPoste2 = result.getString("destino");
                                String primeroPoste2 = result.getString("primero");
                                String segundoPoste2 = result.getString("segundo");

                                poste2.setLineaPoste2(lineaPoste2);
                                poste2.setDestinoPoste2(destinoPoste2);
                                poste2.setPrimeroPoste2(primeroPoste2);
                                poste2.setSegundoPoste2(segundoPoste2);
                                System.out.println(lineaPoste2);

                                poste2List.add(poste2);
                            }


                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                        Poste2Adapter adapter = new Poste2Adapter(Poste2Activity.this, poste2List);
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
        Intent irParadas = new Intent(this, PosteActivity.class);
        startActivity(irParadas);
        finish();
    }
}