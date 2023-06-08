package edu.sharif.bookstore.entity;

public class Feedback {
    private String username, comment, bookId;
    private int rating;

    public Feedback(String username, String comment, String bookId, int rating) {
        this.username = username;
        this.comment = comment;
        this.bookId = bookId;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public String getBookId() {
        return bookId;
    }

    public int getRating() {
        return rating;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
