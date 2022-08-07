package com.example.houserentingsystem.repo.user;

import com.example.houserentingsystem.model.user.userRoom.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoomRepo extends JpaRepository<UserRoom, Integer> {
    @Query(value = "SELECT * FROM user_userRoom u WHERE u.Register_id = ?1", nativeQuery = true)
    List<UserRoom> getUserRoomList(Integer userId);
}
