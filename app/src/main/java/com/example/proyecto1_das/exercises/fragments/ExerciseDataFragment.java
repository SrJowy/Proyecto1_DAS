package com.example.proyecto1_das.exercises.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyecto1_das.R;

public class ExerciseDataFragment extends Fragment {

    private String data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = getView().findViewById(R.id.tv_data);
        tv.setText(data);
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setData2(String data) {
        TextView tv = getView().findViewById(R.id.tv_data);
        tv.setText(data);
    }
}