package com.example.foodapp;

public class CartRecItem {


    private int imageResource;
    private String itemTitle;
    private String itemPrice;
    private String itemValue;
    private String countValue;


    public CartRecItem(int imageResource, String itemTitle, String itemPrice, String itemValue, String countValue) {
        this.imageResource = imageResource;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemValue = itemValue;
        this.countValue = countValue;
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

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getCountValue() {
        return countValue;
    }

    public void setCountValue(String countValue) {
        this.countValue = countValue;
    }
}
