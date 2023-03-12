package com.example.proyecto1_das.exercises;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1_das.data.Exercise;
import com.example.proyecto1_das.databinding.FragmentExerciseBinding;

public class MyViewHolder extends RecyclerView.ViewHolder {

        public interface listenerViewHolder {
            void selectItem(int exID);
        }

        private final listenerViewHolder listener;

        public final TextView mIdView;
        public final TextView mContentView;
        public Exercise mItem;
        public boolean[] selected;

        public MyViewHolder(FragmentExerciseBinding binding) {
            super(binding.getRoot());
            listener = (listenerViewHolder) binding.getRoot().getContext();
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            binding.getRoot().setOnClickListener(view -> {
                if (!selected[getAbsoluteAdapterPosition()]) {
                    listener.selectItem(mItem.getId());
                }
            });
        }

}
