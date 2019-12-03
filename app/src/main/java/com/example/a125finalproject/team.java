package com.example.a125finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static com.google.gson.JsonParser.parseString;

public class team extends AppCompatActivity {
    protected final int[] teamID = {57, 58, 1044, 328, 397, 61, 354, 62, 338, 64,
            65, 66, 67, 68, 356, 340, 73, 346, 563, 76};
    protected int idTeam;
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamlayout);
        final Intent startpage = new Intent(this, MainActivity.class);
        Button back = findViewById(R.id.back1);
        back.setOnClickListener(unused -> startActivity(startpage));
        TextView textView = findViewById(R.id.textView);
        Spinner spinner = findViewById(R.id.teamSquadSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                idTeam = teamID[position];
            }
            public void onNothingSelected(final AdapterView<?> parent) {
            }
        });
        Button confirm = findViewById(R.id.confirmSelection);
        confirm.setOnClickListener(unused -> {
            RequestQueue queue = Volley.newRequestQueue(team.this);
            String url = "https://api.football-data.org/v2/teams/" + idTeam;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    textView.setText("Nice");
                    // JsonElement squadAsElement = parseString(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("That didn't work!");
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("X-Auth-Token", "771cf98b010b400aa6d66534393e4d4a");
                    return params;
                }
            };
            queue.add(stringRequest);
        });
    }
}
