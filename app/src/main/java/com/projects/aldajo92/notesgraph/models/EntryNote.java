package com.projects.aldajo92.notesgraph.models;

import android.os.Parcel;
import android.os.Parcelable;

public class EntryNote implements Parcelable {

    private long timestamp;

    private float value;

    private String description;

    private String urlPicture;

    public EntryNote(long timestamp, float value, String description, String urlPicture) {
        this.timestamp = timestamp;
        this.value = value;
        this.description = description;
        this.urlPicture = urlPicture;
    }

    protected EntryNote(Parcel in) {
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

    public static final Creator<EntryNote> CREATOR = new Creator<EntryNote>() {
        @Override
        public EntryNote createFromParcel(Parcel in) {
            return new EntryNote(in);
        }

        @Override
        public EntryNote[] newArray(int size) {
            return new EntryNote[size];
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
