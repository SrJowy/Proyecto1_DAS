package com.example.proyecto1_das.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class OptionDialog extends DialogFragment {

    private String title;
    private CharSequence[] elements;
    private int optionId;

    public OptionDialog(String title, CharSequence[] elements, int optionId) {
        this.title = title;
        this.elements = elements;
        this.optionId = optionId;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setSingleChoiceItems(elements, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (optionId == 0) {
                    if (i == 0) {

                    }
                } else {

                }
            }
        });
        return builder.create();
    }
}
