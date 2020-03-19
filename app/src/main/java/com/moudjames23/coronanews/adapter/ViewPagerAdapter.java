package com.moudjames23.coronanews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Moudjames23 on 10/18/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> listFragment = new LinkedList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }



    public void addFragment(Fragment fragment)
    {
        listFragment.add(fragment);
    }
}
