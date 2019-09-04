package com.projects.aldajo92.notesgraph.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DataSetNoteModel implements Parcelable {

    private String title;
    private String description;
    private String units;
    private List<EntryNote> entryNoteList;

    public DataSetNoteModel(String title, String description, String units, List<EntryNote> entryNoteList) {
        this.title = title;
        this.description = description;
        this.units = units;
        this.entryNoteList = entryNoteList;
    }

    public DataSetNoteModel(String title, String description, List<EntryNote> entryNoteList) {
        this.title = title;
        this.description = description;
        this.entryNoteList = entryNoteList;
    }

    protected DataSetNoteModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        units = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(units);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataSetNoteModel> CREATOR = new Creator<DataSetNoteModel>() {
        @Override
        public DataSetNoteModel createFromParcel(Parcel in) {
            return new DataSetNoteModel(in);
        }

        @Override
        public DataSetNoteModel[] newArray(int size) {
            return new DataSetNoteModel[size];
        }
    };

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

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public List<EntryNote> getEntryNoteList() {
        return entryNoteList;
    }

    public void setEntryNoteList(List<EntryNote> entryNoteList) {
        this.entryNoteList = entryNoteList;
    }
}
