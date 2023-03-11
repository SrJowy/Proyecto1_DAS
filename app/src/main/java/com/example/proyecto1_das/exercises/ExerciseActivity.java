package com.example.proyecto1_das.exercises;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.proyecto1_das.R;
import com.example.proyecto1_das.data.Exercise;
import com.example.proyecto1_das.exercises.fragments.ExerciseDataFragment;
import com.example.proyecto1_das.exercises.fragments.ExerciseFragment;
import com.example.proyecto1_das.preferences.Preferences;
import com.example.proyecto1_das.utils.LocaleUtils;

public class ExerciseActivity extends AppCompatActivity implements MyViewHolder.listenerViewHolder {

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleUtils.initialize(getBaseContext());
        setContentView(R.layout.activity_exercise);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String rId = bundle.getString("RID");
            Bundle b = new Bundle();
            b.putString("RID", rId);
            ExerciseFragment eFrag = new ExerciseFragment();
            eFrag.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, eFrag)
                    .commit();
        }

        DrawerLayout d = findViewById(R.id.my_drawer_layout2);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, d, R.string.nav_open, R.string.nav_close);

        d.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void selectItem(int exID) {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ExerciseDataFragment eFragment = (ExerciseDataFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentContainerView3);
            eFragment.setData2(exID);
        } else {
            Intent i = new Intent(this, ExerciseDataActivity.class);
            i.putExtra("ExID", exID);
            startActivity(i);
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