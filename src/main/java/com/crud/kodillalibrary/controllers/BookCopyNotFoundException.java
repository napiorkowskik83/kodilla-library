package com.crud.kodillalibrary.controllers;

import java.util.function.Supplier;

public class BookCopyNotFoundException extends RuntimeException{
    public BookCopyNotFoundException(final String message){
        super(message);
    }
}
