package com.projects.aldajo92.notesgraph.main.dashboard;

import android.content.Context;
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

    private Context context;

    private RecyclerView recyclerView;

    private CardDataListener cardDataListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        context = view.getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<DataSetNoteModel> list = new ArrayList<>();

        list.add(new DataSetNoteModel("Title", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Final", "description", new ArrayList<>()));

        recyclerView = view.findViewById(R.id.recyclerView_dashboard);
        recyclerView.setAdapter(new DataSetAdapter(list, cardDataListener));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setCardDataListener(CardDataListener cardDataListener) {
        this.cardDataListener = cardDataListener;
    }

    public static DashBoardFragment createInstance() {
        return new DashBoardFragment();
    }

}
