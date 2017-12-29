package com.example.kobac.chipsysauce.recipes.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kobac.chipsysauce.R;
import com.example.kobac.chipsysauce.recipes.RecipesActivity;

/**
 * Shows the tab.
 */
public class TabView extends LinearLayout {

    // private Callback mCallback;

    private ViewPager mViewPager;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOrientation(HORIZONTAL);

        for (final RecipesActivity.Sauces sauces : RecipesActivity.Sauces.values()) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.weight = 1;

            // Create tab iconActive
            final ViewGroup tabView = (ViewGroup) View.inflate(getContext(), R.layout.z__item_tab, null);
            final ImageView imgIcon = tabView.findViewById(R.id.img_icon);

            imgIcon.setImageResource(sauces.iconInactive);

            // Button: Tab
            imgIcon.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tabPosition = sauces.ordinal();

                    // Set the current page
                    mViewPager.setCurrentItem(tabPosition);

                    setCurrentTab(tabPosition);
                }
            });

            // Show tab
            addView(tabView, params);
        }

        // Default tab
        setCurrentTab(0);
    }



    /**
     * Set the current tab state.
     *
     * @param position The position of the clicked tab.
     */
    private void setCurrentTab(final int position) {
        for (int i = 0; i < getChildCount(); i++) {
            ViewGroup tabView = (ViewGroup) getChildAt(i);
            ImageView imgIcon = tabView.findViewById(R.id.img_icon);
            View lineView = tabView.findViewById(R.id.line);

            if (i == position) {
                imgIcon.setImageResource(RecipesActivity.Sauces.values()[i].iconActive);
                lineView.setVisibility(View.VISIBLE);
            } else {
                imgIcon.setImageResource(RecipesActivity.Sauces.values()[i].iconInactive);
                lineView.setVisibility(View.INVISIBLE);
            }

            //imgIcon.setImageResource(i == position ? RecipesActivity.Sauces.values()[i].iconActive : RecipesActivity.Sauces.values()[i].iconInactive);
        }
    }

    //Setting ViewPager with tabs and tabs line.
    public void setupWithViewPager(final ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    public void setCallback(final Callback callback) {
//        mCallback = callback;
//    }

    public TabView(Context context) {
        super(context);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    /**
//     * Callback interface.
//     */
//    public interface Callback {
//
//        /**
//         * Triggered when tab is clicked.
//         *
//         * @param position The position of the clicked tab.
//         */
//        void onTabClick(final int position);
//    }

}
