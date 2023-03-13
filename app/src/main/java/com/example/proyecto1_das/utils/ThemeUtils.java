package com.example.proyecto1_das.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.proyecto1_das.R;
import com.example.proyecto1_das.RoutineActivity;

public class ThemeUtils {

    public static void changeTheme(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("com.example.proyecto1_das_preferences",
                Context.MODE_PRIVATE);
        String theme = prefs.getString("theme", "light");
        if (theme.equals("light")) {
            context.setTheme(R.style.Theme_Proyecto1_DAS);
        } else {
            context.setTheme(R.style.Theme_Proyecto1_DAS_Dark);
        }
    }

    public static void changeActionBar(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(activity,
                R.color.act_bar)));
        SpannableString s = new SpannableString("Pocket Routine");
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(activity, R.color.white)),
                0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionBar.setTitle(s);
    }
}
