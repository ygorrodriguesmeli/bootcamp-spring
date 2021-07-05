package com.meli.linktracker.exception;

public class LinkForbiddenException extends RuntimeException {
    public LinkForbiddenException(String mensagem) {
        super(mensagem);
    }

    public LinkForbiddenException(Exception e) {
        super(e);
    }
}
