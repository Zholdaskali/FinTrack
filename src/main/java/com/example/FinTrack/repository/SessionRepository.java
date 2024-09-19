package com.example.FinTrack.repository;

import com.example.FinTrack.model.entity.Session;
import com.example.FinTrack.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
     int deleteByToken(String token);
     List<Session> findByUserId(Long userId);

     Session findByToken(String token);
}
