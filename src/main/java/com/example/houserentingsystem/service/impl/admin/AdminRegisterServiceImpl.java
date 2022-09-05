package com.example.houserentingsystem.service.impl.admin;
import com.example.houserentingsystem.component.SendEmailComponents;
import com.example.houserentingsystem.component.authorizeUser.AuthorizeUser;
import com.example.houserentingsystem.dto.admin.AdminRegisterDto;
import com.example.houserentingsystem.enums.UserStatus;
import com.example.houserentingsystem.model.User;
import com.example.houserentingsystem.model.admin.AdminRegister;
import com.example.houserentingsystem.repo.admin.AdminRegisterRepo;
import com.example.houserentingsystem.service.admin.AdminRegisterService;
import com.example.houserentingsystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminRegisterServiceImpl implements AdminRegisterService {
    private final AdminRegisterRepo adminRegisterRepo;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SendEmailComponents sendEmailComponents;

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
        List<AdminRegisterDto> adminRegisterList= new ArrayList<>();
        List<AdminRegister> adminRegisterList1 = adminRegisterRepo.getAdminRegisterList(AuthorizeUser.getAdminRegister().getId());
for(AdminRegister adminRegister : adminRegisterList1){
    adminRegisterList.add(AdminRegisterDto.builder()
            .id(adminRegister.getId())
            .name(adminRegister.getName())
            .contact(adminRegister.getContact())
            .email(adminRegister.getEmail())
            .citizenshipNo(adminRegister.getIdNumber())
            .build());
}
        return adminRegisterList;
    }

    @Override
    public AdminRegisterDto findById(Integer integer) {
        AdminRegister adminRegister;
        Optional<AdminRegister> optionalAdminRegister= adminRegisterRepo.findById(integer);
        if(optionalAdminRegister.isPresent()){
            adminRegister= optionalAdminRegister.get();
            return AdminRegisterDto.builder()
                    .id(adminRegister.getId())
                    .name(adminRegister.getName())
                    .contact(adminRegister.getContact())

                    .build();
        }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    public AdminRegister findAdminByEmail(String email) {
        return adminRegisterRepo.findAdminRegisterByEmail(email);
    }
}
