package com.example.FinTrack.config;

import com.example.FinTrack.util.encoder.BCryptPasswordEncoder;
import com.example.FinTrack.util.encoder.PasswordEncoder;
import com.example.FinTrack.util.token.RandomStringTokenGenerator;
import com.example.FinTrack.util.token.TokenGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TokenGenerator apiKeyGenerator() {
        return new RandomStringTokenGenerator(128);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
