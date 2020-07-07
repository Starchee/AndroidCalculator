package com.starchee.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;


public class MainActivity extends FragmentActivity {

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private PadFragment padFragment;
    private DisplayFragment displayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager  = findViewById(R.id.pager_main);

        viewPager.setOffscreenPageLimit(2);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        padFragment = new PadFragment();
        displayFragment = new DisplayFragment();
        displayFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() == 1) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(1);
                }
            }
        });

        pagerAdapter = new PagerAdapter(this, padFragment, displayFragment);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(pagerAdapter.getItemCount()-1, false);
        viewPager.setPageTransformer(new MainViewPagerTransformer());

    }
}

