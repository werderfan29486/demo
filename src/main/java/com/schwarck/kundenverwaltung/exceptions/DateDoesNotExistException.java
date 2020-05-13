package com.schwarck.kundenverwaltung.exceptions;

public class DateDoesNotExistException extends Exception {

    public DateDoesNotExistException() {
        super("Termin nicht vorhanden. ");
    }

}
