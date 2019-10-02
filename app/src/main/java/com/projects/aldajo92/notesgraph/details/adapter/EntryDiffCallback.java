package com.projects.aldajo92.notesgraph.details.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.projects.aldajo92.notesgraph.models.EntryNoteModel;

import java.util.List;

public class EntryDiffCallback extends DiffUtil.Callback {
    private final List<EntryNoteModel> oldEntryNoteList;
    private final List<EntryNoteModel> newEntryNoteList;

    public EntryDiffCallback(List<EntryNoteModel> oldEmployeeList, List<EntryNoteModel> newEmployeeList) {
        this.oldEntryNoteList = oldEmployeeList;
        this.newEntryNoteList = newEmployeeList;
    }

    @Override
    public int getOldListSize() {
        return oldEntryNoteList.size();
    }

    @Override
    public int getNewListSize() {
        return newEntryNoteList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldEntryNoteList.get(oldItemPosition).getTimestamp().equals(newEntryNoteList.get(
                newItemPosition).getTimestamp());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final EntryNoteModel oldEmployee = oldEntryNoteList.get(oldItemPosition);
        final EntryNoteModel newEmployee = newEntryNoteList.get(newItemPosition);

        return oldEmployee.getTimestamp().equals(newEmployee.getTimestamp());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
