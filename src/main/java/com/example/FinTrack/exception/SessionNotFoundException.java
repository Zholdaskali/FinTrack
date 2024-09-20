package com.example.FinTrack.exception;

public class SessionNotFoundException extends RuntimeException{
    public SessionNotFoundException() {
        super("Session not found");
    }
}
