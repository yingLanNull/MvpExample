package com.yinglan.mvpexample.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.yinglan.mvpexample.R;
import com.yinglan.mvpexample.domain.view.ExtendedViewPager;
import com.yinglan.mvpexample.ui.fragment.ImageViewerFragment;

import java.util.List;


public class ImageViewerActivity extends AppCompatActivity {

    private int currentIndex;
    private ExtendedViewPager mViewPager;
    private List<String> images;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewpager);
        mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager);

        currentIndex = getIntent().getIntExtra("CURRENT_INDEX", 0);
        images = getIntent().getStringArrayListExtra("IMAGES");

        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(currentIndex);
    }


    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Fragment getItem(int position) {
            return ImageViewerFragment.newInstance(
                    images.get(position));
        }

    }


}
