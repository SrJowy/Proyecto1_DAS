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
import com.example.proyecto1_das.data.Exercise;
import com.example.proyecto1_das.db.MyDB;

import java.util.List;

public class ExerciseDataFragment extends Fragment {

    private int exID;

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
        MyDB myDB = new MyDB(getContext());
        List<Exercise> lEx = myDB.selectExerciseByExerciseID(exID);
        if (!lEx.isEmpty()) {
            Exercise e = lEx.get(0);
            TextView tExName = getView().findViewById(R.id.exName);
            tExName.setText(e.getName());

            TextView tExDesc = getView().findViewById(R.id.exDesc);
            tExDesc.setText(e.getDes());

            TextView tNumSeries = getView().findViewById(R.id.numSeriesData);
            tNumSeries.setText(Integer.toString(e.getNumSeries()));

            TextView tNumReps = getView().findViewById(R.id.numRepsData);
            tNumReps.setText(Integer.toString(e.getNumReps()));

            TextView tKgs = getView().findViewById(R.id.exKGs);
            tKgs.setText(Double.toString(e.getNumKgs()));
        }
    }

    public void setData(int exID) {
        this.exID = exID;
    }

    public void setData2(int exID) {
        MyDB myDB = new MyDB(getContext());
        List<Exercise> lEx = myDB.selectExerciseByExerciseID(exID);
        if (!lEx.isEmpty()) {
            Exercise e = lEx.get(0);
            TextView tExName = getView().findViewById(R.id.exName);
            tExName.setText(e.getName());

            TextView tExDesc = getView().findViewById(R.id.exDesc);
            tExDesc.setText(e.getDes());

            TextView tNumSeries = getView().findViewById(R.id.numSeriesData);
            tNumSeries.setText(Integer.toString(e.getNumSeries()));

            TextView tNumReps = getView().findViewById(R.id.numRepsData);
            tNumReps.setText(Integer.toString(e.getNumReps()));

            TextView tKgs = getView().findViewById(R.id.exKGs);
            tKgs.setText(Double.toString(e.getNumKgs()));
        }
    }
}