package edu.sharif.bookstore.exception;

public class OrderException extends RuntimeException {
    private OrderExceptionType orderExceptionType;

    public OrderException(String message, OrderExceptionType orderExceptionType) {
        super(message);
        this.orderExceptionType = orderExceptionType;
    }
}
