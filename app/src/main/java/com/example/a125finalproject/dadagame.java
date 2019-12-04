package com.example.a125finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class dadagame extends AppCompatActivity {
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dadalayout);
        Button initialButton = findViewById(R.id.initialButton);
        Intent newin = new Intent(this, MainActivity.class);
        initialButton.setOnClickListener(unused -> startActivity(newin));
    }
}
