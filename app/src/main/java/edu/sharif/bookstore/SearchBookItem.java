package edu.sharif.bookstore;

public class SearchBookItem {
    private String bookTitle, publisherName, authorName, price;

    public SearchBookItem(String bookTitle, String publisherName, String authorName, String price) {
        this.bookTitle = bookTitle;
        this.publisherName = publisherName;
        this.authorName = authorName;
        this.price = price;
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
}
