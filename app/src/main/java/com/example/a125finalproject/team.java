package com.example.a125finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

import static android.graphics.Color.parseColor;
import static com.google.gson.JsonParser.parseString;

public class team extends AppCompatActivity {
    protected final int[] teamID = {57, 58, 1044, 328, 397, 61, 354, 62, 338, 64,
            65, 66, 67, 68, 356, 340, 73, 346, 563, 76};
    protected int idTeam = 57;
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamlayout);
        final Intent startpage = new Intent(this, MainActivity.class);
        Button back = findViewById(R.id.back1);
        back.setOnClickListener(unused -> startActivity(startpage));
        Spinner spinner = findViewById(R.id.teamSquadSpinner);
        Button confirm = findViewById(R.id.confirmSelection);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                idTeam = teamID[position];
                confirm.setOnClickListener(unused -> {
                    RequestQueue queue = Volley.newRequestQueue(team.this);
                    String url = "https://api.football-data.org/v2/teams/" + idTeam;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String response) {
                            String team = spinner.getSelectedItem().toString();
                            JsonElement squadAsElement = parseString(response);
                            JsonObject squadAsObject = squadAsElement.getAsJsonObject();
                            JsonArray squadAsList = squadAsObject.get("squad").getAsJsonArray();
                            ScrollView squadScrollView = findViewById(R.id.squadScrollView);
                            TextView title = findViewById(R.id.title);
                            title.setText("Currently looking at members of: " + team);
                            confirm.setText("see another team");
                            LinearLayout squadLayout = squadScrollView.findViewById(R.id.squadLayout);
                            squadLayout.removeAllViews();
                            for (JsonElement players : squadAsList) {
                                if (players.getAsJsonObject().get("role").getAsString().equals("PLAYER")) {
                                    View squadChunk = getLayoutInflater().inflate(R.layout.chunk_squad_list,
                                            squadLayout, false);
                                    JsonObject playerObject = players.getAsJsonObject();
                                    TextView position = squadChunk.findViewById(R.id.position);
                                    position.setText(playerObject.get("position").getAsString());
                                    TextView nationality = squadChunk.findViewById(R.id.nationality);
                                    nationality.setText(playerObject.get("nationality").getAsString());
                                    TextView name = squadChunk.findViewById(R.id.name);
                                    name.setText(playerObject.get("name").getAsString());
                                    TextView color11 = squadChunk.findViewById(R.id.color11);
                                    if (playerObject.get("position").getAsString().equals("Goalkeeper")) {
                                        color11.setBackgroundColor(parseColor("#FF7F00"));
                                    }
                                    if (playerObject.get("position").getAsString().equals("Defender")) {
                                        color11.setBackgroundColor(parseColor("#00FF00"));
                                    }
                                    if (playerObject.get("position").getAsString().equals("Midfielder")) {
                                        color11.setBackgroundColor(parseColor("#0000FF"));
                                    }
                                    if (playerObject.get("position").getAsString().equals("Attacker")) {
                                        color11.setBackgroundColor(parseColor("#FF0000"));
                                    }
                                    squadLayout.addView(squadChunk);
                                    TextView dada = squadChunk.findViewById(R.id.dada);
                                    dada.setBackgroundColor(parseColor("#808080"));
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            int a = 1;
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
            public void onNothingSelected(final AdapterView<?> parent) {
            }
        });
    }
}
