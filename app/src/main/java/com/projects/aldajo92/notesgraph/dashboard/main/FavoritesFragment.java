package com.projects.aldajo92.notesgraph.dashboard.main;

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

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_favorites, container, false);
        this.view = view;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showList();
    }

    public void showList() {
        recyclerView = view.findViewById(R.id.recyclerView_favorites);
        List<String> list = new ArrayList<>();
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");

        recyclerView.setAdapter(new DashBoardAdapter(list, view.getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    public static FavoritesFragment createInstance() {
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        return favoritesFragment;
    }
}
