package com.travello.exception;

public class NoPriceFoundException extends RuntimeException {
    public NoPriceFoundException(String message) {
        super(message);
    }
}
