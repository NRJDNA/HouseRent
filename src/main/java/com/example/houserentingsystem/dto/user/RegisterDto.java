package com.example.houserentingsystem.dto.user;

import com.example.houserentingsystem.enums.Gender;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class RegisterDto {
    private int id;

    @NotEmpty(message = "Number can not be empty!!")
    private String name;

    @NotEmpty(message = "National id can not be empty!!")
    private String citizenshipNo;

    private Gender gender;

    @NotEmpty(message = "Email can not be empty!!")
    private String email;

    @Size(max = 10, min = 10, message = "MobileNumber should be 10 .")
    @NotEmpty(message = "Mobile number can not be empty!!")
    private String mobileNumber;

    @NotEmpty(message = "Password can not be empty!!")
    private String password;
}
