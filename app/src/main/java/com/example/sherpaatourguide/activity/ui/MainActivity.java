package com.example.sherpaatourguide.activity.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.home.DashboardFragment;
import com.example.sherpaatourguide.home.ExploreFragment;
import com.example.sherpaatourguide.home.MapsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle drawerToggle;
    private AutoHideFAB addFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        LinearLayout drawerLinear = findViewById(R.id.drawerLinear);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        final BottomNavigationView navigationView = findViewById(R.id.navigationView);
//        addFab = findViewById(R.id.addFab);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        setSupportActionBar(toolbar);
        drawerToggle.setDrawerSlideAnimationEnabled(false);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DashboardFragment());
        adapter.addFragment(new ExploreFragment());

        adapter.addFragment(new MapsFragment());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.setSelectedItemId(R.id.navigation_dashboard);
                        break;
                    case 1:
                        navigationView.setSelectedItemId(R.id.navigation_explore);
                        break;
                    case 2:
                        navigationView.setSelectedItemId(R.id.navigation_notifications);
                        break;
                    case 3:
                        navigationView.setSelectedItemId(R.id.navigation_map);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_explore:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.navigation_map:
                        viewPager.setCurrentItem(3);
                        break;

                }
                return true;
            }
        });

        navigationView.setSelectedItemId(R.id.navigation_explore);


    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.map) {
            startActivity(new Intent(this, MapsFragment.class));
        }

        return super.onOptionsItemSelected(item) || drawerToggle.onOptionsItemSelected(item);
    }

    public AutoHideFAB getAddFab() {
        return addFab;
    }
}
