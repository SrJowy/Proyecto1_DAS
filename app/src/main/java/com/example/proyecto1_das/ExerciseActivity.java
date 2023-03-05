package com.example.proyecto1_das;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class ExerciseActivity extends AppCompatActivity implements MyViewHolder.listenerViewHolder {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
    }

    @Override
    public void selectItem(String data) {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ExerciseDataFragment eFragment = (ExerciseDataFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentContainerView3);
            eFragment.setData2(data);
        } else {
            Intent i = new Intent(this, ExerciseDataActivity.class);
            i.putExtra("content", data);
            startActivity(i);
        }
    }
}