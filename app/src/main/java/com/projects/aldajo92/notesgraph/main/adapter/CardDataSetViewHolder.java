package com.projects.aldajo92.notesgraph.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.main.DataSetNoteModel;

public class CardDataSetViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewTitle;
    private TextView textViewDescription;
    private CardView cardView;

    public CardDataSetViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.textView_title);
        textViewDescription = itemView.findViewById(R.id.textView_description);
        cardView = itemView.findViewById(R.id.cardView);

        cardView.setElevation(0);
    }

    public void bindData(DataSetNoteModel data){
        textViewTitle.setText(data.getTitle());
        textViewDescription.setText(data.getDescription());
    }
}
