package com.example.houserentingsystem.controller;

import com.example.houserentingsystem.component.authorizeUser.AuthorizeUser;
import com.example.houserentingsystem.model.admin.AdminRegister;
import com.example.houserentingsystem.model.register.Register;
import com.example.houserentingsystem.service.impl.admin.AdminRegisterServiceImpl;
import com.example.houserentingsystem.service.impl.user.RegisterServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class MainController {
    private final RegisterServiceImpl registerService;
    private final AdminRegisterServiceImpl adminRegisterService;

    public MainController(RegisterServiceImpl registerService, AdminRegisterServiceImpl adminRegisterService) {
        this.registerService = registerService;
        this.adminRegisterService = adminRegisterService;
    }

    @GetMapping("/")
    public String openUserHomePage() {
        if (AuthorizeUser.getUserStatus().ordinal() == 0) {
            Register register = registerService.findRegisterByEmail(AuthorizeUser.getRegister().getEmail());
            AuthorizeUser.setRegister(null);
            //asign again
            AuthorizeUser.setRegister(register);
            return "user/userLandingPage";
        } else if (AuthorizeUser.getUserStatus().ordinal() == 1) {
            AdminRegister adminRegister = adminRegisterService.findAdminByEmail(AuthorizeUser.getAdminRegister().getEmail());
            AuthorizeUser.setAdminRegister(null);
            AuthorizeUser.setAdminRegister(adminRegister);
            return "admin/adminLandingPage";
        } else {
            return null;
        }
    }

    @GetMapping("/home")
    public String openHomePage() {
        return "homePage";
    }

    @GetMapping("/login")
    public String openLogin() {
        return "loginPage";
    }
}
