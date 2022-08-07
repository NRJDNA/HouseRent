package com.example.houserentingsystem.repo.admin;

import com.example.houserentingsystem.model.admin.AdminRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRegisterRepo extends JpaRepository<AdminRegister, Integer> {

    public AdminRegister findAdminRegisterByEmail(String email);
}