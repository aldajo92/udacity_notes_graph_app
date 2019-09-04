package com.projects.aldajo92.notesgraph.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
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
import com.projects.aldajo92.notesgraph.main.favorite.FavoritesFragment;
import com.projects.aldajo92.notesgraph.views.ConfirmDeleteDialog;

import static com.projects.aldajo92.notesgraph.create.CreateGraphActivity.REQUEST_CREATE_GRAPH;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CardDataListener {

    private DashBoardFragment dashBoardFragment;
    private FavoritesFragment favoritesFragment;

    private BottomNavigationView bottomNavigationView;

    private FloatingActionButton fabButton;

    private Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dashBoardFragment = DashBoardFragment.createInstance();
        favoritesFragment = FavoritesFragment.createInstance();
        dashBoardFragment.setCardDataListener(this);

        active = dashBoardFragment;

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fabButton = findViewById(R.id.fabButton);

        getSupportFragmentManager().beginTransaction().add(R.id.container, favoritesFragment, "2").hide(favoritesFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, dashBoardFragment, "1").commit();

        fabButton.setOnClickListener(v -> {
            openCreateGraph();
        });
    }

    private void openCreateGraph() {
        Intent intent = new Intent(this, CreateGraphActivity.class);
        startActivityForResult(intent, REQUEST_CREATE_GRAPH);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_dashboard:
                getSupportFragmentManager().beginTransaction().hide(active).show(dashBoardFragment).commit();
                active = dashBoardFragment;
                return true;

            case R.id.action_favorites:
                getSupportFragmentManager().beginTransaction().hide(active).show(favoritesFragment).commit();
                active = favoritesFragment;
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
        // Inflate the bottom_navigation_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void onDelete() {
        ConfirmDeleteDialog dialog = ConfirmDeleteDialog.createInstance(() -> Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show());
        dialog.show(getSupportFragmentManager(), "name");
    }

    @Override
    public void onEdit() {
        Toast.makeText(this, "Edit", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick() {
        Intent intent = new Intent(this, DetailGraphActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFavorite(Boolean isChecked) {
        Toast.makeText(this, "Favorite: " + isChecked, Toast.LENGTH_LONG).show();
    }
}
