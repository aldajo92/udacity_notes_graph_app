package com.projects.aldajo92.notesgraph.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;

public class ConfirmDeleteDialog extends DialogFragment {

    private Button buttonContinue;
    private Button buttonCancel;

    private DeleteDialogCallback callback;

    private DataSetNoteModel dataSetNoteModel;

    private int position = -1;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm_delete, null);
        buttonContinue = view.findViewById(R.id.button_continue);
        buttonCancel = view.findViewById(R.id.button_cancel);

        initListeners();

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false)
                .create();

        Window window = alertDialog.getWindow();

        if (window != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
        }

        return alertDialog;
    }

    private void initListeners() {
        buttonCancel.setOnClickListener(v -> {
            dismiss();
        });

        buttonContinue.setOnClickListener(v -> {
            dismiss();
            if (callback != null) {
                callback.onConfirm(dataSetNoteModel, position);
            }
        });
    }

    private void setCallback(DeleteDialogCallback callback) {
        this.callback = callback;
    }

    public void setDataSetNoteModel(DataSetNoteModel dataSetNoteModel) {
        this.dataSetNoteModel = dataSetNoteModel;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static ConfirmDeleteDialog createInstance(DataSetNoteModel noteModel, int position, DeleteDialogCallback callback) {
        ConfirmDeleteDialog dialog = new ConfirmDeleteDialog();
        dialog.setDataSetNoteModel(noteModel);
        dialog.setPosition(position);
        dialog.setCallback(callback);
        return dialog;
    }

    public interface DeleteDialogCallback {
        void onConfirm(DataSetNoteModel model, int position);
    }

}
