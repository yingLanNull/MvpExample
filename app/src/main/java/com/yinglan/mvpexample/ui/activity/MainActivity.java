package com.yinglan.mvpexample.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.yinglan.mvpexample.R;
import com.yinglan.mvpexample.ui.fragment.DailyFragment;
import com.yinglan.mvpexample.ui.fragment.DouBanFragment;
import com.yinglan.mvpexample.ui.fragment.MovieFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fragment)
    FrameLayout fragment;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment, new DailyFragment(), "首页").commit();
        setupDrawerContent();
    }


    private void setupDrawerContent() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //Checking if the item is in checked state or not, if not make it in checked state
                        if (menuItem.isChecked())
                            menuItem.setChecked(false);
                        else
                            menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        Fragment fragment = fragmentManager.findFragmentByTag(menuItem.getTitle().toString());
                        if (null == fragment) {
                            switch (menuItem.getItemId()) {
                                case R.id.drawer_news:
                                    fragment = new DailyFragment();
                                    break;
                                case R.id.drawer_movie:
                                    fragment = new MovieFragment();
                                    break;
                                case R.id.drawer_img:
                                    fragment = new DouBanFragment();
                                    break;
                                default:
                                    return true;
                            }
                            fragmentManager.beginTransaction().add(R.id.fragment, fragment, menuItem.getTitle().toString()).commit();
                        } else {
                            fragmentManager.beginTransaction().replace(R.id.fragment, fragment, menuItem.getTitle().toString()).commit();
                        }
                        return true;
                    }
                });
    }
}
