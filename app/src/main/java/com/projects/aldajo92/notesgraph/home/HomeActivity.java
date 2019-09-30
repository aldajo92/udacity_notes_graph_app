package com.projects.aldajo92.notesgraph.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.SplashActivity;
import com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity;
import com.projects.aldajo92.notesgraph.details.DetailGraphActivity;
import com.projects.aldajo92.notesgraph.home.adapter.CardDataListener;
import com.projects.aldajo92.notesgraph.home.dashboard.DashBoardFragment;
import com.projects.aldajo92.notesgraph.home.settings.SettingsFragment;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.models.EntryNoteModel;
import com.projects.aldajo92.notesgraph.views.ConfirmDeleteDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.EXTRA_NOTE_MODEL;
import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.EXTRA_POSITION;
import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.EXTRA_REQUEST_CODE;
import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.REQUEST_CREATE_GRAPH;
import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.REQUEST_EDIT_GRAPH;
import static com.projects.aldajo92.notesgraph.details.DetailGraphActivity.EXTRA_DETAIL_NOTE_MODEL;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CardDataListener {

    private DashBoardFragment dashBoardFragment;
    private DashBoardFragment favoritesFragment;
    private SettingsFragment settingsFragment;

    private BottomNavigationView bottomNavigationView;

    private FloatingActionButton fabButton;

    private Fragment fragmentActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dashBoardFragment = DashBoardFragment.createInstance();
        dashBoardFragment.setCardDataListener(this);

        favoritesFragment = DashBoardFragment.createInstance();
        favoritesFragment.setCardDataListener(this);

        settingsFragment = SettingsFragment.createInstance(this::signOut);

        fragmentActive = dashBoardFragment;

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fabButton = findViewById(R.id.fabButton);

        getSupportFragmentManager().beginTransaction().add(R.id.container, settingsFragment, "3").hide(settingsFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, favoritesFragment, "2").hide(favoritesFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, dashBoardFragment, "1").commit();

        fabButton.setOnClickListener(v -> openCreateGraph());

        List<EntryNoteModel> entryNoteModelList = new ArrayList<>();
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 12f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 14f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 15f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 16f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 17f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 18f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));
        entryNoteModelList.add(new EntryNoteModel(new Date().getTime(), 20f, "description", ""));

        List<DataSetNoteModel> list = new ArrayList<>();
        list.add(new DataSetNoteModel("Title Favorite", "description", entryNoteModelList));
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
        Intent intent = new Intent(this, EditCreateGraphActivity.class);
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

            case R.id.action_settings:
                getSupportFragmentManager().beginTransaction().hide(fragmentActive).show(settingsFragment).commit();
                fragmentActive = settingsFragment;
                return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                openCreateGraph();
                break;
            case R.id.action_about:
                showToast("about");
                break;
        }
        return super.onOptionsItemSelected(item);
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
            if (data != null) {
                DataSetNoteModel model = data.getParcelableExtra(EXTRA_NOTE_MODEL);
                if (resultCode == RESULT_OK) {
                    int position = data.getIntExtra(EXTRA_POSITION, -1);
                    if (fragmentActive instanceof DashBoardFragment) {
                        ((DashBoardFragment) fragmentActive).updateData(model, position);
                    }
                }
            }
        } else if (requestCode == REQUEST_CREATE_GRAPH) {
            if (data != null) {
                DataSetNoteModel model = data.getParcelableExtra(EXTRA_NOTE_MODEL);
                if (resultCode == RESULT_OK) {
                    insertItem(model);
                }
            }
        }
    }

    private void insertItem(DataSetNoteModel model) {
        if (fragmentActive instanceof DashBoardFragment) {
            ((DashBoardFragment) fragmentActive).addItem(model);
        }
    }

    private void deleteItem(DataSetNoteModel model, int position) {
        if (fragmentActive instanceof DashBoardFragment) {
            ((DashBoardFragment) fragmentActive).deleteItem(model, position);
        }
    }

    @Override
    public void onDelete(DataSetNoteModel dataSetNoteModel, int position) {
        ConfirmDeleteDialog dialog =
                ConfirmDeleteDialog.createInstance(
                        dataSetNoteModel,
                        position,
                        this::deleteItem
                );
        dialog.show(getSupportFragmentManager(), "name");
    }

    @Override
    public void onEdit(DataSetNoteModel dataSetNoteModel, int position) {
        Intent intent = new Intent(this, EditCreateGraphActivity.class);
        intent.putExtra(EXTRA_REQUEST_CODE, REQUEST_EDIT_GRAPH);
        intent.putExtra(EXTRA_NOTE_MODEL, dataSetNoteModel);
        intent.putExtra(EXTRA_POSITION, position);
        startActivityForResult(intent, REQUEST_EDIT_GRAPH);
    }

    @Override
    public void onClick(DataSetNoteModel dataSetNoteModel) {
        Intent intent = new Intent(this, DetailGraphActivity.class);
        intent.putExtra(EXTRA_DETAIL_NOTE_MODEL, dataSetNoteModel);
        startActivity(intent);
    }

    @Override
    public void onFavorite(DataSetNoteModel dataSetNoteModel, Boolean isChecked) {
        Toast.makeText(
                this,
                dataSetNoteModel.getTitle().concat(" " + isChecked),
                Toast.LENGTH_SHORT
        ).show();
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }
}
