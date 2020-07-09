package com.starchee.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.starchee.calculator.model.Main;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;


public class MainActivity extends FragmentActivity implements
        PadFragment.MathPadOnClickListener,
        PadNumberFragment.DotButtonOnClickListener,
        PadNumberFragment.DeleteButtonOnClickListener,
        PadNumberFragment.DeleteButtonOnLongClickListener,
        PadOperationFragment.OperationPadOnClickListener,
        PadOperationFragment.EqualsOnClickListener {

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private PadFragment padFragment;
    private DisplayFragment displayFragment;


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
    public View.OnClickListener setMathButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDisplayExpression(view);
                displayFragment.setOperatorEnabled(true);
                if (displayFragment.isCalculateEnabled()){
                    setDisplayAnswer();
                }
            }
        };
    }

    @Override
    public View.OnClickListener setDeleteButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFragment.deleteExpressionToken();
            }
        };
    }

    @Override
    public View.OnClickListener setOperationButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!displayFragment.isOperatorEnabled()) {
                    displayFragment.deleteExpressionToken();
                }
                setDisplayExpression(view);
                displayFragment.setDotEnabled(true);
                displayFragment.setCalculateEnabled(true);
                displayFragment.setOperatorEnabled(false);
            }
        };
    }

    @Override
    public View.OnClickListener setDotButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (displayFragment.isDotEnabled()){
                    setDisplayExpression(view);
                    displayFragment.setDotEnabled(false);
                }
            }
        };
    }

    @Override
    public View.OnClickListener setEqualsButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFragment.setCalculateEnabled(false);
                displayFragment.clearExpression();
                displayFragment.setExpressionToken(displayFragment.getAnswer());
                displayFragment.clearAnswer();
            }
        };
    }

    private void setDisplayAnswer() {
        displayFragment.setAnswer(Main.calculate(displayFragment.getExpression()));
    }

    private void setDisplayExpression(View view) {
        displayFragment.setExpressionToken(((Button) view).getText().toString());
    }


    @Override
    public View.OnLongClickListener setDeleteButtonOnLongClickListener() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                displayFragment.clearDisplay();
                return true;
            }
        };
    }
}

