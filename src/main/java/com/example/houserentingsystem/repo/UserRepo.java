package com.example.houserentingsystem.repo;

import com.example.houserentingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public User findUserByEmail(String email);
}