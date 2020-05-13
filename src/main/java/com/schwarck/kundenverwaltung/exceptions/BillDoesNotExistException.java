package com.schwarck.kundenverwaltung.exceptions;

public class BillDoesNotExistException extends Exception {
    public BillDoesNotExistException() {
        super("Rechnung nicht vorhanden. ");
    }
}
