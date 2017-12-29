package com.example.kobac.chipsysauce.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kobac.chipsysauce.BaseActivity;
import com.example.kobac.chipsysauce.R;
import com.example.kobac.chipsysauce.storage.AppStorage;

import java.util.Map;

/**
 * Created by kobac on 18.12.17..
 */

public class RecipesDetailsActivity extends BaseActivity {

    private TextView sauceName;
    private TextView commentButton;
    private TextView sauceIngredients;
    private TextView saucePreparation;

    private ImageView sauceImage;
    private ImageView sauceType;
    private ImageView favouriteImage;

    private String id;

    private Map<String, Object> mFavorites;

    public static final String SAUCE_NAME = "name";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_details);


        sauceName = (TextView) findViewById(R.id.sauceNameDetails);
        commentButton = (TextView) findViewById(R.id.commentButton);
        sauceIngredients = (TextView) findViewById(R.id.sauceIngredient);
        saucePreparation = (TextView) findViewById(R.id.saucePreparation);
        sauceImage = (ImageView) findViewById(R.id.sauceImage);
        sauceType = (ImageView) findViewById(R.id.sauceType);
        favouriteImage = (ImageView) findViewById(R.id.favouriteButtonDetails);

        final Intent intent = getIntent();
        RecipesModel recipesModel = (RecipesModel) intent.getSerializableExtra(SAUCE_NAME);

        sauceName.setText(recipesModel.getSauceName());

        Glide.with(sauceImage.getContext()).load(recipesModel.getSauceImage()).into(sauceImage);
        sauceIngredients.setText(recipesModel.getSauceIngredient());
        saucePreparation.setText(recipesModel.getSaucePreparation());
        commentButton.setText(recipesModel.getComentsCount());
        id = String.valueOf(recipesModel.getSauceId());

        int iconSauce = RecipesActivity.Sauces.getIcon(recipesModel.getSauceType());
        sauceType.setImageResource(iconSauce);

        mFavorites = AppStorage.getFavorites(this);


        if (AppStorage.isFavorite(this, id)) {

            favouriteImage.setImageResource(R.drawable.favorite_active);
        }

        favouriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentState = isFavorite(id);

                boolean desiredState = !currentState;
                saveState(id, desiredState);
                if (desiredState) {
                    favouriteImage.setImageResource(R.drawable.favorite_active);
                } else {
                    favouriteImage.setImageResource(R.drawable.favorite_inactive);
                }

                Intent intent1 = new Intent();
                intent1.putExtra("desired_state", desiredState);
                setResult(RESULT_OK, intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void saveState(final String id, boolean isFavourite) {
        mFavorites.put(id, isFavourite);
        AppStorage.storeFavorite(this, id, isFavourite);

    }

    private boolean isFavorite(final String id) {
        if (mFavorites.containsKey(id)) {
            boolean isFavorite = (boolean) mFavorites.get(id);
            return isFavorite;
        }

        return false;
    }

}
