package com.meli.linktracker.exception;

public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException(String mensagem) {
        super(mensagem);
    }

    public LinkNotFoundException(Exception e) {
        super(e);
    }
}
