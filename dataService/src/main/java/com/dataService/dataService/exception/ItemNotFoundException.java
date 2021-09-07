package com.dataService.dataService.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
