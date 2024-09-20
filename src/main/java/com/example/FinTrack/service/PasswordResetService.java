package com.example.FinTrack.service;

import com.example.FinTrack.exception.SessionHasExpiredException;
import com.example.FinTrack.model.entity.User;
import com.example.FinTrack.model.request.PasswordResetRequest;
import com.example.FinTrack.util.encoder.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PasswordResetService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String editUser(
            @RequestBody PasswordResetRequest passwordResetRequest
    ) {
        if(sessionService.checkSession(passwordResetRequest.getToken())) {
            User editUser = sessionService.getTokenForUser(passwordResetRequest.getToken());
            String hashNewPassword = passwordEncoder.hash(passwordResetRequest.getNewPassword());
            editUser.setPassword(hashNewPassword);
            userService.saveUser(editUser);
            sessionService.invalidate(passwordResetRequest.getToken());
            return "Пароль успешно изменен";
        }
        throw new SessionHasExpiredException();
    }
}
