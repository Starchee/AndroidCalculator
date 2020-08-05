package com.starchee.calculator.ui;

import android.view.View;
import android.widget.ImageView;

import com.starchee.calculator.R;
import com.starchee.calculator.ui.display.LastItemHistoryDecorator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class MainViewPagerTransformer implements ViewPager2.PageTransformer {

    private float offsetPadNumber = 0.0f;
    final LastItemHistoryDecorator itemDecoration;

    public MainViewPagerTransformer() {
        itemDecoration = new LastItemHistoryDecorator();
    }

    @Override
    public void transformPage(@NonNull View page, float position) {

        final float pageHeight = page.getHeight();
        float offsetDisplay = pageHeight - offsetPadNumber;
        final ImageView arrowView = page.findViewById(R.id.arrow_display);
        final RecyclerView recyclerView = page.findViewById(R.id.recyclerView);

        if (recyclerView != null) {
            if (recyclerView.getItemDecorationCount() > 0) {
                recyclerView.removeItemDecoration(itemDecoration);
            }
            recyclerView.addItemDecoration(itemDecoration);
        }


        if (offsetPadNumber == 0 && (page.findViewById(R.id.PadNumberFragment)) != null) {
            offsetPadNumber = pageHeight - page.findViewById(R.id.PadNumberFragment).getHeight();
        }

        if (position < 0.0f) {
            page.setTranslationY(offsetPadNumber + pageHeight * -position);
        } else if (position > 0.0f) {
            page.setTranslationY(-offsetDisplay - (pageHeight - offsetDisplay) * position);
            itemDecoration.setPadding((int) (offsetDisplay - offsetDisplay * position));
            arrowView.setRotationX(180 * -position);
        } else if (position == 0.0f) {
            if (page.findViewById(R.id.PadNumberFragment) != null) {
                page.setTranslationY(offsetPadNumber);
            } else {
                page.setTranslationY(-offsetDisplay);
                itemDecoration.setPadding((int) offsetDisplay);
                arrowView.setRotationX(0.0f);
            }
        }
    }
}
