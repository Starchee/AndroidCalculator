package com.starchee.calculator.ui.main;

import android.os.Bundle;
import android.view.View;

import com.starchee.calculator.R;
import com.starchee.calculator.ui.MainViewPagerTransformer;
import com.starchee.calculator.ui.PagerAdapter;
import com.starchee.calculator.ui.display.DisplayFragment;
import com.starchee.calculator.ui.keypad.PadAdvancedFragment;
import com.starchee.calculator.ui.keypad.PadFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


public class MainActivity extends AppCompatActivity implements
        ViewPagerButtonListener,
        PadAdvancedFragment.PadAdvancedFragmentOnClickListener {

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private PadFragment padFragment;
    private DisplayFragment displayFragment;
    private ViewPagerButtonListener padViewPagerButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager_main);
        viewPager.setOffscreenPageLimit(2);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        padFragment = new PadFragment();
        displayFragment = new DisplayFragment();

        if (padFragment instanceof ViewPagerButtonListener) {
            padViewPagerButtonListener = padFragment;
        } else {
            throw new ClassCastException(padFragment.toString() + " must implement ViewPagerButtonListener");
        }

        pagerAdapter = new PagerAdapter(this, padFragment, displayFragment);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(pagerAdapter.getItemCount() - 1, false);
        viewPager.setPageTransformer(new MainViewPagerTransformer());
    }

    @Override
    public void arrowOnClickListener() {
        if (viewPager.getCurrentItem() == 1) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }
    }

    @Override
    public void padAdvanceArrowButtonOnClickListener() {
        padViewPagerButtonListener.arrowOnClickListener();
    }
}

