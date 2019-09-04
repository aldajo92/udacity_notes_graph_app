package com.projects.aldajo92.notesgraph.models;

public class EntryNote {

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
