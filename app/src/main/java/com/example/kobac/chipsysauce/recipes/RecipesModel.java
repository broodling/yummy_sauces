package com.example.kobac.chipsysauce.recipes;

import java.io.Serializable;

public class RecipesModel implements Serializable {

    private int sauceId;
    private String sauceImage;
    private String sauceName;
    private int sauceType;
    private String sauceIngredient;
    private String saucePreparation;
    private String comentsCount;
    private String likeCount;
    private String dislikeCount;

    public RecipesModel(int sauceId, String sauceImage, String sauceName, int sauceType, String sauceIngredient, String saucePreparation, String comentsCount, String likeCount, String dislikeCount) {
        this.sauceId = sauceId;
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

    public int getSauceType() {
        return sauceType;
    }

    public String getSauceIngredient() {
        return sauceIngredient;
    }

    public String getSaucePreparation() {
        return saucePreparation;
    }

    public String getComentsCount() {
        return comentsCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public String getDislikeCount() {
        return dislikeCount;
    }

    public int getSauceId() {
        return sauceId;
    }
}
