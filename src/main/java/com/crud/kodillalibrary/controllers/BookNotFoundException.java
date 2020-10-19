package com.crud.kodillalibrary.controllers;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(final String message){
        super(message);
    }
}
