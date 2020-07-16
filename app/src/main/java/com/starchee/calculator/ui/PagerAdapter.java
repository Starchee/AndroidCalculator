package com.starchee.calculator.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public PagerAdapter(@NonNull Fragment fragment, Fragment... fragments) {
        super(fragment);
        this.fragments.addAll(Arrays.asList(fragments));
    }

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity, Fragment... fragments) {
        super(fragmentActivity);
        this.fragments.addAll(Arrays.asList(fragments));
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
