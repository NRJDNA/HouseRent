package com.example.houserentingsystem.service.impl.user;


import com.example.houserentingsystem.component.authorizeUser.AuthorizeUser;
import com.example.houserentingsystem.dto.admin.AdminRoomDto;
import com.example.houserentingsystem.dto.user.UserRoomDto;
import com.example.houserentingsystem.enums.RoomStatus;
import com.example.houserentingsystem.model.user.userRoom.UserRoom;
import com.example.houserentingsystem.repo.admin.AdminRoomRepo;
import com.example.houserentingsystem.repo.user.RegisterRepo;
import com.example.houserentingsystem.repo.user.UserRoomRepo;
import com.example.houserentingsystem.service.impl.admin.AdminRegisterServiceImpl;
import com.example.houserentingsystem.service.user.UserRoomService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final AdminRegisterServiceImpl adminRegisterService;


    public UserRoomServiceImpl(UserRoomRepo userRoomRepo, RegisterRepo registerRepo, AdminRoomRepo adminRoomRepo, AdminRegisterServiceImpl adminRegisterService) {
        this.userRoomRepo = userRoomRepo;
        this.registerRepo=registerRepo;

        this.adminRegisterService = adminRegisterService;
    }

    @Override
    public UserRoomDto save(UserRoomDto userRoomDto) throws ParseException, IOException {
        UserRoom entity = new UserRoom();
            entity.setId(userRoomDto.getId());
            entity.setName(userRoomDto.getName());
            entity.setContact(userRoomDto.getContact());
            entity.setAddress(userRoomDto.getAddress());
            entity.setEmail(userRoomDto.getEmail());
            entity.setRoomType(userRoomDto.getRoomType());
            entity.setUserRoomDate(userRoomDto.getUserRoomDate());
            entity.setRoomStatus(userRoomDto.getRoomStatus());
            entity.setDescription(userRoomDto.getDescription());
            entity.setRegister(AuthorizeUser.getRegister());


            if (entity.getId() == null) {
                entity.setRoomStatus(RoomStatus.AVAILABLE);
            } else {
                entity.setRoomStatus(userRoomDto.getRoomStatus());
                entity.setRentedBy(adminRegisterService.findAll().get(0).getName());
                entity.setRegister(userRoomDto.getRegister());
            }
            userRoomRepo.save(entity);

            return userRoomDto;
    }
    @Override
    public List<UserRoomDto> findAll() throws IOException {
        List<UserRoomDto> userRoomList = new ArrayList<>();
        List<UserRoom> userRoomList1 = userRoomRepo.getUserRoomList(AuthorizeUser.getRegister().getId());
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
                    .rentedBy(userRoom.getRentedBy())
                    .description(userRoom.getDescription())
                    .register(userRoom.getRegister())
                    .build());
        }
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
                        .userRoomDate(userRoom.getUserRoomDate())
                        .roomStatus(userRoom.getRoomStatus())
                        .rentedBy(userRoom.getRentedBy())
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
                            .rentedBy(userRoom.getRentedBy())
                    .register(userRoom.getRegister())
                    .build());
        }
        return userRoomList;
    }




}
