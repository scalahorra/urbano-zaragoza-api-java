package com.example.transportezaragozaapi.bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.transportezaragozaapi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Poste2Activity extends AppCompatActivity {

    Poste2 poste2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poste2);

        TextView urlPoste2 = findViewById(R.id.urlPoste2);

        Bundle bundle = getIntent().getExtras();

        String bUrlPoste2 = bundle.getString("urlPoste");
        //urlPoste2.setText(bUrlPoste2);
        System.out.println(bUrlPoste2);

        poste2 = new Poste2("", "", "", "", "", "", "", "");

        poste2.setUrlPoste2(bUrlPoste2);
        urlPoste2.setText(poste2.getUrlPoste2());


    }


    private void buscarLineas() {

        String url = poste2.getUrlPoste2();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }


    @Override
    public void onBackPressed() {
        Intent irParadas = new Intent(this, PosteActivity.class);
        startActivity(irParadas);
        finish();
    }
}