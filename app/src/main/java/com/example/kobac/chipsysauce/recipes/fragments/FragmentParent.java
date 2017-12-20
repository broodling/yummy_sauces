package com.example.kobac.chipsysauce.recipes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kobac.chipsysauce.BaseActivity;
import com.example.kobac.chipsysauce.R;
import com.example.kobac.chipsysauce.recipes.RecipesListAdapter;
import com.example.kobac.chipsysauce.recipes.RecipesModel;

import java.util.ArrayList;

/**
 * Base for all fragments.
 *
 * @param <ACTIVITY> The supplied activity.
 */
public abstract class FragmentParent<ACTIVITY extends BaseActivity> extends Fragment {

    // @formatter:off
    private View mView;
    private boolean mDestroyed = false;
    private Bundle mSavedInstanceState;
    // @formatter:on

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDestroyed = true;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(defineLayout(), null);
        show(getArguments(), mView);

        return mView;
    }

    @Override
    public View getView() {
        final View view = super.getView();
        return view != null
                ? view
                : mView;
    }

    /**
     * Get application activity.
     *
     * @return The application activity.
     */
    @SuppressWarnings("unchecked")
    public ACTIVITY getAppActivity() {
        return (ACTIVITY) getActivity();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = getArguments() != null
                ? getArguments()
                : savedInstanceState;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        if (outState != null && mSavedInstanceState != null) {
            outState.putAll(mSavedInstanceState);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Ends the fragment.
     */
    protected void finish() {
        if (getFragmentManager() != null) {
            getFragmentManager().popBackStackImmediate();
        }
    }

    /**
     * Look for a child view with the given id. If this view has the given id,
     * return this view.
     *
     * @param id The id to search for.
     *
     * @return The view that has the given id in the hierarchy or null
     */
    protected View findViewById(final int id) {
        final View view = getView();

        if (view != null) {
            return getView().findViewById(id);
        } else {
            return null;
        }
    }

    /**
     * Checks if the fragment is destroyed or not.
     *
     * @return False if the fragment is not destroyed, true if it is destroyed.
     */
    public boolean isDestoyed() {
        return mDestroyed;
    }

    /**
     * Check if this fragment is still attached to its parent activity.
     *
     * @return True if the parent activity is not null.
     */
    public boolean isAttached() {
        return getActivity() != null;
    }

    /**
     * Define layout.
     *
     * @return The defined layout.
     */
    public abstract int defineLayout();

    /**
     * Triggered when the fragment view is initialized.
     */
    abstract protected void show(final Bundle bundle, final View view);

}
