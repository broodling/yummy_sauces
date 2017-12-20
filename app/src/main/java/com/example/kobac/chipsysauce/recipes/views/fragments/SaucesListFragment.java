package com.example.kobac.chipsysauce.recipes.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kobac.chipsysauce.R;
import com.example.kobac.chipsysauce.recipes.RecipesModel;
import com.example.kobac.chipsysauce.recipes.views.RecipesListAdapter;

import java.util.ArrayList;

/**
 * Shows the list of supplied sauces.
 */
public class SaucesListFragment extends FragmentParent {

    private RecyclerView recyclerView;
    private RecipesListAdapter mAdapter;
    private LinearLayoutManager mLayotManager;

    public static SaucesListFragment newInstance(final ArrayList<RecipesModel> saucesList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Extras.SAUCES, saucesList);
        SaucesListFragment saucesListFragment = new SaucesListFragment();
        saucesListFragment.setArguments(bundle);

        return saucesListFragment;
    }

    @Override
    protected void show(Bundle bundle, View view) {
        ArrayList<RecipesModel> saucesList = (ArrayList<RecipesModel>) bundle.getSerializable(Extras.SAUCES);

        mLayotManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayotManager);
        mAdapter = new RecipesListAdapter(getContext(), saucesList);
        recyclerView.setAdapter(mAdapter);

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
