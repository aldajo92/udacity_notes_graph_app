package com.projects.aldajo92.notesgraph.main.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

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
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;
import com.projects.aldajo92.notesgraph.utils.CalendarUtils;
import com.projects.aldajo92.notesgraph.views.MyMarkerView;

import java.util.ArrayList;
import java.util.List;

public class CardDataViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewTitle;
    private TextView textViewDescription;
    private CardView cardView;
    private LineChart lineChart;

    private ImageButton buttonEdit;
    private ImageButton buttonDelete;
    private CheckBox checkFavorite;

    private CardDataListener listener;

    private DataSetNoteModel dataSetNoteModel;


    public CardDataViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.textView_title);
        textViewDescription = itemView.findViewById(R.id.textView_description);
        cardView = itemView.findViewById(R.id.cardView);
        lineChart = itemView.findViewById(R.id.lineaChart);

        buttonDelete = itemView.findViewById(R.id.button_delete);
        buttonEdit = itemView.findViewById(R.id.button_edit);
        checkFavorite = itemView.findViewById(R.id.imageView_favorite);

        buttonEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEdit(dataSetNoteModel, getAdapterPosition());
            }
        });

        buttonDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDelete(dataSetNoteModel);
            }
        });

        cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(dataSetNoteModel);
            }
        });

        checkFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onFavorite(dataSetNoteModel, isChecked);
            }
        });

        cardView.setElevation(0);
        initLinearData();
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


        MyMarkerView markerView = new MyMarkerView(itemView.getContext(), R.layout.view_marker);
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
                set1.setColor(ContextCompat.getColor(itemView.getContext(), R.color.clear_color));
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

    public void bindData(DataSetNoteModel data) {
        this.dataSetNoteModel = data;
        lineChart.clear();
        lineChart.invalidate();
        textViewTitle.setText(data.getTitle());
        textViewDescription.setText(data.getDescription());
        setLineData(data.getEntryNoteModelList());
    }

    public void bindListener(CardDataListener listener) {
        this.listener = listener;
    }
}
