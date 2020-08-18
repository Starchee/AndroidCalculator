package com.starchee.calculator.ui;

import android.view.View;
import android.widget.ImageView;

import com.starchee.calculator.R;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class PadViewPagerTransformer implements ViewPager2.PageTransformer {

    private float offsetPadAdvanced = 0.0f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        final float pageWidth = page.getWidth();
        final ImageView arrowView = page.findViewById(R.id.arrow_pad);

        if (offsetPadAdvanced == 0 && (page.findViewById(R.id.divide_button)) != null) {
            offsetPadAdvanced =  page.findViewById(R.id.divide_button).getWidth();
        }

        if (position > 0.0f) {
            page.setTranslationX( (pageWidth - offsetPadAdvanced) *-position);
            arrowView.setRotationY(180 * position);
        } else if (position < 0.0f) {
            page.setTranslationX(pageWidth * -position);
            page.setAlpha(Math.max(1.0f + position, 0.0f));

        } else {
                page.setTranslationX(0.0f);
                page.setAlpha(1.0f);
        }
    }
}

