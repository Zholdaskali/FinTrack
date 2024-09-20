package com.example.FinTrack.exception;

public class SessionHasExpiredException extends RuntimeException {
    public SessionHasExpiredException() {
        super ("Session has expired");
    }
}
