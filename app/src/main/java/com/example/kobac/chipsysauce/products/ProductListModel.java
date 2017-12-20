package com.example.kobac.chipsysauce.products;

import java.util.ArrayList;

/**
 * Created by kobac on 14.12.17..
 */

public class ProductListModel {

    private boolean more;
    private ArrayList<ProductsModel> list;

    public ProductListModel(boolean more, ArrayList<ProductsModel> list) {
        this.more = more;
        this.list = list;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public ArrayList<ProductsModel> getList() {
        return list;
    }

    public void setList(ArrayList<ProductsModel> list) {
        this.list = list;
    }
}
