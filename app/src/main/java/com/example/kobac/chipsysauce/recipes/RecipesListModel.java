package com.example.kobac.chipsysauce.recipes;

import java.util.ArrayList;


public class RecipesListModel {

    private ArrayList<RecipesModel> list;

    public RecipesListModel(ArrayList<RecipesModel> list) {
        this.list = list;
    }

    public ArrayList<RecipesModel> getList() {
        return list;
    }

    public void setList(ArrayList<RecipesModel> list) {
        this.list = list;
    }

}
