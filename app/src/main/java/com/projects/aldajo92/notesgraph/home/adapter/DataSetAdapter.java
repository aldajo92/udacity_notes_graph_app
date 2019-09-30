package com.projects.aldajo92.notesgraph.home.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;

import java.util.ArrayList;
import java.util.List;

public class DataSetAdapter extends RecyclerView.Adapter<CardDataViewHolder> {

    private List<DataSetNoteModel> dataSetNoteModelList;

    private CardDataListener cardDataListener;

    public DataSetAdapter(List<DataSetNoteModel> dataSetNoteModelList) {
        this.dataSetNoteModelList = dataSetNoteModelList;
    }

    public DataSetAdapter(List<DataSetNoteModel> dataSetNoteModelList, CardDataListener cardDataListener) {
        this.dataSetNoteModelList = (dataSetNoteModelList != null) ?  dataSetNoteModelList : new ArrayList<>();
        this.cardDataListener = cardDataListener;
    }

    public void setCardDataListener(CardDataListener cardDataListener) {
        this.cardDataListener = cardDataListener;
    }

    public void setDataSetNoteModelList(List<DataSetNoteModel> dataSetNoteModelList) {
        this.dataSetNoteModelList = dataSetNoteModelList;
        notifyDataSetChanged();
    }

    public void addItem(DataSetNoteModel model) {
        dataSetNoteModelList.add(0, model);
        notifyItemInserted(0);
    }

    public void updateItem(int position, DataSetNoteModel model) {
        dataSetNoteModelList.set(position, model);
        notifyItemChanged(position);
    }

    public void deleteItem(int position, DataSetNoteModel model) {
        dataSetNoteModelList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public CardDataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_card_with_options, viewGroup, false);
        return new CardDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDataViewHolder viewHolder, int i) {
        viewHolder.bindData(dataSetNoteModelList.get(i));
        viewHolder.bindListener(cardDataListener);
    }

    @Override
    public int getItemCount() {
        return dataSetNoteModelList.size();
    }

}
