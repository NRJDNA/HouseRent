package com.example.houserentingsystem.dto.user;

import com.example.houserentingsystem.enums.RoomStatus;
import com.example.houserentingsystem.enums.RoomType;
import com.example.houserentingsystem.model.register.Register;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class UserRoomDto {
    private Integer id;

    @NotEmpty(message = "Name can not be empty !!")
    private String name;

    private String contact;

    @NotEmpty(message = "Address can not be empty!!")
    private String address;
//    @NotEmpty(message = "Type of Room can't be empty")
    private RoomType roomType;

    private String userRoomDate;

    private RoomStatus roomStatus;
    private Register register;

    private String filePath; //photos

    private MultipartFile multipartFile;


    @NotEmpty(message = "Description can not be empty!!")
    private String description;
}
