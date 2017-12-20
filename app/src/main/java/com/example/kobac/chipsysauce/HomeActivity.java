package com.example.kobac.chipsysauce;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.kobac.chipsysauce.products.ProductsActivity;
import com.example.kobac.chipsysauce.recipes.RecipesActivity;


public class HomeActivity extends BaseActivity {

    private RelativeLayout recepiesLayout;
    private RelativeLayout productsLayout;
    private RelativeLayout favouritesLayout;
    private RelativeLayout addRecepiesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        recepiesLayout = (RelativeLayout) findViewById(R.id.recepiesLayout);
        productsLayout = (RelativeLayout) findViewById(R.id.productsLayout);
        favouritesLayout = (RelativeLayout) findViewById(R.id.favouritesLayout);
        addRecepiesLayout = (RelativeLayout) findViewById(R.id.addRecepiesLayout);

        recepiesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RecipesActivity.class);
                startActivity(intent);
            }
        });

        productsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
                startActivity(intent);
            }
        });


    }
}
