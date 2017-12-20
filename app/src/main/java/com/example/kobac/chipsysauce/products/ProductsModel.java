package com.example.kobac.chipsysauce.products;

/**
 * Created by kobac on 14.12.17..
 */

public class ProductsModel {


    String productName;
    String productImage;
    String productId;

    public ProductsModel(String productName, String productImage, String productId) {
        this.productName = productName;
        this.productImage = productImage;
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

}
