package com.example.FinTrack.service;

import com.example.FinTrack.exception.AuthenticationErrorException;
import com.example.FinTrack.exception.InvalidPasswordException;
import com.example.FinTrack.exception.UserAlreadyExistsException;
import com.example.FinTrack.model.entity.User;
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
            throw new UserAlreadyExistsException();
        } else {
            User user = new User(userName, passwordEncoder.hash(password));
            userService.saveUser(user);
            return "Пользователь успешно создан";
        }
    }

    public String login(String userName, String password) {
        if(userService.existsByUsername(userName)) {
            User user = userService.getByUserName(userName);
            String passwordMatches = user.getPassword();
            Long userId = user.getId();
            if(passwordEncoder.check(password, passwordMatches)) {
                sessionService.manageCountSession(userId);
                return sessionService.generateForUser(userId);
            } else {
                throw new InvalidPasswordException();
            }
        } else {
            throw new AuthenticationErrorException();
        }
    }

    public boolean logout(String token)  {
        return sessionService.invalidate(token);
    }
}

