package com.example.proyecto1_das.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LocaleUtils {
    public static void initialize(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.proyecto1_das_preferences",
                Context.MODE_PRIVATE);

        Locale newLoc = new Locale(sharedPreferences.getString("lang","en"));
        Locale.setDefault(newLoc);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(newLoc);
        configuration.setLayoutDirection(newLoc);

        Context nContext =
                context.createConfigurationContext(configuration);
        context.getResources().updateConfiguration(configuration,
                nContext.getResources().getDisplayMetrics());
    }
}
