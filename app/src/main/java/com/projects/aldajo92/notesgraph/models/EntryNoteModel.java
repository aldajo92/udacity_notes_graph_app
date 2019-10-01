package com.projects.aldajo92.notesgraph.models;

import android.os.Parcel;
import android.os.Parcelable;

public class EntryNoteModel implements Parcelable {

    private long timestamp;

    private float value;

    private String description;

    private String urlPicture;

    public EntryNoteModel(){

    }

    public EntryNoteModel(long timestamp, float value, String description, String urlPicture) {
        this.timestamp = timestamp;
        this.value = value;
        this.description = description;
        this.urlPicture = urlPicture;
    }

    protected EntryNoteModel(Parcel in) {
        timestamp = in.readLong();
        value = in.readFloat();
        description = in.readString();
        urlPicture = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timestamp);
        dest.writeFloat(value);
        dest.writeString(description);
        dest.writeString(urlPicture);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EntryNoteModel> CREATOR = new Creator<EntryNoteModel>() {
        @Override
        public EntryNoteModel createFromParcel(Parcel in) {
            return new EntryNoteModel(in);
        }

        @Override
        public EntryNoteModel[] newArray(int size) {
            return new EntryNoteModel[size];
        }
    };

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
