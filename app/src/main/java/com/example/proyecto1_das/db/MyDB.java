package com.example.proyecto1_das.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.proyecto1_das.data.Exercise;
import com.example.proyecto1_das.data.Routine;

import java.util.ArrayList;
import java.util.List;

public class MyDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_ROUTINES =
            "CREATE TABLE ROUTINES ('ID' INTEGER PRIMARY KEY AUTOINCREMENT, 'MAIL' VARCHAR(255), 'DESCRIPTION' TEXT, FOREIGN KEY (MAIL) REFERENCES USERS(MAIL), UNIQUE (ID, MAIL))";

    private static final String SQL_CREATE_TABLE_USUARIOS =
            "CREATE TABLE USERS ('MAIL' VARCHAR(255) PRIMARY KEY NOT NULL, 'PASSWORD' VARCHAR(255))";

    private static final String SQL_CREATE_TABLE_EX =
            "CREATE TABLE EXERCISES (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,NAME TEXT,DES TEXT,NUM_SERIES INTEGER,NUM_REPS INTEGER,KG REAL,LINK TEXT)";

    private static final String SQL_CREATE_TABLE_EJS_ROUT =
            "CREATE TABLE ROUTINE_EXERCISE (ID_ROUT INTEGER, ID_EJ INTEGER,PRIMARY KEY (ID_ROUT, ID_EJ), FOREIGN KEY (ID_ROUT) REFERENCES ROUTINES(ID), FOREIGN KEY (ID_EJ) REFERENCES EXERCISES(ID))";

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ROUTINES);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USUARIOS);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_EX);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_EJS_ROUT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ROUTINES);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USUARIOS);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_EX);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_EJS_ROUT);
    }

    public void insertRoutine(String mail, String description) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO ROUTINES (MAIL, DESCRIPTION) VALUES (?, ?)";
        db.execSQL(sql, new Object[]{mail, description});
        db.close();
    }

    public void insertEjRoutine(int idRout, int idEj) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO ROUTINE_EXERCISE (ID_ROUT, ID_EJ) VALUES (?, ?)";
        try {
            db.execSQL(sql, new Object[]{idRout, idEj});
        } catch (SQLException e) {
            Log.e("INSERT_ERROR", "insertEjRoutine: Already exists ", e);
        }
        db.close();
    }
    public void insertExercises(String name, String des, int numSeries, int numReps, double kg, String link) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO EXERCISES (NAME, DES, NUM_SERIES, NUM_REPS, KG, LINK) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            db.execSQL(sql, new Object[]{name, des, numSeries, numReps, kg, link});
        } catch (SQLException e) {
            Log.e("INSERT_ERROR", "insertExercises: Already exists ", e);
        }
        db.close();
    }

    public void insertUsr(String usr, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO USERS (MAIL, PASSWORD) VALUES (?, ?)";
        try {
            db.execSQL(sql, new Object[]{usr, pass});
        } catch (SQLException e) {
            Log.e("INSERT_ERROR", "insertUsr: That user already exists ", e);
        }
        db.close();
    }

    public boolean checkUsr(String mail, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM USERS WHERE MAIL = ? AND PASSWORD = ?";
        Cursor cursor = db.rawQuery(query, new String[]{mail, pass});
        boolean res = false;
        if (cursor.getCount() > 0) {
            res = true;
        }
        cursor.close();
        db.close();
        return res;
    }

    public List<Routine> loadRoutines(String mail) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM ROUTINES WHERE MAIL = ?";
        Cursor cursor = db.rawQuery(query, new String[]{mail});

        List<Routine> lRoutines = new ArrayList<Routine>();

        while (cursor.moveToNext()) {
            Routine r = new Routine();
            r.setId(cursor.getInt(0));
            r.setMail(cursor.getString(1));
            r.setDesc(cursor.getString(2));
            lRoutines.add(r);
        }
        cursor.close();
        db.close();
        return lRoutines;
    }


    public List<Exercise> selectExercisesByRoutineID(String idRoutine) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT e.ID, e.NAME, e.DES, e.NUM_SERIES, e.NUM_REPS, e.KG, e.LINK FROM EXERCISES e INNER JOIN ROUTINE_EXERCISE re ON e.ID = re.ID_EJ WHERE re.ID_ROUT = ?";;
        Cursor cursor = db.rawQuery(query, new String[]{idRoutine});

        List<Exercise> lEx = new ArrayList<>();

        while (cursor.moveToNext()) {
            Exercise e = new Exercise();
            e.setId(cursor.getInt(0));
            e.setName(cursor.getString(1));
            e.setDes(cursor.getString(2));
            e.setNumSeries(cursor.getInt(3));
            e.setNumReps(cursor.getInt(4));
            e.setNumKgs(cursor.getDouble(5));
            e.setLink(cursor.getString(6));
            lEx.add(e);
        }
        cursor.close();
        db.close();
        return lEx;
    }

    public List<Exercise> selectExerciseByExerciseID(int idEx) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT ID, NAME, DES, NUM_SERIES, NUM_REPS, KG, LINK FROM EXERCISES WHERE ID = ?";;
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(idEx)});

        List<Exercise> lEx = new ArrayList<>();

        while (cursor.moveToNext()) {
            Exercise e = new Exercise();
            e.setId(cursor.getInt(0));
            e.setName(cursor.getString(1));
            e.setDes(cursor.getString(2));
            e.setNumSeries(cursor.getInt(3));
            e.setNumReps(cursor.getInt(4));
            e.setNumKgs(cursor.getDouble(5));
            e.setLink(cursor.getString(6));
            lEx.add(e);
        }
        cursor.close();
        db.close();
        return lEx;
    }
}
