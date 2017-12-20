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

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
