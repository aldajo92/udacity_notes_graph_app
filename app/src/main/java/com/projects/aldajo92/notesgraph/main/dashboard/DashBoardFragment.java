package com.projects.aldajo92.notesgraph.main.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.main.adapter.CardDataListener;
import com.projects.aldajo92.notesgraph.main.adapter.DataSetAdapter;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment {

    private RecyclerView recyclerView;

    private CardDataListener cardDataListener;

    private List<DataSetNoteModel> dataSetNoteModelList = new ArrayList<>();
    private DataSetAdapter adapter;

    public DashBoardFragment() {
        adapter = new DataSetAdapter(dataSetNoteModelList, cardDataListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView_dashboard);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setCardDataListener(CardDataListener cardDataListener) {
        this.cardDataListener = cardDataListener;
        adapter.setCardDataListener(cardDataListener);
    }

    public void setDataSetNoteModelList(List<DataSetNoteModel> dataSetNoteModelList) {
        this.dataSetNoteModelList = dataSetNoteModelList;
        adapter.setDataSetNoteModelList(dataSetNoteModelList);
    }

    public static DashBoardFragment createInstance() {
        return new DashBoardFragment();
    }

    public void updateData(DataSetNoteModel model, int position) {
        if (position >= 0){
            this.dataSetNoteModelList.set(position, model);
            adapter.updateItem(position, model);
        }
    }
}
