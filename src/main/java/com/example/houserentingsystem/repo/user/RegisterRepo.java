package com.example.houserentingsystem.repo.user;

import com.example.houserentingsystem.model.register.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepo extends JpaRepository<Register, Integer> {
    @Query(value = "select * from user_register u where u.id=?1",nativeQuery = true)
    List<Register> getRegisterList(Integer id);


    public Register findUserByEmail(String email);
}
