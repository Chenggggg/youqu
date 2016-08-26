package com.lssdjt.chenggggg.lssdjt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.lssdjt.chenggggg.lssdjt.fragment.TextJokeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Toolbar mToolbar;
    private ViewPager mViewpager;
    private NavigationView mNavView;
    private FloatingActionButton mFAB;
    private ArrayList<Fragment> mFragments;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();

        mViewpager.setAdapter(new mAdapter(getSupportFragmentManager()));

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_newscenter:
                        Snackbar.make(mNavView,"newscenter",Snackbar.LENGTH_SHORT).show();
                        mViewpager.setCurrentItem(0);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_interested:
                        Snackbar.make(mNavView,"nav_interested",Snackbar.LENGTH_SHORT).show();
                        mViewpager.setCurrentItem(1);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_picshop:
                        Snackbar.make(mNavView,"nav_picshop",Snackbar.LENGTH_SHORT).show();
                        mViewpager.setCurrentItem(2);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_wechat:
                        Snackbar.make(mNavView,"nav_wechat",Snackbar.LENGTH_SHORT).show();
                        mViewpager.setCurrentItem(3);
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }



    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewpager = (ViewPager) findViewById(R.id.vp_main);
        mNavView = (NavigationView)findViewById(R.id.nav_view);
        mFAB = (FloatingActionButton) findViewById(R.id.fab_main);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mFragments.add(new TextJokeFragment());
        }
    }

    private class mAdapter extends FragmentPagerAdapter{

        public mAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}

