package com.projects.aldajo92.notesgraph.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.main.dashboard.DashBoardFragment;
import com.projects.aldajo92.notesgraph.main.favorite.FavoritesFragment;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentPagerAdapter pagerAdapter;

    private DashBoardFragment dashBoardFragment;
    private FavoritesFragment favoritesFragment;

    private BottomNavigationView bottomNavigationView;

    private Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dashBoardFragment = DashBoardFragment.createInstance();
        favoritesFragment = FavoritesFragment.createInstance();

        active = dashBoardFragment;

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.container, favoritesFragment, "2").hide(favoritesFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, dashBoardFragment, "1").commit();
    }

    public static String TAG = "adjgf";

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
}
