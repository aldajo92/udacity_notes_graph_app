package com.projects.aldajo92.notesgraph.details.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;

public class EntryViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewDate;
    private TextView textViewValue;
    private TextView textViewDescription;
    private CardView cardView;

    private EntryDataListener listener;

    private EntryNoteModel entryNoteModel;


    public EntryViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewDate = itemView.findViewById(R.id.textView_date);
        textViewValue = itemView.findViewById(R.id.textView_value);
        textViewDescription = itemView.findViewById(R.id.textView_description);
        cardView = itemView.findViewById(R.id.cardView);

        cardView.setElevation(0);
        cardView.setOnClickListener(v -> {
            if(listener != null){
                listener.onClick(entryNoteModel, getAdapterPosition());
            }
        });
    }

    public void bindData(EntryNoteModel data) {
        this.entryNoteModel = data;
        textViewDate.setText("" + data.getTimestamp());
        textViewValue.setText("" + data.getValue());
        textViewDescription.setText(data.getDescription());
    }

    public void bindListener(EntryDataListener listener) {
        this.listener = listener;
    }
}
