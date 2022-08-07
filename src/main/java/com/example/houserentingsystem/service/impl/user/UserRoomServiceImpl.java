package com.example.houserentingsystem.service.impl.user;


import com.example.houserentingsystem.dto.user.UserRoomDto;
import com.example.houserentingsystem.model.user.userRoom.UserRoom;
import com.example.houserentingsystem.repo.user.UserRoomRepo;
import com.example.houserentingsystem.service.user.UserRoomService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserRoomServiceImpl implements UserRoomService {
    private final UserRoomRepo userRoomRepo;

    public UserRoomServiceImpl(UserRoomRepo userRoomRepo) {
        this.userRoomRepo = userRoomRepo;
    }

    @Override
    public UserRoomDto save(UserRoomDto userRoomDto) throws ParseException {
        UserRoom entity = new UserRoom();
        entity.setId(userRoomDto.getId());
        entity.setAddress(userRoomDto.getAddress());
        entity.setRoomType(userRoomDto.getRoomType());
        entity.setUserRoomDate(new SimpleDateFormat("dd-MM-yyyy").parse(userRoomDto.getUserRoomDate()));
        entity.setUserRoomDate(new Date());
        entity.setDescription(userRoomDto.getDescription());
        entity = userRoomRepo.save(entity);

        return userRoomDto;
    }

    @Override
    public List<UserRoomDto> findAll() {
        return null;
    }

    @Override
    public UserRoomDto findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
