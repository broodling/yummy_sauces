package com.example.kobac.chipsysauce.recipes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base for all fragments.
 */
public abstract class FragmentParent extends Fragment {

    // @formatter:off
    private View mView;
    // @formatter:on

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
