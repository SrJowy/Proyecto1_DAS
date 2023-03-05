package com.example.proyecto1_das;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1_das.data.Exercise;
import com.example.proyecto1_das.databinding.FragmentExerciseBinding;

public class MyViewHolder extends RecyclerView.ViewHolder {

        public interface listenerViewHolder {
            void selectItem(String data);
        }

        private listenerViewHolder listener;

        public final TextView mIdView;
        public final TextView mContentView;
        public Exercise mItem;
        public boolean[] selected;

        public MyViewHolder(FragmentExerciseBinding binding) {
            super(binding.getRoot());
            listener = (listenerViewHolder) binding.getRoot().getContext();
            Log.i("LISTA", "MyViewHolder: " + listener);
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selected[getAbsoluteAdapterPosition()] == true) {
                        selected[getAbsoluteAdapterPosition()] = false;

                    } else {
                        selected[getAbsoluteAdapterPosition()] = true;
                        Log.i("LISTA", "onClick: Se ha pulsado " + mContentView.getText());
                        listener.selectItem(mItem.getDes());
                    }
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
}
