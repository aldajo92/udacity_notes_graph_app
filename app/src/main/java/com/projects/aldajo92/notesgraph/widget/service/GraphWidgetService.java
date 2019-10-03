package com.projects.aldajo92.notesgraph.widget.service;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.utils.PreferenceUtil;
import com.projects.aldajo92.notesgraph.widget.GraphWidgetProvider;
import com.projects.aldajo92.notesgraph.widget.WidgetType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.projects.aldajo92.notesgraph.widget.GraphListWidgetService.GRAPHS_ENTRIES_KEY;

public class GraphWidgetService extends LifecycleService {

    private GraphWidgetViewModel viewModel;
    private AppWidgetManager appWidgetManager;

    private WidgetType widgetType;
    private List<DataSetNoteModel> dataSetNoteModels;

    @Override
    public void onCreate() {
        super.onCreate();
        viewModel = new GraphWidgetViewModel(getApplication());
        appWidgetManager = AppWidgetManager.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            viewModel.configFBListener();

            Type type = new TypeToken<ArrayList<DataSetNoteModel>>(){}.getType();
            String rawString = PreferenceUtil.getString(getApplicationContext(), GRAPHS_ENTRIES_KEY);
            ArrayList<DataSetNoteModel> list = new Gson().fromJson(rawString, type);

            if (!list.isEmpty()) {
                widgetType = WidgetType.GRAPH;
                dataSetNoteModels = list;
            } else {
                widgetType = WidgetType.NONE;
            }
            updateWidget();

        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWidget() {
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, GraphWidgetProvider.class));
        GraphWidgetProvider.updateRecipeWidgets(this, appWidgetManager, appWidgetIds);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_view);
    }

    public static void startActionUpdateWidgets(@NonNull Context context) {
        Intent intent = new Intent(context, GraphWidgetService.class);
        context.startService(intent);
    }
}
