package com.crud.kodillalibrary.controllers;

public class BookCopyNotAvailableException extends Exception{
    public BookCopyNotAvailableException(final String message){
        super(message);
    }
}
