package com.projects.aldajo92.notesgraph.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.main.DataSetNoteModel;

import java.util.List;

public class DataSetAdapter extends RecyclerView.Adapter<CardDataSetViewHolder> {

    private List<DataSetNoteModel> list;

    private CardDataListener cardDataListener;

    public DataSetAdapter(List<DataSetNoteModel> list) {
        this.list = list;
    }

    public DataSetAdapter(List<DataSetNoteModel> list, CardDataListener cardDataListener) {
        this.list = list;
        this.cardDataListener = cardDataListener;
    }

    public void setCardDataListener(CardDataListener cardDataListener) {
        this.cardDataListener = cardDataListener;
    }

    @NonNull
    @Override
    public CardDataSetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_card_with_options, viewGroup, false);
        return new CardDataSetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDataSetViewHolder viewHolder, int i) {
        viewHolder.bindData(list.get(i));
        viewHolder.bindListener(cardDataListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
