package com.example.houserentingsystem.service.admin;


import com.example.houserentingsystem.dto.admin.AdminRegisterDto;
import com.example.houserentingsystem.service.GenericCrudServiceImpl;
import org.springframework.data.jpa.repository.Query;

public interface AdminRegisterService extends GenericCrudServiceImpl<AdminRegisterDto, Integer> {
}
