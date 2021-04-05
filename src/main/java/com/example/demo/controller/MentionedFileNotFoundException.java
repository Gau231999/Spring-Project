package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MentionedFileNotFoundException extends RuntimeException {
    public MentionedFileNotFoundException(String message) {
        super(message);
    }

    public MentionedFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}