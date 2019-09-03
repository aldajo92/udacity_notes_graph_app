package com.projects.aldajo92.notesgraph.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.projects.aldajo92.notesgraph.R;
import com.projects.aldajo92.notesgraph.main.dashboard.DashBoardFragment;
import com.projects.aldajo92.notesgraph.main.favorite.FavoritesFragment;

public class HomeActivity extends AppCompatActivity {

    private FragmentPagerAdapter pagerAdapter;

    private DashBoardFragment dashBoardFragment;
    private FavoritesFragment favoritesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        dashBoardFragment = DashBoardFragment.createInstance();
        favoritesFragment = FavoritesFragment.createInstance();

        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return dashBoardFragment;
                    default:
                        return favoritesFragment;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                String title = getItem(position).getClass().getName();
                return title.subSequence(title.lastIndexOf(".") + 1, title.length());
            }
        };

        ViewPager viewPager = findViewById(R.id.viewPager_home);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout_home);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static String TAG = "adjgf";
}
