package com.projects.aldajo92.notesgraph.widget.service;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleService;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.widget.GraphWidgetProvider;
import com.projects.aldajo92.notesgraph.widget.WidgetType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        if (intent != null) {
            viewModel.configFBListener();
            viewModel.getLiveDataGraphs().observe(this, stringDataSetNoteModelHashMap -> {
                Collection<DataSetNoteModel> collection = stringDataSetNoteModelHashMap.values();
                List<DataSetNoteModel> listModels = new ArrayList<>(collection);
                Collections.sort(listModels, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));

                if (!listModels.isEmpty()) {
                    widgetType = WidgetType.GRAPH;
                    dataSetNoteModels = listModels;
                } else {
                    widgetType = WidgetType.NONE;
                }
                updateWidget();
            });
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWidget() {
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, GraphWidgetProvider.class));
        GraphWidgetProvider.updateRecipeWidgets(this, appWidgetManager, appWidgetIds, widgetType, dataSetNoteModels);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_view);
    }

    public static void startActionUpdateWidgets(@NonNull Context context) {
        Intent intent = new Intent(context, GraphWidgetService.class);
        context.startService(intent);
    }
}
