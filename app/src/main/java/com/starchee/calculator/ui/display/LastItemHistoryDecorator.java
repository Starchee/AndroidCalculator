package com.starchee.calculator.ui.display;

import android.graphics.Rect;
import android.view.View;

import com.starchee.calculator.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LastItemHistoryDecorator extends RecyclerView.ItemDecoration {

    private ChildRecyclerDecorator childRecyclerDecorator;

    public LastItemHistoryDecorator() {
        childRecyclerDecorator = new ChildRecyclerDecorator();
    }

    public void setPadding(int padding) {
        childRecyclerDecorator.setPaddingTop(padding);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
         RecyclerView childRecyclerView = view.findViewById(R.id.childRecyclerView);
        if (childRecyclerView.getItemDecorationCount() > 0){
            childRecyclerView.removeItemDecorationAt(0);
        }
        int parentPosition = parent.getChildAdapterPosition(view);

        if (parentPosition == parent.getAdapter().getItemCount() - 1) {

            childRecyclerView.addItemDecoration(childRecyclerDecorator, 0);

        } else {
            childRecyclerView.addItemDecoration(new DefaultItemDecorator());
        }
    }


    private static class ChildRecyclerDecorator extends RecyclerView.ItemDecoration{
        private int paddingTop;

        public void setPaddingTop(int paddingTop) {
            this.paddingTop = paddingTop;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

            int parentPosition = parent.getChildAdapterPosition(view);

            if (parentPosition == parent.getAdapter().getItemCount() - 1) {
                outRect.top = paddingTop;
            } else {
                outRect.top = 0;
            }

        }
    }

    private static class DefaultItemDecorator extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

            outRect.top = 0;
            }
        }

}
