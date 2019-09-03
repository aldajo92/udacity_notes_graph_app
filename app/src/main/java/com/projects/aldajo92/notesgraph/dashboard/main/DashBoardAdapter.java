package com.projects.aldajo92.notesgraph.dashboard.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.aldajo92.notesgraph.R;

import java.util.List;

public class DashBoardAdapter extends RecyclerView.Adapter<CardDataSetViewHolder> {

    private List<String> items;

    public DashBoardAdapter(List<String> items, Context context) {
        this.items = items;
    }

    @NonNull
    @Override
    public CardDataSetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_card_dataset, viewGroup, false);
        return new CardDataSetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDataSetViewHolder viewHolder, int i) {
        viewHolder.bindData(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
