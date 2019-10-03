package com.projects.aldajo92.notesgraph.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;

import java.util.List;

public class GraphListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private List<DataSetNoteModel> dataSetNoteModelList;

    public GraphListRemoteViewsFactory(Context applicationContext, List<DataSetNoteModel> dataSetNoteModelList) {
        this.context = applicationContext;
        this.dataSetNoteModelList = dataSetNoteModelList;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return dataSetNoteModelList != null ? dataSetNoteModelList.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        DataSetNoteModel dataSet = dataSetNoteModelList.get(position);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
        views.setTextViewText(R.id.textView_ingredient_summary, dataSet.getTitle());

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
