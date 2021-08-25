package com.dataService.dataService.exception;

public class ItemAlreadyExistException extends RuntimeException{
    public ItemAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
