package com.example.FinTrack.controller;

import com.example.FinTrack.model.request.LogOutRequest;
import com.example.FinTrack.model.request.RegisterRequest;
import com.example.FinTrack.model.response.MessageResponse;
import com.example.FinTrack.service.AuthenticationService;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    //регистрация
    @PostMapping("/register")
    public MessageResponse<String> register(@RequestBody RegisterRequest registerRequest) {
        return MessageResponse.empty(authenticationService.register(registerRequest.getUserName(), registerRequest.getPassword()));
    }


    //логин
    @PostMapping("/login")
    public MessageResponse<String> login(@RequestBody RegisterRequest registerRequest) {
        return MessageResponse.empty(authenticationService.login(registerRequest.getUserName(), registerRequest.getPassword()));
    }

    @PostMapping("/logout")
    public MessageResponse<Boolean> logout(@RequestBody LogOutRequest logOutRequest) {
        String token = logOutRequest.getToken();
        return MessageResponse.of(authenticationService.logout(token));
    }
}
