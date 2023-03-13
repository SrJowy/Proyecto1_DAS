package com.example.proyecto1_das.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import com.example.proyecto1_das.R;

import java.util.Locale;

public class Preferences extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    public interface PrefListener {
        void changeLang(String lang);
        void changeTheme(String theme);
    }

    private PrefListener prefListener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        prefListener = (PrefListener) context;
    }

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.pref_config);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        switch (s) {
            case "theme":
                prefListener.changeTheme(sharedPreferences.getString(s,"light"));
                break;
            case "lang":
                prefListener.changeLang(sharedPreferences.getString(s,"en"));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
