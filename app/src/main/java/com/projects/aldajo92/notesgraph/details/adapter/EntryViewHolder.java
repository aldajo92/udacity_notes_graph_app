package com.projects.aldajo92.notesgraph.details.adapter;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;
import com.projects.aldajo92.notesgraph.utils.CalendarUtils;

import java.util.Locale;

import static com.projects.aldajo92.notesgraph.utils.CalendarUtils.DATE_LABEL_FORMAT;
import static com.projects.aldajo92.notesgraph.utils.CalendarUtils.DEFAULT_DATE_FORMAT;

public class EntryViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewDate;
    private TextView textViewValue;
    private TextView textViewDescription;
    private CardView cardView;

    private EntryDataListener listener;

    private EntryNoteModel entryNoteModel;

    private String units = "";


    public EntryViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewDate = itemView.findViewById(R.id.textView_date);
        textViewValue = itemView.findViewById(R.id.textView_value);
        textViewDescription = itemView.findViewById(R.id.textView_description);
        cardView = itemView.findViewById(R.id.cardView);

        cardView.setElevation(0);
        cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(entryNoteModel, getAdapterPosition());
            }
        });
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void bindData(EntryNoteModel data) {
        this.entryNoteModel = data;
        textViewDate.setText(CalendarUtils.timestampToCalendarString(data.getTimestamp(), DATE_LABEL_FORMAT));
        textViewValue.setText(String.format(Locale.getDefault(), "%.2f %s", data.getValue(), units));
        if (data.getDescription().isEmpty()) {
            textViewDescription.setVisibility(View.GONE);
        } else {
            textViewDescription.setVisibility(View.VISIBLE);
            textViewDescription.setText(data.getDescription());
        }
    }

    public void bindListener(EntryDataListener listener) {
        this.listener = listener;
    }
}
