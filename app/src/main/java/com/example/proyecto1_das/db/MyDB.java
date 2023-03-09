package com.example.proyecto1_das.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.proyecto1_das.data.Routine;

import java.util.ArrayList;
import java.util.List;

public class MyDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_ROUTINES =
            "CREATE TABLE ROUTINES ('ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Mail' VARCHAR(255), 'Description' VARCHAR(255))";

    private static final String SQL_CREATE_TABLE_USUARIOS =
            "CREATE TABLE USUARIOS ('Mail' VARCHAR(255) PRIMARY KEY NOT NULL, 'Password' VARCHAR(255))";

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ROUTINES);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USUARIOS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ROUTINES);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USUARIOS);
    }

    public void insertRoutine(String mail, String description) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO RUTINES (Mail, Description) VALUES (?, ?)";
        db.execSQL(sql, new Object[]{mail, description});
        db.close();
    }

    public void insertUsr(String usr, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO USUARIOS (Mail, Password) VALUES (?, ?)";
        try {
            db.execSQL(sql, new Object[]{usr, pass});
        } catch (SQLException e) {
            Log.e("INSERT_ERROR", "insertUsr: That user already exists ", e);
        }
        db.close();
    }

    public boolean checkUsr(String mail, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM USUARIOS WHERE Mail = ? AND Password = ?";
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
        String query = "SELECT * FROM ROUTINES WHERE Mail = ?";
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



}
