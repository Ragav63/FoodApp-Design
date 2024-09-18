package com.example.foodapp;

public class LookingRecItem {


    private int imageResource;
    private String itemTitle;

    public LookingRecItem(int imageResource, String itemTitle) {
        this.imageResource = imageResource;
        this.itemTitle = itemTitle;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
