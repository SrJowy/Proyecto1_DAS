package com.example.proyecto1_das;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto1_das.db.MyDB;
import com.example.proyecto1_das.dialog.MessageDialog;
import com.example.proyecto1_das.utils.FileUtils;

public class AddRoutineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        Button b = findViewById(R.id.buttonSaveRoutine);
        b.setOnClickListener(c -> {
            FileUtils fileUtils = new FileUtils();
            String mail = fileUtils.readFile(this, "config.txt");

            EditText etName = findViewById(R.id.editTextRoutineName);
            String rName = etName.getText().toString();
            if (!rName.isEmpty() && !rName.isBlank()) {
                MyDB myDB = new MyDB(this);
                myDB.insertRoutine(mail, rName);
                myDB.close();
                setResult(RESULT_OK);
                finish();
            } else {
                String message = getString(R.string.val_error);
                MessageDialog d = new MessageDialog("ERROR", message);
                d.show(getSupportFragmentManager(), "errorDialog");
            }

        });
    }
}