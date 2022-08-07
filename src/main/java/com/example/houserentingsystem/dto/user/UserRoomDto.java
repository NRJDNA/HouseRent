package com.example.houserentingsystem.dto.user;

import com.example.houserentingsystem.enums.RoomType;
import lombok.*;

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

    @NotEmpty(message = "Address can not be empty!!")
    private String address;
    @NotEmpty(message = "Type of Room can't be empty")
    private RoomType roomType;

    private String userRoomDate;

    private Date complainDate;

    @NotEmpty(message = "Description can not be empty!!")
    private String description;
}
