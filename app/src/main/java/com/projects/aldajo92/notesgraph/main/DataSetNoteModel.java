package com.projects.aldajo92.notesgraph.main;

import java.util.List;

public class DataSetNoteModel {

    private String title;
    private String description;
    private List<EntryNote> entryNoteList;

    public DataSetNoteModel(String title, String description, List<EntryNote> entryNoteList) {
        this.title = title;
        this.description = description;
        this.entryNoteList = entryNoteList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EntryNote> getEntryNoteList() {
        return entryNoteList;
    }

    public void setEntryNoteList(List<EntryNote> entryNoteList) {
        this.entryNoteList = entryNoteList;
    }
}
