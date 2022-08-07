package com.example.houserentingsystem.dto.admin;

import com.example.houserentingsystem.enums.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRegisterDto {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String contact;
    private Gender gender;
    private String citizenshipNo;
    private String post;
    private String password;
}