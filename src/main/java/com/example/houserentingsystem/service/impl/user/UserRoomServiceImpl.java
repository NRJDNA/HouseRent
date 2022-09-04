package com.example.houserentingsystem.service.impl.user;


import com.example.houserentingsystem.component.authorizeUser.AuthorizeUser;
import com.example.houserentingsystem.dto.admin.AdminRoomDto;
import com.example.houserentingsystem.dto.user.UserRoomDto;
import com.example.houserentingsystem.enums.RoomStatus;
import com.example.houserentingsystem.model.admin.adminRoom.AdminRoom;
import com.example.houserentingsystem.model.user.userRoom.UserRoom;
import com.example.houserentingsystem.repo.admin.AdminRoomRepo;
import com.example.houserentingsystem.repo.user.RegisterRepo;
import com.example.houserentingsystem.repo.user.UserRoomRepo;
import com.example.houserentingsystem.service.user.UserRoomService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoomServiceImpl implements UserRoomService {
    private final UserRoomRepo userRoomRepo;
    private final RegisterRepo registerRepo;
    private final AdminRoomDto adminRoomDto;
    private final AdminRoom adminRoom;



    public UserRoomServiceImpl(UserRoomRepo userRoomRepo, RegisterRepo registerRepo, AdminRoomRepo adminRoomRepo, AdminRoom adminRoom, AdminRoomDto adminRoomDto, AdminRoom adminRoom1) {
        this.userRoomRepo = userRoomRepo;
        this.registerRepo=registerRepo;

        this.adminRoomDto = adminRoomDto;
        this.adminRoom = adminRoom1;
    }

    @Override
    public UserRoomDto save(UserRoomDto userRoomDto) throws ParseException, IOException {
        UserRoom entity = new UserRoom();
//        if (imageDto.isStatus()) {
            entity.setId(userRoomDto.getId());
            entity.setName(userRoomDto.getName());
            entity.setContact(userRoomDto.getContact());
            entity.setAddress(userRoomDto.getAddress());
            entity.setEmail(userRoomDto.getEmail());
            entity.setRoomType(userRoomDto.getRoomType());
            entity.setUserRoomDate(userRoomDto.getUserRoomDate());
            //            entity.setUserRoomDate(new Date());
//        entity.getUserRoomDate(userRoomDto.getUserRoomDate());
            entity.setRoomStatus(userRoomDto.getRoomStatus());
            entity.setDescription(userRoomDto.getDescription());
//            entity.setRentedBy(adminRoomDto.getName());
            entity.setRegister(AuthorizeUser.getRegister());


            if (entity.getId() == null) {
                entity.setRoomStatus(RoomStatus.AVAILABLE);
            } else {
                entity.setRoomStatus(userRoomDto.getRoomStatus());
                entity.setRegister(userRoomDto.getRegister());
            }
            userRoomRepo.save(entity);

            return userRoomDto;
    }
    @Override
    public List<UserRoomDto> findAll() throws IOException {
        List<UserRoomDto> userRoomList = new ArrayList<>();
        List<UserRoom> userRoomList1 = userRoomRepo.getUserRoomList(AuthorizeUser.getRegister().getId());
//        List<UserRoom> userRoomList2 = userRoomRepo.getUserRoomList(AuthorizeUser.getRegister().getId());
        for (UserRoom userRoom : userRoomList1) {
            userRoomList.add(UserRoomDto.builder()
                    .id(userRoom.getId())
                    .name(userRoom.getName())
                    .contact(userRoom.getContact())
                    .address(userRoom.getAddress())
                    .email(userRoom.getEmail())
                    .roomType(userRoom.getRoomType())
                    .userRoomDate(userRoom.getUserRoomDate())
                    .roomStatus(userRoom.getRoomStatus())

//                    .userRoomDate(userRoom.getUserRoomDate())
                    .description(userRoom.getDescription())

                    .register(userRoom.getRegister())
//                    .register(userRoom.getRegister())
                    .build());
        }
//        for (UserRoom userRoom : userRoomList2) {
//            userRoomList.add(UserRoomDto.builder()
//                    .id(userRoom.getId())
//                    .address(userRoom.getAddress())
//                    .roomType(userRoom.getRoomType())
//                    .userRoomDate(new SimpleDateFormat("yyyy-MM-dd").format(userRoom.getUserRoomDate()))
////                    .userRoomStatus(userRoom.getUserRoomStatus())
////                    .userRoomDate(userRoom.getUserRoomDate())
//                    .description(userRoom.getDescription())
//                    .register(userRoom.getRegister())
////                    .register(userRoom.getRegister())
//                    .build());
//        }
        return userRoomList;
    }

        @Override
    public UserRoomDto findById(Integer integer) throws IOException {
            UserRoom userRoom;
            Optional<UserRoom> optionalUserRoom=userRoomRepo.findById(integer);
            if (optionalUserRoom.isPresent())
            {
                userRoom=optionalUserRoom.get();
                return   UserRoomDto.builder()
                        .id(userRoom.getId())
                        .name(userRoom.getName())
                        .contact(userRoom.getContact())
                        .address(userRoom.getAddress())
                        .email(userRoom.getEmail())
                        .roomType(userRoom.getRoomType())
//                        .userRoomDate(userRoom.getUserRoomDate())
                        .userRoomDate(userRoom.getUserRoomDate())
                        .roomStatus(userRoom.getRoomStatus())
//                        .register(userRoom.getRegister())
                        .description(userRoom.getDescription())
                        .register(userRoom.getRegister())
                        .build();
            }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

        userRoomRepo.deleteById(integer);
    }
//    public Integer getTotalUserRoom()
//    {
//        Integer totalUserRoom=Integer.valueOf(userRoomRepo.getTotalUserRoom());
//        return totalUserRoom;
//    }
//    public Integer getTotalPending()
//    {
//        Integer totalPending=Integer.valueOf(userRoomRepo.getPendingUserRoom());
//        return totalPending;
//    }
//    public Integer getTotalApprove()
//    {
//        Integer totalApprove=Integer.valueOf(userRoomRepo.getApproveUserRoom());
//        return totalApprove;
//    }


    public UserRoomDto updateUserRoom(UserRoomDto userRoomDto) throws ParseException {
        UserRoom entity=new UserRoom();
        entity.setId(userRoomDto.getId());
        entity.setName(userRoomDto.getName());
        entity.setContact(userRoomDto.getContact());
        entity.setAddress(userRoomDto.getAddress());
        entity.setEmail(userRoomDto.getEmail());
        entity.setRoomType(userRoomDto.getRoomType());
        entity.setUserRoomDate(userRoomDto.getUserRoomDate());
        entity.setDescription(userRoomDto.getDescription());
        entity.setRegister(AuthorizeUser.getRegister());
        userRoomRepo.save(entity);
        return userRoomDto;
    }

    public List<UserRoomDto> findAllUserRoom(){
        List<UserRoomDto> userRoomList = new ArrayList<>();
        List<UserRoom> userRoomList1=userRoomRepo.getUserRoomDetails();
        for(UserRoom userRoom : userRoomList1){
            userRoomList.add(UserRoomDto.builder()
                            .id(userRoom.getId())
                            .name(userRoom.getName())
                            .contact(userRoom.getContact())
                            .email(userRoom.getEmail())
                            .roomStatus(userRoom.getRoomStatus())
                            .userRoomDate(userRoom.getUserRoomDate())
                            .description(userRoom.getDescription())
                            .address(userRoom.getAddress())
                            .roomType(userRoom.getRoomType())
                    .register(userRoom.getRegister())
                    .build());
        }
        return userRoomList;
    }

    public UserRoomDto findId(Integer integer){

        return null;

    }

    public Integer getTotalRented(){
        Integer totalRented=Integer.valueOf(userRoomRepo.getAvailableUserRoom());
        return totalRented;
    }

    public Integer getTotalAvailable(){
        Integer totalAvailable = Integer.valueOf(userRoomRepo.getAvailableUserRoom());
        return totalAvailable;
    }


}
