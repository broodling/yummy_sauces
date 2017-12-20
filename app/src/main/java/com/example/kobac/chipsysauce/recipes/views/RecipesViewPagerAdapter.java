package com.example.kobac.chipsysauce.recipes.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kobac.chipsysauce.recipes.RecipesModel;
import com.example.kobac.chipsysauce.recipes.views.fragments.SaucesListFragment;

import java.util.ArrayList;

public class RecipesViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ArrayList<RecipesModel>> mSaucesCompleteList;

    public RecipesViewPagerAdapter(FragmentManager manager, final ArrayList<ArrayList<RecipesModel>> saucesCompleteList) {
        super(manager);
        mSaucesCompleteList = saucesCompleteList;
    }

    @Override
    public Fragment getItem(int position) {
        return SaucesListFragment.newInstance(mSaucesCompleteList.get(position));
    }

    @Override
    public int getCount() {
        return mSaucesCompleteList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}

