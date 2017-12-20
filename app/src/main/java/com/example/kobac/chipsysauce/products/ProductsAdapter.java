package com.example.kobac.chipsysauce.products;

import android.content.Context;
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
 * Created by kobac on 14.12.17..
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private Context context;
    ArrayList<ProductsModel> productList;

    public ProductsAdapter(Context context, ArrayList<ProductsModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ProductsModel productsModel = productList.get(position);
        holder.productName.setText(productsModel.getProductName());
        holder.productID = productsModel.productId;


        Glide.with(holder.productImage.getContext()).load(productsModel.getProductImage()).into(holder.productImage);
//        .preload(R.drawable.loading_spinner)

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    public void addItems(final ArrayList<ProductsModel> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String imageUrl;
        public String productID;
        public ImageView productImage;
        public TextView productName;

        public ViewHolder(final View itemView) {
            super(itemView);

            productImage = (ImageView) itemView.findViewById(R.id.productImage);
            productName = (TextView) itemView.findViewById(R.id.productName);
        }
    }
}
