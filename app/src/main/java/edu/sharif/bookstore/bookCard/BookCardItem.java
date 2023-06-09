package edu.sharif.bookstore.bookCard;

import android.graphics.drawable.Drawable;

public class BookCardItem {
    private String bookTitle, publisherName, authorName, price, bookId;

    private Drawable bookCardImage;

    public BookCardItem(String bookTitle, String publisherName, String authorName, String price, String bookId, Drawable bookCardImage) {
        this.bookTitle = bookTitle;
        this.publisherName = publisherName;
        this.authorName = authorName;
        this.price = price;
        this.bookId = bookId;
        this.bookCardImage = bookCardImage;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPrice() {
        return price;
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

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
