package com.projects.aldajo92.notesgraph.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DataSetNoteModel implements Parcelable {

    private String title;
    private String description;
    private String units;
    private List<EntryNoteModel> entryNoteModelList;

    public DataSetNoteModel(String title, String description, String units, List<EntryNoteModel> entryNoteModelList) {
        this.title = title;
        this.description = description;
        this.units = units;
        this.entryNoteModelList = entryNoteModelList;
    }

    public DataSetNoteModel(String title, String description, List<EntryNoteModel> entryNoteModelList) {
        this.title = title;
        this.description = description;
        this.entryNoteModelList = entryNoteModelList;
    }

    protected DataSetNoteModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        units = in.readString();
        entryNoteModelList = in.createTypedArrayList(EntryNoteModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(units);
        dest.writeTypedList(entryNoteModelList);
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

    public List<EntryNoteModel> getEntryNoteModelList() {
        return entryNoteModelList;
    }

    public void setEntryNoteModelList(List<EntryNoteModel> entryNoteModelList) {
        this.entryNoteModelList = entryNoteModelList;
    }
}
