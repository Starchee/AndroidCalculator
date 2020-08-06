package com.starchee.calculator.ui.keypad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starchee.calculator.R;
import com.starchee.calculator.ui.main.ViewPagerButtonListener;
import com.starchee.calculator.ui.PadViewPagerTransformer;
import com.starchee.calculator.ui.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class PadFragment extends Fragment implements ViewPagerButtonListener {

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private PadOperationFragment padOperationFragment;
    private PadAdvancedFragment padAdvancedFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pad_fragment, container, false);
        viewPager  = rootView.findViewById(R.id.pager_pad);
        viewPager.setOffscreenPageLimit(2);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        padOperationFragment = new PadOperationFragment();
        padAdvancedFragment = new PadAdvancedFragment();


        pagerAdapter = new PagerAdapter(this, padOperationFragment, padAdvancedFragment);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new PadViewPagerTransformer());

        return rootView;
    }

    @Override
    public void arrowOnClickListener() {
                if (viewPager.getCurrentItem() == 1) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(1);
                }
    }
}
