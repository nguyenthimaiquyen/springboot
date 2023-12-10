package com.quyen.test.exception;

public class AppointmentNotFoundException extends Exception {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
