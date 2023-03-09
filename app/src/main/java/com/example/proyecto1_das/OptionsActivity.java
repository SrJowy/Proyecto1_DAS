package com.example.proyecto1_das;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proyecto1_das.dialog.OptionDialog;
import com.example.proyecto1_das.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OptionsActivity extends AppCompatActivity implements Preferences.PrefListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_pref, new Preferences())
                .commit();
    }

    @Override
    public void changeLang(String lang) {
        Locale newLoc = new Locale(lang);
        Locale.setDefault(newLoc);

        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(newLoc);
        configuration.setLayoutDirection(newLoc);

        Context context =
                getBaseContext().createConfigurationContext(configuration);
        getBaseContext().getResources().updateConfiguration(configuration,
                context.getResources().getDisplayMetrics());

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void changeTheme() {

    }
}