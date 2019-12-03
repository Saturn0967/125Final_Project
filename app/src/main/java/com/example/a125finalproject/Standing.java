package com.example.a125finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static com.google.gson.JsonParser.parseString;

public class Standing extends AppCompatActivity {
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standinglayout);
        Button button = findViewById(R.id.button);
        final Intent startpage = new Intent(this, MainActivity.class);
        Button back = findViewById(R.id.back);
        back.setOnClickListener(unused -> startActivity(startpage));
        LinearLayout standing = findViewById(R.id.saosdfh);
        standing.setVisibility(View.INVISIBLE);
        button.setOnClickListener(unused -> {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://api.football-data.org/v2/competitions/2021/standings";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    JsonElement standingsElement = parseString(response).getAsJsonObject();
                    JsonObject standingsObject = standingsElement.getAsJsonObject();
                    JsonArray standingsArray = standingsObject.get("standings").getAsJsonArray();
                    JsonObject standings = standingsArray.get(0).getAsJsonObject();
                    JsonArray tableArray = standings.get("table").getAsJsonArray();
                    LinearLayout standingsLayout = findViewById(R.id.StandingList);
                    int count = 0;
                    for (int i = 0; i < 20; i++) {
                        JsonElement teamStanding = tableArray.get(i);
                        View standingChunk = getLayoutInflater().inflate(R.layout.chunk_standing_list,
                                standingsLayout, false);
                        TextView position = standingChunk.findViewById(R.id.position);
                        JsonObject standingAsObject = teamStanding.getAsJsonObject();
                        position.setText(standingAsObject.get("position").getAsString());
                        TextView team = standingChunk.findViewById(R.id.team);
                        team.setText(standingAsObject.get("team").getAsJsonObject().get("name").getAsString());
                        TextView played = standingChunk.findViewById(R.id.played);
                        played.setText(standingAsObject.get("playedGames").getAsString());
                        TextView won = standingChunk.findViewById(R.id.won);
                        won.setText(standingAsObject.get("won").getAsString());
                        TextView drew = standingChunk.findViewById(R.id.drew);
                        drew.setText(standingAsObject.get("draw").getAsString());
                        TextView lost = standingChunk.findViewById(R.id.lost);
                        lost.setText(standingAsObject.get("lost").getAsString());
                        TextView points = standingChunk.findViewById(R.id.points);
                        points.setText(standingAsObject.get("points").getAsString());
                        standingsLayout.addView(standingChunk);
                        count++;
                    }
                    standing.setVisibility(View.VISIBLE);
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
}
