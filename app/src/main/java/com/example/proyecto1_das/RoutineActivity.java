package com.example.proyecto1_das;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.proyecto1_das.exercises.ExerciseActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class RoutineActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<String> lRutinas;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    public RoutineActivity() {
        super();
        lRutinas = new ArrayList<String>();
        lRutinas.add("1");
        lRutinas.add("2");
        lRutinas.add("3");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        ListView lv = findViewById(R.id.lRutinas);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lRutinas));

        DrawerLayout d = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, d, R.string.nav_open, R.string.nav_close);

        d.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button b = findViewById(R.id.button2);
        b.setOnClickListener( c -> {
            Intent i = new Intent(this, ExerciseActivity.class);
            startActivity(i);
        });

        NavigationView n = findViewById(R.id.nav_menu);
        n.bringToFront();
        n.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            Log.i("ELEM", "item: " + item);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (R.id.nav_settings == item.getItemId()) {
            Intent i = new Intent(this, OptionsActivity.class);
            startActivity(i);
        }
        return true;
    }
}