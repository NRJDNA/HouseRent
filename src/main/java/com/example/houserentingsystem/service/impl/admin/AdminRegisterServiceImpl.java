package com.example.houserentingsystem.service.impl.admin;
import com.example.houserentingsystem.dto.admin.AdminRegisterDto;
import com.example.houserentingsystem.enums.UserStatus;
import com.example.houserentingsystem.model.User;
import com.example.houserentingsystem.model.admin.AdminRegister;
import com.example.houserentingsystem.repo.admin.AdminRegisterRepo;
import com.example.houserentingsystem.service.admin.AdminRegisterService;
import com.example.houserentingsystem.service.impl.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class AdminRegisterServiceImpl implements AdminRegisterService {
    private final AdminRegisterRepo adminRegisterRepo;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminRegisterServiceImpl(AdminRegisterRepo adminRegisterRepo, UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder) {
        this.adminRegisterRepo = adminRegisterRepo;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdminRegisterDto save(AdminRegisterDto adminRegisterDto) throws ParseException {
        AdminRegister adminRegister = new AdminRegister();
        adminRegister.setId(adminRegisterDto.getId());
        adminRegister.setName(adminRegisterDto.getName());
        adminRegister.setAddress(adminRegisterDto.getAddress());
        adminRegister.setGender(adminRegisterDto.getGender());
        adminRegister.setEmail(adminRegisterDto.getEmail());
        adminRegister.setContact(adminRegisterDto.getContact());
//        adminRegister.setPost(adminRegisterDto.getPost());
        adminRegister.setIdNumber(adminRegisterDto.getCitizenshipNo());

        AdminRegister adminRegister1 = adminRegisterRepo.save(adminRegister);

        User user = new User();
        user.setEmail(adminRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(adminRegisterDto.getPassword()));
        user.setUserStatus(UserStatus.ADMIN);
        userService.save(user);
        return AdminRegisterDto.builder().id(adminRegister1.getId()).build();
    }

    @Override
    public List<AdminRegisterDto> findAll() {
        return null;
    }

    @Override
    public AdminRegisterDto findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    public AdminRegister findAdminByEmail(String email) {
        return adminRegisterRepo.findAdminRegisterByEmail(email);
    }
}