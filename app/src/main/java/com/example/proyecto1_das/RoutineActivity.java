package com.example.proyecto1_das;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class RoutineActivity extends AppCompatActivity {

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}