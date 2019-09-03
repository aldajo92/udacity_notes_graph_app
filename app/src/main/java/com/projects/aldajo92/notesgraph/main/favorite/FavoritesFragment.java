package com.projects.aldajo92.notesgraph.main.favorite;

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
import com.projects.aldajo92.notesgraph.main.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.main.adapter.DataSetAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.recyclerView_favorites);
        showList();
    }

    public void showList() {
        List<DataSetNoteModel> list = new ArrayList<>();

        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Final", "description", new ArrayList<>()));

        recyclerView.setAdapter(new DataSetAdapter(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public static FavoritesFragment createInstance() {
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        return favoritesFragment;
    }
}
