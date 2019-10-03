package com.projects.aldajo92.notesgraph.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.projects.aldajo92.notesgraph.BaseActivity;
import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.SplashActivity;
import com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity;
import com.projects.aldajo92.notesgraph.details.DetailGraphActivity;
import com.projects.aldajo92.notesgraph.home.adapter.CardDataListener;
import com.projects.aldajo92.notesgraph.home.dashboard.DashBoardFragment;
import com.projects.aldajo92.notesgraph.home.settings.SettingsFragment;
import com.projects.aldajo92.notesgraph.models.DataSetNoteModel;
import com.projects.aldajo92.notesgraph.models.UserModel;
import com.projects.aldajo92.notesgraph.utils.Constants;
import com.projects.aldajo92.notesgraph.views.ConfirmDeleteDialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.EXTRA_NOTE_MODEL;
import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.EXTRA_POSITION;
import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.EXTRA_REQUEST_CODE;
import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.REQUEST_CREATE_GRAPH;
import static com.projects.aldajo92.notesgraph.create.EditCreateGraphActivity.REQUEST_EDIT_GRAPH;
import static com.projects.aldajo92.notesgraph.details.DetailGraphActivity.EXTRA_DETAIL_NOTE_MODEL;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CardDataListener {

    private DashBoardFragment dashBoardFragment, favoritesFragment;
    private SettingsFragment settingsFragment;

    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fabButton;

    private Fragment fragmentActive;

    private UserModel userModel;

    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeViewModel = new HomeViewModel();

        if (getIntent().hasExtra(Constants.USER_EXTRA)) {
            userModel = getIntent().getParcelableExtra(Constants.USER_EXTRA);
        }

        setupFragments();
        setupViews();

        homeViewModel.getLiveDataGraphs().observe(this, mapDataSetNoteModels -> {

            Collection<DataSetNoteModel> collection = mapDataSetNoteModels.values();
            List<DataSetNoteModel> models = new ArrayList<>(collection);
            Collections.sort(models, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));

            dashBoardFragment.setDataSetNoteModelList(models);

            List<DataSetNoteModel> favoritesList = new ArrayList<>();
            for(DataSetNoteModel noteModel: models){
                if(noteModel.getIsFavorite()){
                    favoritesList.add(noteModel);
                }
            }

            favoritesFragment.setDataSetNoteModelList(favoritesList);
        });


//        dashBoardFragment.setDataSetNoteModelList(list);
//        favoritesFragment.setDataSetNoteModelList(list1);
    }

    private void setupFragments(){
        dashBoardFragment = DashBoardFragment.createInstance();
        dashBoardFragment.setCardDataListener(this);

        favoritesFragment = DashBoardFragment.createInstance();
        favoritesFragment.setCardDataListener(this);

        settingsFragment = SettingsFragment.createInstance(userModel, this::signOut);

        fragmentActive = dashBoardFragment;

        getSupportFragmentManager().beginTransaction().add(R.id.container, settingsFragment, "3").hide(settingsFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, favoritesFragment, "2").hide(favoritesFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, dashBoardFragment, "1").commit();
    }

    private void setupViews(){
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fabButton = findViewById(R.id.fabButton);
        fabButton.setOnClickListener(v -> openCreateGraph());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_dashboard:
                showFragment(dashBoardFragment);
                return true;

            case R.id.action_favorites:
                showFragment(favoritesFragment);
                return true;

            case R.id.action_settings:
                showFragment(settingsFragment);
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
                    homeViewModel.editItem(model, position);
                }
            }
        } else if (requestCode == REQUEST_CREATE_GRAPH) {
            if (data != null) {
                DataSetNoteModel model = data.getParcelableExtra(EXTRA_NOTE_MODEL);
                if (resultCode == RESULT_OK) {
                    bottomNavigationView.setSelectedItemId(R.id.action_dashboard);
                    homeViewModel.addItem(model);
                }
            }
        }
    }

    @Override
    public void onDelete(DataSetNoteModel dataSetNoteModel, int position) {
        ConfirmDeleteDialog dialog =
                ConfirmDeleteDialog.createInstance(
                        dataSetNoteModel,
                        position,
                        homeViewModel::removeItem
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
    public void onFavorite(DataSetNoteModel model, int position, Boolean isChecked) {
        model.setIsFavorite(isChecked);
        homeViewModel.editItem(model, position);
    }

    private void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().hide(fragmentActive).show(fragment).commit();
        fragmentActive = fragment;
        boolean showFabButton = fragment instanceof DashBoardFragment;
        showFabButton(showFabButton);
    }

    private void openCreateGraph() {
        Intent intent = new Intent(this, EditCreateGraphActivity.class);
        startActivityForResult(intent, REQUEST_CREATE_GRAPH);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    private void showFabButton(boolean show) {
        if (show) {
            fabButton.show();
        } else {
            fabButton.hide();
        }
    }
}
