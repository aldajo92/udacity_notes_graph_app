package com.projects.aldajo92.notesgraph.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.utils.PreferenceUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GraphListWidgetService extends RemoteViewsService {

    public static final String GRAPHS_ENTRIES_KEY = "com.example.aldajo92.bakingApp.GRAPHS_KEY";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Type type = new TypeToken<ArrayList<DataSetNoteModel>>(){}.getType();
        String rawString = PreferenceUtil.getString(getApplicationContext(), GRAPHS_ENTRIES_KEY);
        ArrayList<DataSetNoteModel> list = new Gson().fromJson(rawString, type);
        return new GraphListRemoteViewsFactory(getApplicationContext(), list);
    }
}