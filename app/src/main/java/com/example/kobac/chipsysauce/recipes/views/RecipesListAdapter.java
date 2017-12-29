package com.example.kobac.chipsysauce.recipes.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kobac.chipsysauce.R;
import com.example.kobac.chipsysauce.recipes.RecipesActivity;
import com.example.kobac.chipsysauce.recipes.RecipesDetailsActivity;
import com.example.kobac.chipsysauce.recipes.RecipesModel;
import com.example.kobac.chipsysauce.storage.AppStorage;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kobac on 12.12.17..
 */

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<RecipesModel> recipesList;
    private Map<String, Object> mFavorites;


    public RecipesListAdapter(Context context, ArrayList<RecipesModel> recipesList) {
        this.mContext = context;
        this.recipesList = recipesList;
        mFavorites = AppStorage.getFavorites(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RecipesModel recipesModel = recipesList.get(position);
        final String sauceKeyId = Integer.toString(recipesModel.getSauceId());

        holder.sauceName.setText(recipesModel.getSauceName());
        holder.imageUrl = recipesModel.getSauceImage();

        if (isFavorite(sauceKeyId)) {
            holder.favouritesButton.setImageResource(R.drawable.favorite_active);
        } else {
            holder.favouritesButton.setImageResource(R.drawable.favorite_inactive);
        }


        // Show image
        Glide.with(holder.sauceImage.getContext()).load(recipesModel.getSauceImage()).into(holder.sauceImage);

        // Button: Item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecipesDetailsActivity.class);
                intent.putExtra(RecipesDetailsActivity.SAUCE_NAME, recipesModel);
                ((RecipesActivity) mContext).startActivityForResult(intent, RecipesActivity.SAUCE_REQUEST);

            }
        });


        // Button: Favorite
        holder.favouritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean currentState = isFavorite(sauceKeyId);

                boolean desiredState = !currentState;
                saveState(sauceKeyId, desiredState);

                if (desiredState) {
                    holder.favouritesButton.setImageResource(R.drawable.favorite_active);
                } else {
                    holder.favouritesButton.setImageResource(R.drawable.favorite_inactive);
                }

                notifyDataSetChanged();
            }
        });
    }


    private boolean isFavorite(final String id) {
        if (mFavorites.containsKey(id)) {
            boolean isFavorite = (boolean) mFavorites.get(id);
            return isFavorite;
        }

        return false;
    }


    /**
     * Saves the favorite state.
     *
     * @param id          The Id of the sauce.
     * @param isFavourite True if sauce is favorite, false otherwise.
     */
    private void saveState(final String id, boolean isFavourite) {
        mFavorites.put(id, isFavourite);
        AppStorage.storeFavorite(mContext, id, isFavourite);
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public void addItems(final ArrayList<RecipesModel> recipesList) {
        this.recipesList = recipesList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public int recipesId;
        public String imageUrl;
        public ImageView sauceImage;
        public TextView sauceName;
        public ImageView favouritesButton;

        public ViewHolder(final View itemView) {
            super(itemView);

            sauceImage = (ImageView) itemView.findViewById(R.id.sauceImage);
            sauceName = (TextView) itemView.findViewById(R.id.sauceNameDetails);
            favouritesButton = (ImageView) itemView.findViewById(R.id.favouriteButton);

        }
    }
}
