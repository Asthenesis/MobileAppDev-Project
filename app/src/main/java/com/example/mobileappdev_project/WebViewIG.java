package com.example.mobileappdev_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class WebViewIG extends AppCompatActivity {
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_ig);
        getSupportActionBar().hide();

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);}

        @Override
        public int getCount() { return 3;
        }
        public Fragment getItem(int position) {
            if (position == 0){
                return new FragmentIG();
            } else if (position == 1) {
                return new FragmentPoster();
            }  else {
                return new FragmentYoutube();
            }
        }




    }
}