package br.com.brunood.orders.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order not found");
    }
}
