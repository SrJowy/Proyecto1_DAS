package com.example.proyecto1_das;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.proyecto1_das_preferences",
                Context.MODE_PRIVATE);

        Locale newLoc = new Locale(sharedPreferences.getString("lang","en"));
        Locale.setDefault(newLoc);

        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(newLoc);
        configuration.setLayoutDirection(newLoc);

        Context context =
                getBaseContext().createConfigurationContext(configuration);
        getBaseContext().getResources().updateConfiguration(configuration,
                context.getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);

        Button bSignIn = findViewById(R.id.button);
        bSignIn.setOnClickListener(c -> {
            Intent i = new Intent(this, RoutineActivity.class);
            startActivity(i);
        });

    }
}