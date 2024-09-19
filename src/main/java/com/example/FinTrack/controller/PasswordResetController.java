package com.example.FinTrack.controller;

import com.example.FinTrack.model.entity.User;
import com.example.FinTrack.model.request.PasswordResetRequest;
import com.example.FinTrack.model.response.MessageResponse;
import com.example.FinTrack.service.PasswordResetService;
import com.example.FinTrack.service.SessionService;
import com.example.FinTrack.service.UserService;
import com.example.FinTrack.util.encoder.PasswordEncoder;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password")
public class PasswordResetController {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordResetService passwordResetService;

    @PutMapping("/editUser")
    public MessageResponse<String> editUser(@RequestBody PasswordResetRequest passwordResetRequest) {
        return MessageResponse.empty(passwordResetService.editUser(passwordResetRequest));
    }
}
