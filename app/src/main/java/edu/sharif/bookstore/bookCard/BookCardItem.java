package edu.sharif.bookstore.bookCard;

import android.graphics.drawable.Drawable;

import java.util.List;

public class BookCardItem {
    private List<String> authorNames;
    private String bookTitle, publisherName, price, bookId;

    private Drawable bookCardImage;

    private float rating;

    public BookCardItem(List<String> authorNames, String bookTitle, String publisherName,
                        String price, String bookId, Drawable bookCardImage, float rating) {
        this.authorNames = authorNames;
        this.bookTitle = bookTitle;
        this.publisherName = publisherName;
        this.price = price;
        this.bookId = bookId;
        this.bookCardImage = bookCardImage;
        this.rating = rating;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getAuthorNames() {
        StringBuilder result = new StringBuilder();
        for (String authorName : authorNames) {
            result.append(authorName).append(", ");
        }
        return result.substring(0, result.length() - 2);
    }

    public float getRating() {
        return rating;
    }

    public String getPrice() {
        return price + "$";
    }

    public Drawable getBookCardImage() {
        return bookCardImage;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBookCardImage(Drawable bookCardImage) {
        this.bookCardImage = bookCardImage;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
