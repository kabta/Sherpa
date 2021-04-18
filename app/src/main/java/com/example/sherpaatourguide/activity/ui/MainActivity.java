package com.example.sherpaatourguide.activity.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.home.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle drawerToggle;
    private AutoHideFAB addFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        LinearLayout drawerLinear = findViewById(R.id.drawerLinear);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        final BottomNavigationView navigationView = findViewById(R.id.navigationView);
//        addFab = findViewById(R.id.addFab);





        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DashboardFragment());
        adapter.addFragment(new ExploreFragment());


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



                }
                return true;
            }
        });

        navigationView.setSelectedItemId(R.id.navigation_explore);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.lv_explore) {
            startActivity(new Intent(this, ExploreFragment.class));
        }

        return super.onOptionsItemSelected(item) ;
    }

    public AutoHideFAB getAddFab() {
        return addFab;
    }
}
