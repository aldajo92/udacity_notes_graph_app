package com.projects.aldajo92.notesgraph.details.adapter;

import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;

public interface EntryDataListener {
    void onDelete(DataSetNoteModel dataSetNoteModel);

    void onEdit(DataSetNoteModel dataSetNoteModel, int position);

    void onClick(DataSetNoteModel dataSetNoteModel);

    void onFavorite(DataSetNoteModel dataSetNoteModel, Boolean isChecked);
}
