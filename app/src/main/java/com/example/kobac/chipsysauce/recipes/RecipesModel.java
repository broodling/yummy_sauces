package com.example.kobac.chipsysauce.recipes;

import java.io.Serializable;

public class RecipesModel implements Serializable {

    private int sauceID;
    private String sauceImage;
    private String sauceName;
    private String sauceType;
    private String sauceIngredient;
    private String saucePreparation;
    private String comentsCount;
    private String likeCount;
    private String dislikeCount;

    public RecipesModel(int sauceID, String sauceImage, String sauceName, String sauceType,
                        String sauceIngredient, String saucePreparation, String comentsCount, String likeCount, String dislikeCount) {
        this.sauceID = sauceID;
        this.sauceImage = sauceImage;
        this.sauceName = sauceName;
        this.sauceType = sauceType;
        this.sauceIngredient = sauceIngredient;
        this.saucePreparation = saucePreparation;
        this.comentsCount = comentsCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    public String getSauceImage() {
        return sauceImage;
    }


    public String getSauceName() {
        return sauceName;
    }


    public int getSauceID() {
        return sauceID;
    }

}
