package com.projects.aldajo92.notesgraph.details.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;

import java.util.ArrayList;
import java.util.List;

public class EntriesAdapter extends RecyclerView.Adapter<EntryViewHolder> {

    private List<EntryNoteModel> entryNoteModelList;

    private EntryDataListener entryDataListener;

    private String units;

    public EntriesAdapter(List<EntryNoteModel> entryNoteModelList) {
        this.entryNoteModelList = entryNoteModelList;
    }

    public EntriesAdapter(List<EntryNoteModel> entryNoteModelList, EntryDataListener entryDataListener) {
        this.entryNoteModelList = (entryNoteModelList != null) ? entryNoteModelList : new ArrayList<>();
        this.entryDataListener = entryDataListener;
    }

    public void setEntryDataListener(EntryDataListener entryDataListener) {
        this.entryDataListener = entryDataListener;
    }

    public void addItem(EntryNoteModel model) {
        entryNoteModelList.add(model);
        notifyItemInserted(entryNoteModelList.size() - 1);
    }

    public void updateItem(int position, EntryNoteModel model) {
        entryNoteModelList.set(position, model);
        notifyItemChanged(position);
    }

    public void deleteItem(int position, EntryNoteModel model) {
        entryNoteModelList.remove(position);
        notifyItemRemoved(position);
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_entry, viewGroup, false);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder viewHolder, int i) {
        viewHolder.setUnits(units);
        viewHolder.bindData(entryNoteModelList.get(i));
        viewHolder.bindListener(entryDataListener);
    }

    @Override
    public int getItemCount() {
        if (entryNoteModelList == null) {
            return 0;
        }
        return entryNoteModelList.size();
    }

    public void addItems(List<EntryNoteModel> incomesData) {
        this.entryNoteModelList = incomesData;
        notifyDataSetChanged();
    }
}
