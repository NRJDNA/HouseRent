package com.example.houserentingsystem.repo;

import com.example.houserentingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public User findUserByEmail(String email);
    @Query(value = "SELECT * FROM tbl_user u WHERE u.user_id=?1 and u.userStatus = 1", nativeQuery = true)
    List<User> userList(Integer userId);
}
