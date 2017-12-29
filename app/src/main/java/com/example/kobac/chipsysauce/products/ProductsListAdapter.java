package com.example.kobac.chipsysauce.products;

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
 * Created by kobac on 14.12.17..
 */

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ViewHolder> {

    private Context mContext;
    ArrayList<ProductsModel> productList;

    public ProductsListAdapter(Context context, ArrayList<ProductsModel> productList) {
        this.mContext = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final ProductsModel productsModel = productList.get(position);
        holder.productName.setText(productsModel.getProductName());
        holder.productID = productsModel.productId;


        Glide.with(holder.productImage.getContext()).load(productsModel.getProductImage()).into(holder.productImage);
//        .preload(R.drawable.loading_spinner)

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductDetailsActivity.class);
                intent.putExtra(ProductDetailsActivity.PRODUCT_NAME, productsModel);
                holder.itemView.getContext().startActivity(intent);
            }
        });

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
