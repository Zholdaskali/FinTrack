package com.example.FinTrack.service;

import com.example.FinTrack.model.entity.User;
import com.example.FinTrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void saveUser(User user) {
        userRepository.save(user);
    }
    public boolean existsByUsername(String userName) {
        return userRepository.existsByUserName(userName);
    }
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User getByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
