package com.example.kobac.chipsysauce.recipes;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.kobac.chipsysauce.BaseActivity;
import com.example.kobac.chipsysauce.R;
import com.example.kobac.chipsysauce.api.API;
import com.example.kobac.chipsysauce.api.APIResponse;
import com.example.kobac.chipsysauce.api.APITask;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;

/**
 * Shows the sauces.
 */
public class RecipesActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_activity);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.tab_item_hot);
        mTabLayout.getTabAt(1).setIcon(R.drawable.tab_item_salty);
        mTabLayout.getTabAt(2).setIcon(R.drawable.tab_item_sweet);
        mTabLayout.getTabAt(3).setIcon(R.drawable.tab_item_sour);

        mViewPager.setOffscreenPageLimit(4);

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
                JSONArray sauceKindRecords = (JSONArray) sauceKind.get("records");
                JSONObject typeAccess = (JSONObject) sauceKindRecords.get(3);
                String typeID = typeAccess.getAsString("id");
                JSONObject sauces = (JSONObject) table.get(1);
                JSONArray records = (JSONArray) sauces.get("records");

                // Holds the list of 4 sauces list
                final ArrayList<ArrayList<RecipesModel>> saucesCompleteList = new ArrayList<>();

                for (final Sauces sauce : Sauces.values()) {

                    // Create single sauce list
                    final ArrayList<RecipesModel> saucesFlavors = new ArrayList<>();

                    for (int i = 0; i < records.size(); i++) {
                        JSONObject access = (JSONObject) records.get(i);
                        JSONObject fields = (JSONObject) access.get("fields");
                        final int sauceTypeId = Integer.parseInt(fields.getAsString("cms_type_id"));

                        if (sauce.id == sauceTypeId) {
                            final String sauceImage = fields.getAsString("image");
                            final String sauceName = fields.getAsString("name");
                            final String sauceId = access.getAsString("id");
                            final String sauceIngredient = fields.getAsString("ingredient");
                            final String saucePreparation = fields.getAsString("preparation");
                            final String commentsCount = fields.getAsString("comments_count");
                            final String likeCount = fields.getAsString("like");
                            final String dislikeCount = fields.getAsString("dislike");

                            RecipesModel recipesModel = new RecipesModel(sauceTypeId, sauceImage, sauceName, sauceId, sauceIngredient, saucePreparation, commentsCount, likeCount, dislikeCount);
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

    /**
     * Creates a ViewPager adapter.
     *
     * @param saucesCompleteList The complete list of sauces.
     */
    private void setupViewPagerAdapter(final ArrayList<ArrayList<RecipesModel>> saucesCompleteList) {
        RecipesViewPagerAdapter viewPagerAdapter = new RecipesViewPagerAdapter(getSupportFragmentManager(), saucesCompleteList);
    }

    /**
     * List of sauces.
     */
    public enum Sauces {

        HOT(15),
        SALTY(16),
        SWEET(17),
        SOUR(18);

        /**
         * Id of the sauce.
         */
        public int id;

        /**
         * Initialize.
         *
         * @param id The id of the sauce.
         */
        Sauces(final int id) {
            this.id = id;
        }
    }

}
