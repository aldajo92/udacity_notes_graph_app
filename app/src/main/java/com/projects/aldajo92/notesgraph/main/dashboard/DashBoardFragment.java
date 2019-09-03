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
import com.projects.aldajo92.notesgraph.main.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.main.adapter.DataSetAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_dashboard, container, false);
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
        list.add(new DataSetNoteModel("Title", "description", new ArrayList<>()));

        recyclerView = getActivity().findViewById(R.id.recyclerView_dashboard);
        recyclerView.setAdapter(new DataSetAdapter(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public static DashBoardFragment createInstance() {
        DashBoardFragment dashBoardFragment = new DashBoardFragment();
        return dashBoardFragment;
    }
}
