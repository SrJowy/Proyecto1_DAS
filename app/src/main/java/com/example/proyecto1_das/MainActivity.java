package com.example.proyecto1_das;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto1_das.db.MyDB;
import com.example.proyecto1_das.utils.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDB dbManager = new MyDB(this);
        dbManager.insertUsr("joelbraortiz@gmail.com", "12345");
        FileUtils fUtils = new FileUtils();
        if (fUtils.sessionExists(getApplicationContext(), "config.txt")) {
            dbManager.close();
            Intent intent = new Intent(getApplicationContext(), RoutineActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

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
            EditText etMail = findViewById(R.id.editTextMail);
            String mail = etMail.getText().toString();
            EditText etPassword = findViewById(R.id.editTextPassword);
            String password = etPassword.getText().toString();

            boolean exists = dbManager.checkUsr(mail, password);
            dbManager.close();

            if (exists) {
                Random rand = new Random();
                int num = rand.nextInt(900000) + 100000;

                saveSession(mail);

                Intent i = new Intent(this, RoutineActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }

        });

    }

    private void saveSession(String mail) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(mail);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}