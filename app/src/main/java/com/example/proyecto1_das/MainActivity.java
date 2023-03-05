package com.example.proyecto1_das;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bSignIn = findViewById(R.id.button);
        bSignIn.setOnClickListener(c -> {
            Intent i = new Intent(this, RoutineActivity.class);
            startActivity(i);
        });
    }
}