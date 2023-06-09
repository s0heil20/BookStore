package edu.sharif.bookstore.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private String cartId, address, date;
    private int totalPrice;
    private ArrayList<String> bookIds;

    private ArrayList<Book> books;

    public Cart(String cartId, String address, String date, int totalPrice, ArrayList<String> bookIds) {
        this.cartId = cartId;
        this.address = address;
        this.date = date;
        this.totalPrice = totalPrice;
        this.bookIds = bookIds;
        this.books = new ArrayList<Book>();
    }

    public ArrayList<String> getBookIds() {
        return bookIds;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
