package com.example.proyecto1_das.exercises;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.proyecto1_das.R;
import com.example.proyecto1_das.exercises.fragments.ExerciseDataFragment;
import com.example.proyecto1_das.utils.LocaleUtils;

public class ExerciseDataActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleUtils.initialize(getBaseContext());
        setContentView(R.layout.activity_exercise_data);

        DrawerLayout d = findViewById(R.id.my_drawer_layout3);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, d, R.string.nav_open, R.string.nav_close);

        d.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int exID = extras.getInt("ExID");

            ExerciseDataFragment eFragment = (ExerciseDataFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentContainerView2);

            eFragment.setData(exID);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}