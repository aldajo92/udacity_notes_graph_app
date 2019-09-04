package com.projects.aldajo92.notesgraph.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.create.CreateGraphActivity;
import com.projects.aldajo92.notesgraph.details.DetailGraphActivity;
import com.projects.aldajo92.notesgraph.main.adapter.CardDataListener;
import com.projects.aldajo92.notesgraph.main.dashboard.DashBoardFragment;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.models.EntryNote;
import com.projects.aldajo92.notesgraph.views.ConfirmDeleteDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.projects.aldajo92.notesgraph.create.CreateGraphActivity.EXTRA_NOTE_MODEL;
import static com.projects.aldajo92.notesgraph.create.CreateGraphActivity.EXTRA_POSITION;
import static com.projects.aldajo92.notesgraph.create.CreateGraphActivity.EXTRA_REQUEST_CODE;
import static com.projects.aldajo92.notesgraph.create.CreateGraphActivity.REQUEST_CREATE_GRAPH;
import static com.projects.aldajo92.notesgraph.create.CreateGraphActivity.REQUEST_EDIT_GRAPH;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CardDataListener {

    private DashBoardFragment dashBoardFragment;
    private DashBoardFragment favoritesFragment;

    private BottomNavigationView bottomNavigationView;

    private FloatingActionButton fabButton;

    private DashBoardFragment fragmentActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dashBoardFragment = DashBoardFragment.createInstance();
        dashBoardFragment.setCardDataListener(this);

        favoritesFragment = DashBoardFragment.createInstance();
        favoritesFragment.setCardDataListener(this);

        fragmentActive = dashBoardFragment;

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fabButton = findViewById(R.id.fabButton);

        getSupportFragmentManager().beginTransaction().add(R.id.container, favoritesFragment, "2").hide(favoritesFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, dashBoardFragment, "1").commit();

        fabButton.setOnClickListener(v -> {
            openCreateGraph();
        });

        List<EntryNote> entryNoteList = new ArrayList<>();
        entryNoteList.add(new EntryNote(new Date().getTime(), 12f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 14f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 15f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 16f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 17f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 18f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));
        entryNoteList.add(new EntryNote(new Date().getTime(), 20f, "description", ""));

        List<DataSetNoteModel> list = new ArrayList<>();
        list.add(new DataSetNoteModel("Title Favorite", "description", entryNoteList));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list.add(new DataSetNoteModel("Title Final", "description", new ArrayList<>()));
        dashBoardFragment.setDataSetNoteModelList(list);


        List<DataSetNoteModel> list1 = new ArrayList<>();
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        list1.add(new DataSetNoteModel("Title Favorite", "description", new ArrayList<>()));
        favoritesFragment.setDataSetNoteModelList(list1);
    }

    private void openCreateGraph() {
        Intent intent = new Intent(this, CreateGraphActivity.class);
        startActivityForResult(intent, REQUEST_CREATE_GRAPH);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_dashboard:
                getSupportFragmentManager().beginTransaction().hide(fragmentActive).show(dashBoardFragment).commit();
                fragmentActive = dashBoardFragment;
                return true;

            case R.id.action_favorites:
                getSupportFragmentManager().beginTransaction().hide(fragmentActive).show(favoritesFragment).commit();
                fragmentActive = favoritesFragment;
                return true;
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        getSupportFragmentManager().beginTransaction().remove(favoritesFragment).commit();
        getSupportFragmentManager().beginTransaction().remove(dashBoardFragment).commit();
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT_GRAPH) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    DataSetNoteModel model = data.getParcelableExtra(EXTRA_NOTE_MODEL);
                    int position = data.getIntExtra(EXTRA_POSITION, -1);
                    fragmentActive.updateData(model, position);
                }
            }
        }
    }

    @Override
    public void onDelete(DataSetNoteModel dataSetNoteModel) {
        ConfirmDeleteDialog dialog = ConfirmDeleteDialog.createInstance(() -> Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show());
        dialog.show(getSupportFragmentManager(), "name");
    }

    @Override
    public void onEdit(DataSetNoteModel dataSetNoteModel, int position) {
        Intent intent = new Intent(this, CreateGraphActivity.class);
        intent.putExtra(EXTRA_REQUEST_CODE, REQUEST_EDIT_GRAPH);
        intent.putExtra(EXTRA_NOTE_MODEL, dataSetNoteModel);
        intent.putExtra(EXTRA_POSITION, position);
        startActivityForResult(intent, REQUEST_EDIT_GRAPH);
    }

    @Override
    public void onClick(DataSetNoteModel dataSetNoteModel) {
        Intent intent = new Intent(this, DetailGraphActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFavorite(DataSetNoteModel dataSetNoteModel, Boolean isChecked) {
        Toast.makeText(this, "Favorite: " + isChecked, Toast.LENGTH_LONG).show();
    }
}
