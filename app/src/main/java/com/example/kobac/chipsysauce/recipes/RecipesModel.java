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

    public void setSauceImage(String sauceImage) {
        this.sauceImage = sauceImage;
    }

    public String getSauceName() {
        return sauceName;
    }

    public void setSauceName(String sauceName) {
        this.sauceName = sauceName;
    }

    public int getSauceID() {
        return sauceID;
    }

    public void setSauceID(int sauceID) {
        this.sauceID = sauceID;
    }

    public String getSauceType() {
        return sauceType;
    }

    public void setSauceType(String sauceType) {
        this.sauceType = sauceType;
    }

    public String getSauceIngredient() {
        return sauceIngredient;
    }

    public void setSauceIngredient(String sauceIngredient) {
        this.sauceIngredient = sauceIngredient;
    }

    public String getSaucePreparation() {
        return saucePreparation;
    }

    public void setSaucePreparation(String saucePreparation) {
        this.saucePreparation = saucePreparation;
    }

    public String getComentsCount() {
        return comentsCount;
    }

    public void setComentsCount(String comentsCount) {
        this.comentsCount = comentsCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(String dislikeCount) {
        this.dislikeCount = dislikeCount;
    }
}
