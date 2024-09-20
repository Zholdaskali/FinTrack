package com.example.FinTrack.service;

import com.example.FinTrack.exception.SessionNotFoundException;
import com.example.FinTrack.model.entity.Session;
import com.example.FinTrack.model.entity.User;
import com.example.FinTrack.repository.SessionRepository;
import com.example.FinTrack.util.token.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        session.setExpiration(LocalDateTime.now().plusMinutes(1));
        sessionRepository.save(session);
        return token;
    }

    public boolean checkSession(String token) {
        Optional <Session> sessionCheck = sessionRepository.findByToken(token);
        if (sessionCheck.isPresent()) {
            return true;
        } else {
            throw new SessionNotFoundException();
        }
    }

    public User getTokenForUser(String token) {
        Optional<Session> session = sessionRepository.findByToken(token);
        return session.map(Session::getUser)
                .orElseThrow(SessionNotFoundException::new);
    }


    @Transactional
    public boolean invalidate(String token) {
        return sessionRepository.deleteByToken(token) > 0;
    }
    public void manageCountSession(Long userId) {
        List<Session> activeSessions = sessionRepository.findByUserId(userId);
        int maxCountActiveSessions = 2;

        if(activeSessions.size() > maxCountActiveSessions) {
            activeSessions.stream().min(Comparator.comparing(Session::getExpiration)).ifPresent(olderSession -> sessionRepository.delete(olderSession));
        }
    }
}
