package com.example.FinTrack.repository;

import com.example.FinTrack.model.entity.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    public int deleteByToken(String token);
}
