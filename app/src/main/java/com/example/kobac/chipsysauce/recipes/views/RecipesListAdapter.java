package com.example.kobac.chipsysauce.recipes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kobac.chipsysauce.R;

import java.util.ArrayList;

/**
 * Created by kobac on 12.12.17..
 */

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.ViewHolder> {

    private Context context;
    ArrayList<RecipesModel> recipesList;

    public RecipesListAdapter(Context context, ArrayList<RecipesModel> recipesList) {
        this.context = context;
        this.recipesList = recipesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final RecipesModel recipesModel = recipesList.get(position);
        holder.sauceName.setText(recipesModel.getSauceName());
        holder.recipesId = recipesModel.getSauceID();

        holder.imageUrl = recipesModel.getSauceImage();

        Glide.with(holder.sauceImage.getContext()).load(recipesModel.getSauceImage()).into(holder.sauceImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), RecipesDetails.class);
                holder.itemView.getContext().startActivity(intent);

            }
        });


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

        public ViewHolder(final View itemView) {
            super(itemView);

            sauceImage = (ImageView) itemView.findViewById(R.id.sauceImage);
            sauceName = (TextView) itemView.findViewById(R.id.sauceName);

        }
    }
}
