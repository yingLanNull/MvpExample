package com.yinglan.mvpexample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yinglan.mvpexample.R;
import com.yinglan.mvpexample.ui.activity.MainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/10/19
 */

public class DailyFragment extends Fragment {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, null);
        ((AppCompatActivity)getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        initData(view);
        return view;
    }

    public void initData(View view) {
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(7);
        MyPagerAdapter adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Bundle bundle = new Bundle();
            Fragment newFragment = DailyListFragment.newInstance();

            Calendar dateToGetUrl = Calendar.getInstance();
            dateToGetUrl.add(Calendar.DAY_OF_YEAR, 1 - i);
            int date = Integer.parseInt(simpleDateFormat.format(dateToGetUrl.getTime()));

            bundle.putBoolean("first_page?", i == 0);
            bundle.putBoolean("single?", false);
            bundle.putInt("DATE", date);

            newFragment.setArguments(bundle);
            return newFragment;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Calendar displayDate = Calendar.getInstance();
            displayDate.add(Calendar.DAY_OF_YEAR, -position);

            return DateFormat.getDateInstance().format(displayDate.getTime());
        }
    }
}
