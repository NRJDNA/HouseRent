package com.example.houserentingsystem.repo.admin;

import com.example.houserentingsystem.model.admin.AdminRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRegisterRepo extends JpaRepository<AdminRegister, Integer> {
@Query(value = "select * from admin_register a where a.id=?1",nativeQuery = true)
    List<AdminRegister> getAdminRegisterList(Integer id);

    public AdminRegister findAdminRegisterByEmail(String email);
}
