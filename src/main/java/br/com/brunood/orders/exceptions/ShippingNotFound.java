package br.com.brunood.orders.exceptions;

public class ShippingNotFound extends RuntimeException {
    public ShippingNotFound() {
        super("Shipping register not found");
    }
}
