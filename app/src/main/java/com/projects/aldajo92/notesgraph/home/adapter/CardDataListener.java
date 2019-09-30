package com.projects.aldajo92.notesgraph.home.adapter;

import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;

public interface CardDataListener {
    void onDelete(DataSetNoteModel dataSetNoteModel, int position);

    void onEdit(DataSetNoteModel dataSetNoteModel, int position);

    void onClick(DataSetNoteModel dataSetNoteModel);

    void onFavorite(DataSetNoteModel dataSetNoteModel, Boolean isChecked);
}
