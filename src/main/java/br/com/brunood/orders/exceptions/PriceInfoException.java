package br.com.brunood.orders.exceptions;

public class PriceInfoException extends RuntimeException {
    public PriceInfoException() {
        super("Subtotal missing or equal 0");
    }
}
