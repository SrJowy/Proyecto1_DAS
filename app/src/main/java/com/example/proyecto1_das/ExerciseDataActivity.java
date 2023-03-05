package com.example.proyecto1_das;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ExerciseDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_data);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString("content");
            Log.i("EXDATA", "onCreate: " + data);

            ExerciseDataFragment eFragment = (ExerciseDataFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentContainerView2);

            eFragment.setData(data);
        }



    }
}