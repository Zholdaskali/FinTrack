package com.example.FinTrack.service;

import com.example.FinTrack.model.entity.Session;
import com.example.FinTrack.repository.SessionRepository;
import com.example.FinTrack.util.token.TokenGenerator;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private UserService userService;
    public String generateForUser(Long userId) {
        Session session = new Session();
        String token = tokenGenerator.generate();
        session.setToken(token);
        session.setUser(userService.getByUserId(userId));
        session.setExpiration(LocalDateTime.now().plusDays(7));
        sessionRepository.save(session);
        return token;
    }

    @Transactional
    public boolean invalidate(String token) {
        return sessionRepository.deleteByToken(token) > 0;
    }
}
