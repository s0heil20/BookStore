package edu.sharif.bookstore.entity;

import android.graphics.drawable.Drawable;

import java.util.List;

public class Book {

    // Remote Fields
    private String bookId;
    private String title;
    private List<String> authors;
    private String publisher;
    private String description;
    private String category;
    private String imageLink;
    private int pageCount;
    private Drawable image;
    private String datePublished;

    // Local Fields
    private float avgRating;
    private int numbersLeft;
    private int price;
    private int noRatings;

    public Book(String bookId, String title, List<String> authors, String publisher, String datePublished, String description, String category, String imageLink, int pageCount) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.description = description;
        this.category = category;
        this.imageLink = imageLink;
        this.pageCount = pageCount;
        this.datePublished = datePublished;
        // TODO Must be set from database!
        this.price = 100;
        this.avgRating = (float) 2.5;
        this.noRatings = 100;
        this.numbersLeft = 10;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public int getNumbersLeft() {
        return numbersLeft;
    }

    public void setNumbersLeft(int numbersLeft) {
        this.numbersLeft = numbersLeft;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getNoRatings() {
        return noRatings;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public void setNoRatings(int noRatings) {
        this.noRatings = noRatings;
    }
}
