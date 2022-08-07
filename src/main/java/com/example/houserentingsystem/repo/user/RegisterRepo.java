package com.example.houserentingsystem.repo.user;

import com.example.houserentingsystem.model.register.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepo extends JpaRepository<Register, Integer> {
    public Register findUserByEmail(String email);
}
