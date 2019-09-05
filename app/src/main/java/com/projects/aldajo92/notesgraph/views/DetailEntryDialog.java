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
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;

import java.util.Locale;

public class DetailEntryDialog extends DialogFragment {

    private ImageView imageViewEdit;
    private TextView textViewDate;
    private TextView textViewValue;
    private TextView textViewDescription;
    private TextView textViewPosition;

    private EditEntryCallback callback;

    private EntryNoteModel noteModel;

    private int position = -1;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_detail_entry, null);
        imageViewEdit = view.findViewById(R.id.imageView_edit);
        textViewDate = view.findViewById(R.id.textView_date);
        textViewValue = view.findViewById(R.id.textView_value);
        textViewDescription = view.findViewById(R.id.textView_description);
        textViewPosition = view.findViewById(R.id.textView_position);

        textViewDate.setText("" + noteModel.getTimestamp());
        textViewValue.setText(String.format(Locale.getDefault(), "%.2f", noteModel.getValue()));
        textViewDescription.setText(noteModel.getDescription());
        textViewPosition.setText(String.format("%s", position));

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
        imageViewEdit.setOnClickListener(v -> {
            dismiss();
            if (callback != null) {
                callback.onEdit(noteModel, position);
            }
        });
    }

    private void setCallback(EditEntryCallback callback) {
        this.callback = callback;
    }

    public void setNoteModel(EntryNoteModel noteModel) {
        this.noteModel = noteModel;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static DetailEntryDialog createInstance(EntryNoteModel noteModel, int position, EditEntryCallback callback) {
        DetailEntryDialog dialog = new DetailEntryDialog();
        dialog.setNoteModel(noteModel);
        dialog.setPosition(position);
        dialog.setCallback(callback);
        return dialog;
    }

    public interface EditEntryCallback {
        void onEdit(EntryNoteModel noteModel, int position);
    }

}
