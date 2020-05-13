package com.schwarck.kundenverwaltung.exceptions;

public class CustomerDoesNotExistException extends Exception {

    public CustomerDoesNotExistException() {
        super("Kunde nicht vorhanden. ");
    }


}
