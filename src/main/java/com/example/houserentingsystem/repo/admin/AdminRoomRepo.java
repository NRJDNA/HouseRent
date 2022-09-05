package com.example.houserentingsystem.repo.admin;

import com.example.houserentingsystem.model.admin.adminRoom.AdminRoom;
import com.example.houserentingsystem.model.user.userRoom.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author dangal_nirajan on 10/08/2022
 */
public interface AdminRoomRepo extends JpaRepository<AdminRoom , Integer> {
    @Query(value = "SELECT * FROM admin_admin_room u WHERE u.admin_register_id = ?1", nativeQuery = true)
    List<AdminRoom> getAdminRoomList(Integer id);
//    @Modifying
//    @Transactional
//    @Query(value = "update admin_admin_room set email =?1 , price =?2 where id=?3 ", nativeQuery = true)
//    void updateAdminRoom(String email , String price, Integer id);

    @Query(value = "select * from admin_admin_room u  order by u.id",nativeQuery = true)
    List<AdminRoom> getAdminRoomDetails();

    @Query(value = "SELECT a FROM AdminRoom a WHERE CONCAT(a.address,a.price,a.roomType) LIKE %?1% ")
    List<AdminRoom> search(String keyword);

    @Query(value = "select count(ur.user_room_status)from user_user_room ur where ur.user_room_status= 0",nativeQuery = true)
    String getAvailableUserRoom();

    @Query(value = "select count(ur.user_room_status)from user_user_room ur where ur.user_room_status=1",nativeQuery = true)
    String getRentedUserRoom();

}
