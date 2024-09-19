package com.example.FinTrack.controller;

import com.example.FinTrack.model.entity.User;
import com.example.FinTrack.model.request.PasswordResetRequest;
import com.example.FinTrack.model.response.MessageResponse;
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

    @PutMapping("/editUser")
    public MessageResponse<String> editUser(
            @RequestBody PasswordResetRequest passwordResetRequest
    ) {
        if(sessionService.checkSession(passwordResetRequest.getToken())) {
            User editUser = sessionService.getTokenForUser(passwordResetRequest.getToken());
            String hashNewPassword = passwordEncoder.hash(passwordResetRequest.getNewPassword());
            editUser.setPassword(hashNewPassword);
            userService.saveUser(editUser);
            sessionService.invalidate(passwordResetRequest.getToken());
            return MessageResponse.empty("Новый пароль успено изменен");
        }
        return MessageResponse.empty("токен истек");
    }

//    @PutMapping("/editUser")
//    public MessageResponse<String> editUser(
//            @RequestBody PasswordResetRequest passwordResetRequest
//    ) {
//        if (sessionService.checkSession(passwordResetRequest.getToken())) {
//            // Получение пользователя по токену
//            User editUser = sessionService.getTokenForUser(passwordResetRequest.getToken());
//
//            // Хеширование нового пароля
//            String hashNewPassword = passwordEncoder.hash(passwordResetRequest.getNewPassword());
//            editUser.setPassword(hashNewPassword);
//
//            // Сохранение изменений пользователя
//            userService.saveUser(editUser);  // Передаем объект User в saveUser
//
//            return MessageResponse.of("Новый пароль успешно изменен");
//        }
//        // Если сессия не валидна
//        return MessageResponse.empty("Ошибка: Неверный токен или сессия истекла");
//    }

}
