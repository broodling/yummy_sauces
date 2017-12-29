package com.example.kobac.chipsysauce.products;

import java.io.Serializable;

/**
 * Created by kobac on 14.12.17..
 */

public class ProductsModel implements Serializable {

    String productId;
    String productName;
    String productImage;
    String productDescription;
    String productSauceID;

    public ProductsModel(String productName, String productImage, String productId, String productDescription,String productSauceID) {
        this.productName = productName;
        this.productImage = productImage;
        this.productId = productId;
        this.productDescription = productDescription;
        this.productSauceID = productSauceID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductSauceID() {
        return productSauceID;
    }
}
