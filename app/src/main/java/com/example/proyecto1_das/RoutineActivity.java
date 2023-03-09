package com.example.proyecto1_das;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyecto1_das.data.Routine;
import com.example.proyecto1_das.db.MyDB;
import com.example.proyecto1_das.exercises.ExerciseActivity;
import com.example.proyecto1_das.utils.FileUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class RoutineActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<String> lRoutines;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        FileUtils fUtils = new FileUtils();
        String mail = fUtils.readFile(getApplicationContext(), "config.txt");
        if (!mail.isEmpty()) {
            loadData(mail);
        } else {
            lRoutines = new ArrayList<String>();
        }

        ListView lv = findViewById(R.id.lRutinas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lRoutines);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra("RID", ((TextView)view).getText().toString().split(":")[0]);
            startActivity(intent);
        });


        DrawerLayout d = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, d, R.string.nav_open, R.string.nav_close);

        d.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView n = findViewById(R.id.nav_menu);
        n.bringToFront();
        n.setNavigationItemSelectedListener(this);

    }

    private void loadData(String mail) {
        MyDB dbManager = new MyDB(this);
        List<Routine> lRoutinesDB = dbManager.loadRoutines(mail);
        lRoutines = new ArrayList<String>();
        for (Routine r: lRoutinesDB) {
            lRoutines.add(r.getId() + ": " + r.getDesc());
        }
        dbManager.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (R.id.nav_settings == item.getItemId()) {
            Intent i = new Intent(this, OptionsActivity.class);
            startActivity(i);
        } else if (R.id.nav_logout == item.getItemId()) {
            boolean success = deleteFile("config.txt");

            if (success) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
        return true;
    }
}