package com.projects.aldajo92.notesgraph.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.SplashActivity;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.utils.PreferenceUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.projects.aldajo92.notesgraph.widget.GraphListWidgetService.GRAPHS_ENTRIES_KEY;

public class GraphWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

        RemoteViews views;

        Type type = new TypeToken<ArrayList<DataSetNoteModel>>(){}.getType();
        String rawString = PreferenceUtil.getString(context, GRAPHS_ENTRIES_KEY);
        ArrayList<DataSetNoteModel> list = new Gson().fromJson(rawString, type);

        if (!list.isEmpty()) {
            RemoteViews views12 = getRecipeListRemoteView(context);
            appWidgetManager.updateAppWidget(appWidgetId, views12);
        } else {
            views = getEmptyRemoteView(context);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private static RemoteViews getRecipeListRemoteView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list_view);
        String title = context.getString(R.string.title_dashboard);
        views.setTextViewText(R.id.title_text_view, title);

        Intent intent = new Intent(context, GraphListWidgetService.class);
//        String recipesJson = new Gson().toJson(dataSetNoteModels);
//        PreferenceUtil.saveString(context, GRAPHS_ENTRIES_KEY, recipesJson);
        views.setRemoteAdapter(R.id.widget_list_view, intent);

        Intent appIntent = new Intent(context, SplashActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_list_view, appPendingIntent);

        views.setEmptyView(R.id.widget_list_view, R.id.empty_view);
        return views;
    }

    private static RemoteViews getEmptyRemoteView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.empty_widget_view);

        Intent appIntent = new Intent(context, SplashActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.empty_widget_image_view, appPendingIntent);
        views.setOnClickPendingIntent(R.id.empty_widget_image_view, appPendingIntent);

        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }
}

