package com.starchee.calculator;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class MainViewPagerTransformer implements ViewPager2.PageTransformer {

    private float offsetPadNumber = 0.0f;

    @Override
    public void transformPage(@NonNull View page, float position) {

        final float pageHeight = page.getHeight();
        final float offsetTextView = pageHeight - offsetPadNumber;
        final ImageView arrowView = page.findViewById(R.id.arrow_display);

        if (offsetPadNumber == 0 && (page.findViewById(R.id.PadNumberFragment)) != null) {
            offsetPadNumber = pageHeight - page.findViewById(R.id.PadNumberFragment).getHeight();
        }


        if (position < 0.0f) {
            page.setTranslationY(offsetPadNumber + pageHeight * -position);
        } else if (position > 0.0f) {
            page.setTranslationY(-offsetTextView - (pageHeight - offsetTextView) * position);
            arrowView.setRotationX(180 * -position);
        } else if (position == 0.0f) {
            if (page.findViewById(R.id.PadNumberFragment) != null) {
                page.setTranslationY(offsetPadNumber);
            } else {
                page.setTranslationY(-(pageHeight - offsetPadNumber));
                arrowView.setRotationX(0.0f);
            }
        }
    }
}
