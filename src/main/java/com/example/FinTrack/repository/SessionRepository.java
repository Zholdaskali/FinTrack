package com.example.FinTrack.repository;

import com.example.FinTrack.model.entity.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
     int deleteByToken(String token);
     List<Session> findByUserId(Long userId);

     Optional<Session> findByToken(String token);
}
