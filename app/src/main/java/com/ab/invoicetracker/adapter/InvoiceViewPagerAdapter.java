package com.ab.invoicetracker.adapter;


import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InvoiceViewPagerAdapter extends FragmentPagerAdapter  {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitle = new ArrayList<>();

    public InvoiceViewPagerAdapter(FragmentManager manager, Activity mContext) {
        super(manager);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitle.add(title);
    }

//    public void getFragment(Fragment fragment, String title) {
//        mFragmentList.get(fragment);
//    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }

}