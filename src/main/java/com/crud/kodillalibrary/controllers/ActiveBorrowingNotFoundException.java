package com.crud.kodillalibrary.controllers;

public class ActiveBorrowingNotFoundException extends Exception{
    public ActiveBorrowingNotFoundException(final String message){
        super(message);
    }
}
