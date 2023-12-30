package br.com.brunood.orders.exceptions;

public class EmptyBodyException extends RuntimeException {
    public EmptyBodyException() {
        super("Invalid Body");
    }
}
