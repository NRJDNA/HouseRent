package com.example.houserentingsystem.repo.admin;

import com.example.houserentingsystem.model.admin.adminRoom.AdminRoom;
import com.example.houserentingsystem.model.user.userRoom.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dangal_nirajan on 10/08/2022
 */
public interface AdminRoomRepo extends JpaRepository<AdminRoom , Integer> {
    @Query(value = "SELECT * FROM admin_admin_room u WHERE u.admin_register_id = ?1", nativeQuery = true)
    List<AdminRoom> getAdminRoomList(Integer id);

    @Query(value = "select * from admin_admin_room u  order by u.id",nativeQuery = true)
    List<AdminRoom> getAdminRoomDetails();

    @Query(value = "SELECT a FROM AdminRoom a WHERE CONCAT(a.address,a.price,a.roomType) LIKE %?1% ")
    List<AdminRoom> search(String keyword);

    @Query(value = "select count(ur.user_room_status)from user_user_room ur where ur.user_room_status= 0",nativeQuery = true)
    String getAvailableUserRoom();

    @Query(value = "select count(ur.user_room_status)from user_user_room ur where ur.user_room_status=1",nativeQuery = true)
    String getRentedUserRoom();

//    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, p.brand, p.madein, p.price) LIKE %?1%")
//public List<Product> search(String keyword);
}
