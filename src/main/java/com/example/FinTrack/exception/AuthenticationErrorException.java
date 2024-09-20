package com.example.FinTrack.exception;

public class AuthenticationErrorException extends RuntimeException {
    public AuthenticationErrorException() {
        super("Authentication error");
    }
}
