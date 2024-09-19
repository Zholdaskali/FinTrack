package com.example.FinTrack.service;

import com.example.FinTrack.model.entity.User;
import com.example.FinTrack.model.response.MessageResponse;
import com.example.FinTrack.util.encoder.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionService sessionService;

    public String register(String userName, String password) {
        if(userService.existsByUsername(userName)) {
            return "Пользователь уже существует";
        } else {
            return userService.createUser(userName, passwordEncoder.hash(password));
        }
    }

    public String login(String userName, String password) {
        if(userService.existsByUsername(userName)) {
            User user = userService.getByUserName(userName);
            String passwordMatches = user.getPassword();
            Long userId = user.getId();
            if(passwordEncoder.check(password, passwordMatches)) {
                return sessionService.generateForUser(userId);
            } else {
                return "Неправильный пароль";
            }
        } else {
            return "Ошибка аутентификации";
        }
    }

    public boolean logout(String token) {
        return sessionService.invalidate(token);
    }
}

