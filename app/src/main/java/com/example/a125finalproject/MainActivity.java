package com.example.a125finalproject;

import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public final class MainActivity extends AppCompatActivity{
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        Button standingButton = findViewById(R.id.standingButton);
        Button teamButton = findViewById(R.id.teamButton);
        final Intent intent_standing = new Intent(this, Standing.class);
        final Intent intent_team = new Intent(this, team.class);
        standingButton.setOnClickListener(unused -> startActivity(intent_standing));
        teamButton.setOnClickListener(unused -> startActivity(intent_team));
    }
}
