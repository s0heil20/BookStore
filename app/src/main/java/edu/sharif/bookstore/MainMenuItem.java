package edu.sharif.bookstore;

import android.graphics.drawable.Drawable;

public class MainMenuItem {

    Drawable image;
    String title;
    float rating;
    String price;
    String bookId;


    public MainMenuItem(Drawable image, String title, float rating, String price, String bookId){
        this.image = image;
        this.title = title;
        this.rating = rating;
        this.price = price;
        this.bookId = bookId;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
