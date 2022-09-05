package com.example.houserentingsystem.service.impl.user;

import com.example.houserentingsystem.component.authorizeUser.AuthorizeUser;
import com.example.houserentingsystem.dto.user.RegisterDto;
import com.example.houserentingsystem.enums.UserStatus;
import com.example.houserentingsystem.model.User;
import com.example.houserentingsystem.model.register.Register;
import com.example.houserentingsystem.repo.user.RegisterRepo;
import com.example.houserentingsystem.service.impl.UserServiceImpl;
import com.example.houserentingsystem.service.user.RegisterService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final RegisterRepo registerRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;

    public RegisterServiceImpl(RegisterRepo registerRepo, BCryptPasswordEncoder passwordEncoder, UserServiceImpl userService) {
        this.registerRepo = registerRepo;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public RegisterDto save(RegisterDto registerDto) throws ParseException {
        Register register = Register.builder()
                .id(registerDto.getId())
                .name(registerDto.getName())
                .nationalIdNumber(registerDto.getCitizenshipNo())
                .gender(registerDto.getGender())
                .email(registerDto.getEmail())
                .mobileNumber(registerDto.getMobileNumber())
                .build();
        //save into database
        Register register1 = registerRepo.save(register);

        //save into user table
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUserStatus(UserStatus.USER);
        userService.save(user);

        return RegisterDto.builder().id(register1.getId()).build();
    }

    @Override
    public List<RegisterDto> findAll() {
        List<RegisterDto> registerList=new ArrayList<>();
        List<Register> registerList1 =registerRepo.getRegisterList(AuthorizeUser.getRegister().getId());
        for(Register register : registerList1){
            registerList.add(RegisterDto.builder()
                    .id(register.getId())
                    .name(register.getName())
                    .mobileNumber(register.getMobileNumber())
                    .email(register.getEmail())
                    .citizenshipNo(register.getNationalIdNumber())
                    .build());
        }
        return registerList;
    }

    @Override
    public RegisterDto findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    public Register findRegisterByEmail(String email) {
        return registerRepo.findUserByEmail(email);
    }
}
