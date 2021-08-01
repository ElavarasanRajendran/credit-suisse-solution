package com.creditsuisse.project.exception;

public class EventNotSavedException extends RuntimeException{
    public EventNotSavedException(String message) {
        super(message);
    }
}
