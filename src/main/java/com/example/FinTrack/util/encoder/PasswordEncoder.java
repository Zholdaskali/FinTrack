package com.example.FinTrack.util.encoder;

import org.apache.catalina.util.Strftime;

public interface PasswordEncoder {
    public String hash(String password);

    public boolean check(String password, String hash);
}
