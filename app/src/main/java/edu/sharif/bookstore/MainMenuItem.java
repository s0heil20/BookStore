package edu.sharif.bookstore;

import android.graphics.drawable.Drawable;

public class MainMenuItem {

    Drawable image;
    String title;
    String rating;
    String price;

    public MainMenuItem(Drawable image, String title, String rating, String price){
        this.image = image;
        this.title = title;
        this.rating = rating;
        this.price = price;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
