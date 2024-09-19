package com.example.FinTrack.repository;

import com.example.FinTrack.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
     boolean existsByUserName(String userName);
     User findByUserName(String userName);
}
