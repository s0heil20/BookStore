package edu.sharif.bookstore.entity;

import java.util.ArrayList;

public class Cart {
    private String cartId, address, date;
    private int totalPrice;
    private ArrayList<String> bookIds;

    public Cart(String cartId, String address, String date, int totalPrice, ArrayList<String> bookIds) {
        this.cartId = cartId;
        this.address = address;
        this.date = date;
        this.totalPrice = totalPrice;
        this.bookIds = bookIds;
    }
}
