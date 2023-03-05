package com.example.proyecto1_das;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RoutineActivity extends AppCompatActivity {

    private ArrayList<String> lRutinas;

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
    }
}