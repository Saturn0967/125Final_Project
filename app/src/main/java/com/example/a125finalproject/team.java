package com.example.a125finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class team extends AppCompatActivity {
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamlayout);
        final Intent startpage = new Intent(this, MainActivity.class);
        Button back = findViewById(R.id.back1);
        back.setOnClickListener(unused -> startActivity(startpage));
    }
}
