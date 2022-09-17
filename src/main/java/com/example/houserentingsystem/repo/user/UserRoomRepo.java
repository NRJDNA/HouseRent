package com.example.houserentingsystem.repo.user;

import com.example.houserentingsystem.model.user.userRoom.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRoomRepo extends JpaRepository<UserRoom, Integer> {
    @Query(value = "SELECT * FROM user_user_room u WHERE u.register_id = ?1", nativeQuery = true)
    List<UserRoom> getUserRoomList(Integer userId);


    @Query(value = "select count(ur.user_room_status)from user_userRoom ur where ur.user_room_status= 0",nativeQuery = true)
    String getAvailableUserRoom();


    @Query(value = "select * from user_user_room u  order by u.id",nativeQuery = true)
    List<UserRoom> getUserRoomDetails();


//    @Modifying
//    @Transactional
//    @Query(value = "update user_user_room u set u.rentedBy = ?1 where u.id =?2")
//    public void setRentedBy(String adminName, Integer id);


}
