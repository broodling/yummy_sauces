package com.example.kobac.chipsysauce.products;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kobac.chipsysauce.BaseActivity;
import com.example.kobac.chipsysauce.R;

/**
 * Created by kobac on 27.12.17..
 */

public class ProductDetailsActivity extends BaseActivity {

    public static final String PRODUCT_NAME = "product_name";

    private TextView productName;
    private TextView productDescription;

    private ImageView productImage;

    private Button numOfSauces;

    private String productID;
    private String productSauceID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        productName = (TextView) findViewById(R.id.productDetailName);
        productDescription = (TextView) findViewById(R.id.productDetailDescription);
        productImage = (ImageView) findViewById(R.id.productDetailImage);https://github.com/broodling/yummy_sauces/blob/master/app/src/main/java/com/example/kobac/chipsysauce/recipes/views/RecipesListAdapter.java
        numOfSauces = (Button) findViewById(R.id.numOfSauces);

        final Intent intent = getIntent();
        ProductsModel productsModel = (ProductsModel) intent.getSerializableExtra(PRODUCT_NAME);
        productName.setText(productsModel.getProductName());
        productDescription.setText(stripHtml(productsModel.getProductDescription()));
        Glide.with(productImage.getContext()).load(productsModel.getProductImage()).into(productImage);
        productID = productsModel.getProductId();
        productSauceID = productsModel.getProductSauceID();

        int size = 0;

        if (productSauceID == null) {
            size = 0;

        } else if (productSauceID.contains(",")) {
            String[] parts = productSauceID.split(",");
            String part1 = parts[0];
            String part2 = parts[1];
            size = parts.length;

        } else if (!(productSauceID.contains(","))) {
            size = 1;
        }

        numOfSauces.setText("Broj umaka " + size);

    }

    public String stripHtml(String html) {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return String.valueOf(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            return String.valueOf(Html.fromHtml(html));
        }
    }
}
