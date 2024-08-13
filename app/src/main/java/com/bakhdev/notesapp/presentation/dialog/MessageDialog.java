package com.bakhdev.notesapp.presentation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MessageDialog extends DialogFragment {
    String title = "";
    String msg = "";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("Oke", (dialogInterface, i) -> dismiss());
        return builder.create();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
