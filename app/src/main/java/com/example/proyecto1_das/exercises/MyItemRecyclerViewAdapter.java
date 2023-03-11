package com.example.proyecto1_das.exercises;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.proyecto1_das.data.Exercise;
import com.example.proyecto1_das.databinding.FragmentExerciseBinding;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private final List<Exercise> mValues;
    private static boolean[] selected;

    public MyItemRecyclerViewAdapter(List<Exercise> items) {
        selected = new boolean[items.size()];
        mValues = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(
                FragmentExerciseBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false));

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Integer.toString(mValues.get(position).getId()));
        holder.mContentView.setText(mValues.get(position).getName());
        holder.selected = selected;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }



}