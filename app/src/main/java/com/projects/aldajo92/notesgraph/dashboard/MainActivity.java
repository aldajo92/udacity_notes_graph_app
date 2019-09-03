package com.projects.aldajo92.notesgraph.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.dashboard.main.DashBoardAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new DashBoardAdapter(list, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
