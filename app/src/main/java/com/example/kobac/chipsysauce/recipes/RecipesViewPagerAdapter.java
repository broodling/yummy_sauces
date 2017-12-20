package com.example.kobac.chipsysauce.recipes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class RecipesViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private ArrayList<ArrayList<RecipesModel>> mSaucesCompleteList;

    public RecipesViewPagerAdapter(FragmentManager manager, final ArrayList<ArrayList<RecipesModel>> saucesCompleteList) {
        super(manager);
        mSaucesCompleteList = saucesCompleteList;
    }

    @Override
    public Fragment getItem(int position) {


        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}

