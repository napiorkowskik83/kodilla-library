package com.crud.kodillalibrary.controllers;

public class BorrowingNotFoundException extends Exception{
    public BorrowingNotFoundException(final String message){
        super(message);
    }
}
