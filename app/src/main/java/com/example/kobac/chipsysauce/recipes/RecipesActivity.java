package com.example.kobac.chipsysauce.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.example.kobac.chipsysauce.BaseActivity;
import com.example.kobac.chipsysauce.R;
import com.example.kobac.chipsysauce.api.API;
import com.example.kobac.chipsysauce.api.APIResponse;
import com.example.kobac.chipsysauce.api.APITask;
import com.example.kobac.chipsysauce.recipes.views.RecipesViewPagerAdapter;
import com.example.kobac.chipsysauce.recipes.views.TabView;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;

/**
 * Shows the sauces.
 */
public class RecipesActivity extends BaseActivity {

    public static final int SAUCE_REQUEST = 1;

    private ViewPager mViewPager;
    private RecipesViewPagerAdapter mViewPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        // Execute API
        new APITask() {

            @Override
            protected APIResponse doInBackground() throws Exception {
                return API.chipsyAPI(getActivity());
            }

            @Override
            public void onComplete(APIResponse response) {
                JSONObject body = response.getJSONObject();
                JSONObject sync = (JSONObject) body.get("sync");
                JSONObject data = (JSONObject) sync.get("data");
                JSONArray table = (JSONArray) data.get("table");
                JSONObject sauceKind = (JSONObject) table.get(0);
                JSONObject sauces = (JSONObject) table.get(1);
                JSONArray records = (JSONArray) sauces.get("records");

                // Holds the list of 4 sauces list
                final ArrayList<ArrayList<RecipesModel>> saucesCompleteList = new ArrayList<>();

                for (final Sauces sauce : Sauces.values()) {

                    // Create single sauce list
                    final ArrayList<RecipesModel> saucesFlavors = new ArrayList<>();

                    for (int i = 0; i < records.size(); i++) {
                        JSONObject access = (JSONObject) records.get(i);
                        int sauceId = Integer.parseInt(access.getAsString("id"));
                        JSONObject fields = (JSONObject) access.get("fields");
                        final int sauceTypeId = Integer.parseInt(fields.getAsString("cms_type_id"));

                        if (sauce.id == sauceTypeId) {
                            final String sauceImage = fields.getAsString("image");
                            final String sauceName = fields.getAsString("name");
                            final String sauceIngredient = fields.getAsString("ingredient");
                            final String saucePreparation = fields.getAsString("preparation");
                            final String commentsCount = fields.getAsString("comments_count");
                            final String likeCount = fields.getAsString("like");
                            final String dislikeCount = fields.getAsString("dislike");

                            RecipesModel recipesModel = new RecipesModel(sauceId, sauceImage, sauceName, sauceTypeId, sauceIngredient, saucePreparation, commentsCount, likeCount, dislikeCount);
                            saucesFlavors.add(recipesModel);
                        }
                    }

                    saucesCompleteList.add(saucesFlavors);
                }

                setupViewPagerAdapter(saucesCompleteList);

                // Fade out preloader TODO
            }

            @Override
            public void onError(String error) {

            }

            @Override
            protected BaseActivity getActivity() {
                return RecipesActivity.this;
            }
        };

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        finish();
//        return true;
//    }


    /**
     * Creates a ViewPager adapter.
     *
     * @param saucesCompleteList The complete list of sauces.
     */
    private void setupViewPagerAdapter(final ArrayList<ArrayList<RecipesModel>> saucesCompleteList) {
        mViewPagerAdapter = new RecipesViewPagerAdapter(getSupportFragmentManager(), saucesCompleteList);
        mViewPager.setAdapter(mViewPagerAdapter);

        TabView tabView = (TabView) findViewById(R.id.tab_view);
        tabView.setupWithViewPager(mViewPager);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case SAUCE_REQUEST:
                    mViewPagerAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }


    /**
     * List of sauces.
     */
    public enum Sauces {

        HOT(15, R.drawable.tab_hot_active, R.drawable.tab_hot_inactive),
        SALTY(16, R.drawable.tab_salty_active, R.drawable.tab_salty_inactive),
        SWEET(17, R.drawable.tab_sweet_active, R.drawable.tab_sweet_inactive),
        SOUR(18, R.drawable.tab_sour_active, R.drawable.tab_sour_inactive);

        /**
         * Id of the sauce.
         */
        public int id;

        /**
         * The resource Id of the iconActive.
         */
        public int iconActive;

        /**
         * The resource Id of the iconInactive.
         */
        public int iconInactive;

        /**
         * Initialize.
         *
         * @param id         The id of the sauce.
         * @param iconActive The resource Id of the iconActive.
         */
        Sauces(final int id, final int iconActive, final int iconInactive) {
            this.id = id;
            this.iconActive = iconActive;
            this.iconInactive = iconInactive;
        }

        /**
         * Get the icon based on a Id. this method is writen in enum.
         *
         * @param typeId The Id of the sauce.
         * @return The resource Id of the icon.
         */
        public static int getIcon(final int typeId) {
            for (final Sauces sauces : values()) {
                if (typeId == sauces.id) {
                    return sauces.iconActive;
                }
            }

            return SWEET.iconActive;
        }
    }

}
