package com.example.kobac.chipsysauce.products;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kobac.chipsysauce.BaseActivity;
import com.example.kobac.chipsysauce.R;
import com.example.kobac.chipsysauce.api.API;
import com.example.kobac.chipsysauce.api.APIResponse;
import com.example.kobac.chipsysauce.api.APITask;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kobac on 8.12.17..
 */

public class ProductsActivity extends BaseActivity {

    private GridLayoutManager mGridManager;
    private ProductsListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private String productID;
    private String productSauceID;
    private String productName;
    private String productImage;
    private String productDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new ProductsListAdapter(getApplicationContext(), new ArrayList<ProductsModel>());
        mGridManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mGridManager);

        new APITask() {
            @Override
            protected APIResponse doInBackground() throws Exception {
                return API.chipsyAPI(getActivity());
            }

            @Override
            public void onComplete(APIResponse response) {
                ProductListModel productListModel = new ProductListModel(false, new ArrayList<ProductsModel>());
                JSONObject body = response.getJSONObject();
                JSONObject sync = (JSONObject) body.get("sync");
                JSONObject data = (JSONObject) sync.get("data");
                JSONArray table = (JSONArray) data.get("table");
                JSONObject productObject = (JSONObject) table.get(2);
                JSONArray records = (JSONArray) productObject.get("records");
                for (int i = 0; i < records.size(); i++) {
                    JSONObject access = (JSONObject) records.get(i);
                    JSONObject fields = (JSONObject) access.get("fields");
                    productName = fields.getAsString("name");
                    productImage = fields.getAsString("image");
                    productID = access.getAsString("id");
                    productSauceID = fields.getAsString("cms_sauce_set");
                    productDescription = fields.getAsString("description");
                    ProductsModel productsModel = new ProductsModel(productName, productImage, productID, productDescription, productSauceID);
                    productListModel.getList().add(productsModel);

                }

                mAdapter.addItems(productListModel.getList());
            }

            @Override
            public void onError(String error) {

            }

            @Override
            protected BaseActivity getActivity() {
                return ProductsActivity.this;
            }
        };

        mRecyclerView.setAdapter(mAdapter);
    }
}
