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
import android.widget.Toast;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.main.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.main.adapter.CardDataListener;
import com.projects.aldajo92.notesgraph.main.adapter.DataSetAdapter;
import com.projects.aldajo92.notesgraph.views.ConfirmDeleteDialog;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment implements CardDataListener {

    private Context context;

    private RecyclerView recyclerView;

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
        recyclerView.setAdapter(new DataSetAdapter(list, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public static DashBoardFragment createInstance() {
        return new DashBoardFragment();
    }

    @Override
    public void onDelete() {
        ConfirmDeleteDialog dialog = ConfirmDeleteDialog.createInstance(() -> Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show());
        dialog.show(getActivity().getSupportFragmentManager(), "name");
    }

    @Override
    public void onEdit() {
        Toast.makeText(context, "Edit", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick() {
        Toast.makeText(context, "Click", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFavorite() {
        Toast.makeText(context, "Favorite", Toast.LENGTH_LONG).show();
    }
}
