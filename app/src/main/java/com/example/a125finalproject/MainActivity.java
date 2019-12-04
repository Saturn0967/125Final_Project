package com.example.a125finalproject;

import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;


public final class MainActivity extends AppCompatActivity{
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        Button standingButton = findViewById(R.id.standingButton);
        Button teamButton = findViewById(R.id.teamButton);
        Button dadaButton = findViewById(R.id.dadaButton);
        Button didi = findViewById(R.id.ddidi);
        final Intent intent_dada = new Intent(this, dadagame.class);
        final Intent intent_standing = new Intent(this, Standing.class);
        final Intent intent_didi = new Intent(this, didigame.class);
        final Intent intent_team = new Intent(this, team.class);
        standingButton.setOnClickListener(unused -> startActivity(intent_standing));
        didi.setOnClickListener(unused -> startActivity(intent_didi));
        teamButton.setOnClickListener(unused -> startActivity(intent_team));
        dadaButton.setOnClickListener(unused -> startActivity(intent_dada));
    }
}
