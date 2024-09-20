package com.example.FinTrack.service;

import com.example.FinTrack.exception.UserNotFoundException;
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
        if(!userRepository.existsByUserName(userName)) {
            throw new UserNotFoundException();
        }
        return true;
    }
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException();
        }
    }
}
