package com.starchee.calculator.ui.main;

import android.os.Bundle;
import android.view.View;

import com.starchee.calculator.R;
import com.starchee.calculator.ui.MainViewPagerTransformer;
import com.starchee.calculator.ui.PagerAdapter;
import com.starchee.calculator.ui.display.DisplayFragment;
import com.starchee.calculator.ui.keypad.PadAdvancedFragment;
import com.starchee.calculator.ui.keypad.PadFragment;
import com.starchee.calculator.ui.keypad.PadNumberFragment;
import com.starchee.calculator.ui.keypad.PadOperationFragment;
import com.starchee.calculator.viewModels.HistoryViewModel;

import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;


public class MainActivity extends FragmentActivity implements
        DisplayFragment.DisplayFragmentOnClickListener,
        PadNumberFragment.PadNumberFragmentOnClickListener,
        PadOperationFragment.OperationPadOnClickListener,
        PadAdvancedFragment.PadAdvancedFragmentOnClickListener,
        PadFragment.PadFragmentListener {

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private PadFragment padFragment;
    private DisplayFragment displayFragment;
    private MainActivityPadListener mainActivityPadListener;
    private MainActivityPadOperationListener mainActivityPadOperationListener;
    private MainActivityArrowButtonListener mainActivityArrowButtonListener;
    private List<String> dates;
    private HistoryViewModel historyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");

        viewPager = findViewById(R.id.pager_main);
        viewPager.setOffscreenPageLimit(2);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        padFragment = new PadFragment();
        displayFragment = new DisplayFragment();

        if (padFragment instanceof MainActivityArrowButtonListener) {
            mainActivityArrowButtonListener = padFragment;
        } else {
            throw new ClassCastException(padFragment.toString() + " must implement MainActivity.MainActivityArrowButtonListener");
        }

        if (displayFragment instanceof MainActivityPadListener) {
            mainActivityPadListener = displayFragment;
        } else {
            throw new ClassCastException(displayFragment.toString() + " must implement MainActivity.MainActivityPadListener");
        }

        pagerAdapter = new PagerAdapter(this, padFragment, displayFragment);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(pagerAdapter.getItemCount() - 1, false);
        viewPager.setPageTransformer(new MainViewPagerTransformer());

    }

    @Override
    public void arrowButtonOnClickListener() {
        if (viewPager.getCurrentItem() == 1) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }
    }


    @Override
    public void numberButtonOnClickListener(String text) {
        mainActivityPadListener.setOperandInExpression(text);
    }

    @Override
    public void dotButtonOnClickListener(String text) {
        mainActivityPadListener.setDotInExpression(text);
    }

    @Override
    public void delButtonOnClickListener() {
        mainActivityPadListener.deleteExpressionToken();
    }

    @Override
    public void delButtonOnLongClickListener() {
        mainActivityPadListener.clearDisplay();
    }

    @Override
    public void clrButtonOnClickListener() {
        mainActivityPadListener.clearDisplay();
    }

    @Override
    public void operationButtonOnClickListener(String text) {
        mainActivityPadListener.setOperatorInExpression(text);
    }

    @Override
    public void equalsButtonOnClickListener() {
        mainActivityPadListener.setAnswer();
        mainActivityPadOperationListener.setClrButtonVisible();
    }

    @Override
    public void padAdvanceButtonOnClickListener(String text) {
        mainActivityPadListener.setBracketInExpression(text);
    }

    @Override
    public void padAdvanceArrowButtonOnClickListener() {
        mainActivityArrowButtonListener.arrowButtonOnClickListener();
    }

    @Override
    public void onAttachPadNumberFragmentListener(PadNumberFragment padNumberFragment) {
        if (padNumberFragment instanceof MainActivityPadOperationListener) {
            mainActivityPadOperationListener = padNumberFragment;
        } else {
            throw new ClassCastException(displayFragment.toString() + " must implement MainActivity.MainActivityPadOperationListener");

        }
    }
}

