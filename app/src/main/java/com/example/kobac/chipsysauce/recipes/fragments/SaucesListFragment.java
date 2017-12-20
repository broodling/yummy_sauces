package com.example.kobac.chipsysauce.recipes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kobac.chipsysauce.R;
import com.example.kobac.chipsysauce.recipes.RecipesModel;

import java.util.ArrayList;

/**
 * Shows the list of supplied sauces.
 */
public class SaucesListFragment extends FragmentParent {

    public static SaucesListFragment getInstance(final ArrayList<RecipesModel> saucesList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Extras.SAUCES, saucesList);
        SaucesListFragment saucesListFragment = new SaucesListFragment();
        saucesListFragment.setArguments(bundle);

        return saucesListFragment;
    }

    @Override
    protected void show(Bundle bundle, View view) {

    }

    @Override
    public int defineLayout() {
        return R.layout.recycler_view;
    }

    /**
     * Holds the key for extras in Bundle.
     */
    private static final class Extras {

        public static final String SAUCES = "com.example.kobac.c";
    }
}
