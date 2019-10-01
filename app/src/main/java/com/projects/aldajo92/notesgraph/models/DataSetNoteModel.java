package com.projects.aldajo92.notesgraph.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DataSetNoteModel implements Parcelable {

    private String ID;
    private String title;
    private String description;
    private String units;
    private List<EntryNoteModel> entryNoteModelList;
    private Boolean isFavorite = false;
    private long date;

    public DataSetNoteModel() {
        this.ID = "";
        this.title = "";
        this.description = "";
        this.units = "";
        this.isFavorite = false;
        this.date = 0L;
    }

    public DataSetNoteModel(String ID, String title, String description, String units, List<EntryNoteModel> entryNoteModelList) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.units = units;
        this.entryNoteModelList = entryNoteModelList;
    }

    public DataSetNoteModel(String title, String description, String units, List<EntryNoteModel> entryNoteModelList) {
        this.ID = "";
        this.title = title;
        this.description = description;
        this.units = units;
        this.entryNoteModelList = entryNoteModelList;
    }

    public DataSetNoteModel(String title, String description, List<EntryNoteModel> entryNoteModelList) {
        this.ID = "";
        this.title = title;
        this.description = description;
        this.entryNoteModelList = entryNoteModelList;
    }

    protected DataSetNoteModel(Parcel in) {
        ID = in.readString();
        title = in.readString();
        description = in.readString();
        units = in.readString();
        entryNoteModelList = in.createTypedArrayList(EntryNoteModel.CREATOR);
        byte tmpIsFavorite = in.readByte();
        isFavorite = tmpIsFavorite == 0 ? null : tmpIsFavorite == 1;
        date = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(units);
        dest.writeTypedList(entryNoteModelList);
        dest.writeByte((byte) (isFavorite == null ? 0 : isFavorite ? 1 : 2));
        dest.writeLong(date);
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
