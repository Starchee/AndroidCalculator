package com.starchee.calculator;

import android.os.Bundle;
import android.view.View;

import com.starchee.calculator.model.Main;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;


public class MainActivity extends FragmentActivity implements
        PadNumberFragment.PadNumberFragmentOnClickListener,
        PadOperationFragment.OperationPadOnClickListener,
        PadAdvancedFragment.PadAdvancedFragmentOnClickListener {

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private PadFragment padFragment;
    private DisplayFragment displayFragment;

    private void setDisplayAnswer() {
        displayFragment.setAnswer(Main.calculate(displayFragment.getExpression()));
    }

    private void setDisplayExpression(String text) {
        displayFragment.setExpressionToken(text);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager_main);

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
        viewPager.setCurrentItem(pagerAdapter.getItemCount() - 1, false);
        viewPager.setPageTransformer(new MainViewPagerTransformer());

    }

    @Override
    public void numberButtonOnClickListener(String text) {
        setDisplayExpression(text);
        displayFragment.setOperatorEnabled(true);
        if (displayFragment.isCalculateEnabled()) {
            setDisplayAnswer();
        }
    }

    @Override
    public void dotButtonOnClickListener(String text) {
        if (displayFragment.isDotEnabled()) {
            setDisplayExpression(text);
            displayFragment.setDotEnabled(false);
        }
    }

    @Override
    public void delButtonOnClickListener() {
        displayFragment.deleteExpressionToken();
    }

    @Override
    public void delButtonOnLongClickListener() {
        displayFragment.clearDisplay();
    }

    @Override
    public void clrButtonOnClickListener() {

    }

    @Override
    public void operationButtonOnClickListener(String text) {
        if (!displayFragment.isOperatorEnabled()) {
            displayFragment.deleteExpressionToken();
        }
        setDisplayExpression(text);
        displayFragment.setDotEnabled(true);
        displayFragment.setCalculateEnabled(true);
        displayFragment.setOperatorEnabled(false);
    }

    @Override
    public void equalsButtonOnClickListener() {
        displayFragment.setCalculateEnabled(false);
        displayFragment.clearExpression();
        displayFragment.setExpressionToken(displayFragment.getAnswer());
        displayFragment.clearAnswer();
    }

    @Override
    public void padAdvanceButtonOnClickListener(String text) {
        setDisplayExpression(text);
        displayFragment.setOperatorEnabled(true);
        if (displayFragment.isCalculateEnabled()) {
            setDisplayAnswer();
        }
    }
}

