package com.projects.aldajo92.notesgraph.details;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projects.aldajo92.notesgraph.BaseActivity;
import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.create.EditCreateEntryActivity;
import com.projects.aldajo92.notesgraph.details.adapter.EntriesAdapter;
import com.projects.aldajo92.notesgraph.details.adapter.EntryDataListener;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;
import com.projects.aldajo92.notesgraph.utils.CalendarUtils;
import com.projects.aldajo92.notesgraph.views.DetailEntryDialog;
import com.projects.aldajo92.notesgraph.views.MyMarkerView;

import java.util.ArrayList;
import java.util.List;

import static com.projects.aldajo92.notesgraph.create.EditCreateEntryActivity.EXTRA_ENTRY_MODEL;
import static com.projects.aldajo92.notesgraph.create.EditCreateEntryActivity.EXTRA_POSITION;
import static com.projects.aldajo92.notesgraph.create.EditCreateEntryActivity.EXTRA_REQUEST_CODE;
import static com.projects.aldajo92.notesgraph.create.EditCreateEntryActivity.REQUEST_CREATE_ENTRY;
import static com.projects.aldajo92.notesgraph.create.EditCreateEntryActivity.REQUEST_EDIT_ENTRY;
import static com.projects.aldajo92.notesgraph.create.EditCreateEntryActivity.RESULT_DELETE_ITEM;

public class DetailGraphActivity extends BaseActivity implements EntryDataListener {

    public static String EXTRA_DETAIL_NOTE_MODEL = "com.projects.aldajo92.extra_detail_note_model";

    private FloatingActionButton fabAddButton;
    private LineChart lineChart;
    private RecyclerView recyclerView;
    private EntriesAdapter adapter;
    private CheckBox favoriteCheckbox;

    private List<Entry> linearEntryList;
    private LineDataSet set1;
    private LineData lineData;

    private DetailGraphViewModel viewModel;
    private List<EntryNoteModel> incomesData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_graph);

        initViews();

        initDataSet();

        handleExtras();

        initLinearData();

        viewModel.getLiveDataListEntries().observe(this, this::setEntries);
    }

    private void initViews() {
        fabAddButton = findViewById(R.id.fabButton);
        lineChart = findViewById(R.id.lineaChart_entries);
        recyclerView = findViewById(R.id.recyclerView_entries);
        favoriteCheckbox = findViewById(R.id.checkBox_favorite);

        fabAddButton.setOnClickListener(v -> openCreateEntry());

        favoriteCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.favoriteSelected(isChecked));

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initDataSet() {
        set1 = new LineDataSet(linearEntryList, "DataSet");

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

    private void handleExtras() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_DETAIL_NOTE_MODEL)) {
            DataSetNoteModel model = intent.getParcelableExtra(EXTRA_DETAIL_NOTE_MODEL);
            viewModel = new DetailGraphViewModel(model);

            adapter = new EntriesAdapter(model.getEntryNoteModelList(), this);
            favoriteCheckbox.setChecked(model.getIsFavorite());
            recyclerView.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(linearLayoutManager);

            setTitle(model.getTitle());
            setEntries(model.getEntryNoteModelList());
        }
    }

    private void openCreateEntry() {
        Intent intent = new Intent(this, EditCreateEntryActivity.class);
        startActivityForResult(intent, REQUEST_CREATE_ENTRY);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initLinearData() {
        lineChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                String result = "";
                if (value > 0 || value < incomesData.size()) {
                    result = CalendarUtils.timestampToCalendarString(
                            incomesData.get((int) value).getTimestamp(),
                            CalendarUtils.HEADER_FORMAT
                    );
                }
                return result;

            }
        });

        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);

        lineChart.setPinchZoom(false);

//        lineChart.setScaleXEnabled(false);
//        lineChart.setScaleYEnabled(false);

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

    private void setEntries(List<EntryNoteModel> incomesData) {
        this.incomesData = incomesData;
        if (incomesData != null && !incomesData.isEmpty()) {
            linearEntryList = new ArrayList<>();
            int entriesSize = incomesData.size();
            for (int index = 0; index < entriesSize; index++) {
                EntryNoteModel entryNoteModel = incomesData.get(index);
                linearEntryList.add(new Entry(index, entryNoteModel.getValue()));
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            lineChart.setData(new LineData(dataSets));

            if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
                set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
                set1.setValues(linearEntryList);
                lineChart.getData().notifyDataChanged();
            }
        } else {
            lineChart.clear();
        }

        adapter.addItems(incomesData);
        adapter.setUnits(viewModel.getModel().getUnits());
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        recyclerView.smoothScrollToPosition(adapter.getItemCount());
    }

    @Override
    public void onClick(EntryNoteModel entryNoteModel, int position) {
        DetailEntryDialog dialog = DetailEntryDialog.createInstance(entryNoteModel, position, viewModel.getModel().getUnits(), this::openEdit);
        dialog.show(getSupportFragmentManager(), "name");
    }

    private void openEdit(EntryNoteModel entryNoteModel, int position) {
        Intent intent = new Intent(this, EditCreateEntryActivity.class);
        intent.putExtra(EXTRA_ENTRY_MODEL, entryNoteModel);
        intent.putExtra(EXTRA_REQUEST_CODE, REQUEST_EDIT_ENTRY);
        intent.putExtra(EXTRA_POSITION, position);
        startActivityForResult(intent, REQUEST_EDIT_ENTRY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT_ENTRY) {
            if (data != null) {
                EntryNoteModel model = data.getParcelableExtra(EXTRA_ENTRY_MODEL);
                int position = data.getIntExtra(EXTRA_POSITION, -1);
                if (resultCode == RESULT_OK) {
                    viewModel.updateItem(position, model);
                    adapter.updateItem(position, model);
                } else if (resultCode == RESULT_DELETE_ITEM) {
                    viewModel.deleteItem(position, model);
                }
            }
        } else if (requestCode == REQUEST_CREATE_ENTRY) {
            if (data != null) {
                EntryNoteModel model = data.getParcelableExtra(EXTRA_ENTRY_MODEL);
                if (resultCode == RESULT_OK) {
                    viewModel.addItem(model);
                }
            }
        }
    }

    // TODO: Implement this method, once add single item from firebase is added instead list
    private void addEntry(EntryNoteModel model) {
        adapter.addItem(model);
        int size = 0;
        if (linearEntryList != null) {
            size = linearEntryList.size();
        }
        set1.addEntry(new Entry(size, model.getValue()));
        lineChart.getData().notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        recyclerView.smoothScrollToPosition(adapter.getItemCount());
    }
}
