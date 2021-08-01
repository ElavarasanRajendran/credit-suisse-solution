package com.creditsuisse.project.exception;

public class FileNotFoundAtLocation extends RuntimeException{
    public FileNotFoundAtLocation(String message) {
        super(message);
    }
}
