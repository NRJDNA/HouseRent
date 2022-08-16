package com.example.houserentingsystem.dto.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
    private boolean status;
    private String message;
}
