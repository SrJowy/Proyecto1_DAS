package com.example.proyecto1_das;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyecto1_das.data.Routine;
import com.example.proyecto1_das.db.MyDB;
import com.example.proyecto1_das.dialog.OptionDialog;
import com.example.proyecto1_das.exercises.ExerciseActivity;
import com.example.proyecto1_das.utils.FileUtils;
import com.example.proyecto1_das.utils.LocaleUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoutineActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OptionDialog.DialogListener{

    private ArrayList<String> lRoutines;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleUtils.initialize(getBaseContext());

        setContentView(R.layout.activity_routine);

        addDataToList();

        DrawerLayout d = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, d, R.string.nav_open, R.string.nav_close);

        d.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView n = findViewById(R.id.nav_menu);
        n.bringToFront();
        n.setNavigationItemSelectedListener(this);

        FloatingActionButton fButton = findViewById(R.id.floating_button);
        fButton.setOnClickListener(c -> {
            Intent i = new Intent(this, AddRoutineActivity.class);
            activityResultLauncher.launch(i);
        });

    }

    private void addDataToList() {
        FileUtils fUtils = new FileUtils();
        String mail = fUtils.readFile(getApplicationContext(), "config.txt");
        if (!mail.isEmpty()) {
            loadData(mail);
        } else {
            lRoutines = new ArrayList<>();
        }
        ListView lv = findViewById(R.id.lRutinas);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lRoutines);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra("RID", ((TextView)view).getText().toString().split(":")[0]);
            startActivity(intent);
        });
        lv.setOnItemLongClickListener((adapterView, view, i, l) -> {
            CharSequence[] options = {getString(R.string.remove)};
            String[] args = {mail, ((TextView)view).getText().toString().split(":")[1].trim()};
            OptionDialog dialogOption = new OptionDialog(getString(R.string.do_action_menu),options, 0, false, args);
            dialogOption.setListener(this);
            dialogOption.show(getSupportFragmentManager(), "dialogRoutine");
            return true;
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    addDataToList();
                }
            }
    );

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

    @Override
    public void onDialogRes(String res) {
        if (res.equals("00")) {
            addDataToList();
            NotificationManager elManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(this, "IdCanal");
            NotificationChannel elCanal = new NotificationChannel("IdCanal", "NombreCanal",
                    NotificationManager.IMPORTANCE_DEFAULT);
            elManager.createNotificationChannel(elCanal);

            elCanal.setDescription("Descripción del canal");
            elCanal.enableLights(true);
            elCanal.setLightColor(Color.RED);
            elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            elCanal.enableVibration(true);

            elBuilder.setSmallIcon(android.R.drawable.stat_sys_warning)
                    .setContentTitle("Mensaje de Alerta")
                    .setContentText("Ejemplo de notificación en DAS.")
                    .setSubText("Información extra")
                    .setVibrate(new long[]{0, 1000, 500, 1000})
                    .setAutoCancel(true);

            elManager.notify(1, elBuilder.build());
        }
     }
}