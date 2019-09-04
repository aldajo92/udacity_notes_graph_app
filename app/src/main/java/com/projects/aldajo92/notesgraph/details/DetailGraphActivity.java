package com.projects.aldajo92.notesgraph.details;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.details.adapter.EntriesAdapter;
import com.projects.aldajo92.notesgraph.details.adapter.EntryDataListener;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;
import com.projects.aldajo92.notesgraph.utils.CalendarUtils;
import com.projects.aldajo92.notesgraph.views.MyMarkerView;

import java.util.ArrayList;
import java.util.List;

public class DetailGraphActivity extends AppCompatActivity implements EntryDataListener {

    public static String EXTRA_DETAIL_NOTE_MODEL = "com.projects.aldajo92.extra_detail_note_model";

    private LineChart lineChart;
    private RecyclerView recyclerView;
    private EntriesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_graph);

        lineChart = findViewById(R.id.lineaChart_entries);
        recyclerView = findViewById(R.id.recyclerView_entries);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_DETAIL_NOTE_MODEL)){
            DataSetNoteModel model = intent.getParcelableExtra(EXTRA_DETAIL_NOTE_MODEL);

            adapter = new EntriesAdapter(model.getEntryNoteModelList(), this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            setTitle(model.getTitle());

            setLineData(model.getEntryNoteModelList());
        }

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        initLinearData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDelete(DataSetNoteModel dataSetNoteModel) {

    }

    @Override
    public void onEdit(DataSetNoteModel dataSetNoteModel, int position) {

    }

    @Override
    public void onClick(DataSetNoteModel dataSetNoteModel) {

    }

    @Override
    public void onFavorite(DataSetNoteModel dataSetNoteModel, Boolean isChecked) {

    }

    private void initLinearData() {
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);

        lineChart.setPinchZoom(false);

        lineChart.setScaleXEnabled(false);
        lineChart.setScaleYEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        YAxis axisLeft = lineChart.getAxisLeft();
        axisLeft.removeAllLimitLines();
        axisLeft.setDrawZeroLine(false);
        axisLeft.setDrawLimitLinesBehindData(false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(false);
        axisLeft.setEnabled(false);
        axisLeft.setAxisMinimum(-2f);

        YAxis axisRight = lineChart.getAxisRight();
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setEnabled(false);
        axisRight.setAxisMinimum(-2f);


        MyMarkerView markerView = new MyMarkerView(this, R.layout.view_marker);
        markerView.setChartView(lineChart);
        lineChart.setMarker(markerView);

    }

    private void setLineData(List<EntryNoteModel> incomesData) {
        if (incomesData != null) {
            List<Entry> linearEntryList = new ArrayList<>();
            for (int index = 0; index < incomesData.size(); index++) {
                EntryNoteModel entryNoteModel = incomesData.get(index);
                linearEntryList.add(new Entry(index, entryNoteModel.getValue()));
            }

            lineChart.getXAxis().setValueFormatter(new ValueFormatter() {
                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    if (incomesData.isEmpty()) {
                        return super.getAxisLabel(value, axis);
                    } else {
                        return CalendarUtils.timestampToCalendarString(
                                incomesData.get((int) value).getTimestamp(),
                                CalendarUtils.HEADER_FORMAT
                        );
                    }

                }
            });

            LineDataSet set1;

            if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
                set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
                set1.setValues(linearEntryList);
                lineChart.getData().notifyDataChanged();
                lineChart.notifyDataSetChanged();
            } else {
                set1 = new LineDataSet(linearEntryList, "DataSet 1");

                set1.setDrawIcons(false);
                set1.setColor(ContextCompat.getColor(this, R.color.clear_color));
                set1.setCircleColor(Color.WHITE);
                set1.setLineWidth(3f);
                set1.setCircleRadius(6f);
                set1.setCircleHoleRadius(4f);
                set1.setDrawCircleHole(true);
                set1.setCircleHoleColor(Color.BLACK);
                set1.setDrawValues(false);
                set1.setValueTextSize(9f);
                set1.setDrawFilled(false);
                set1.setFormLineWidth(1f);
                set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                set1.setFormSize(15f);

                set1.setFillColor(Color.BLACK);

                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);

                lineChart.setData(new LineData(dataSets));
            }
        }
    }
}
