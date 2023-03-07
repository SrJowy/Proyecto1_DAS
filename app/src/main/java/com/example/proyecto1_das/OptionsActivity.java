package com.example.proyecto1_das;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proyecto1_das.dialog.OptionDialog;
import com.example.proyecto1_das.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class OptionsActivity extends AppCompatActivity {

    private List<String> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_pref, new Preferences())
                .commit();


        /*options = new ArrayList<String>();
        options.add(getString(R.string.Theme));
        options.add(getString(R.string.Language));

        ListView lv = findViewById(R.id.lOptions);
        ArrayAdapter a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
        lv.setAdapter(a);
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            if (i == 0) {
                CharSequence[] options = {getString(R.string.Light), getString(R.string.Dark)};
                OptionDialog d = new OptionDialog(getString(R.string.msg_theme_title), options, i);
                d.show(this.getSupportFragmentManager(), "THEME");
            }
        });*/


    }
}