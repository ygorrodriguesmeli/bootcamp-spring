package com.meli.linktracker.exception.handler;

import com.meli.linktracker.exception.LinkForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LinkForbiddenExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> defaultHandler(LinkForbiddenException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
}
