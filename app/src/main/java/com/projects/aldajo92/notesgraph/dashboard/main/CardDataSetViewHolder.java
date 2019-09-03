package com.projects.aldajo92.notesgraph.dashboard.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.projects.aldajo92.notesgraph.R;

public class CardDataSetViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public CardDataSetViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView_title);
    }

    public void bindData(String data){
        textView.setText(data);
    }
}
