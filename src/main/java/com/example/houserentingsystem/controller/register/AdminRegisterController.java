package com.example.houserentingsystem.controller.register;


import com.example.houserentingsystem.component.SendEmailComponents;
import com.example.houserentingsystem.dto.admin.AdminRegisterDto;
import com.example.houserentingsystem.service.impl.admin.AdminRegisterServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminRegister")
public class AdminRegisterController {
    private final AdminRegisterServiceImpl adminRegisterService;
    private final SendEmailComponents sendEmailComponents;

    public AdminRegisterController(AdminRegisterServiceImpl adminRegisterService, SendEmailComponents sendEmailComponents) {
        this.adminRegisterService = adminRegisterService;
        this.sendEmailComponents = sendEmailComponents;
    }

    @GetMapping
    public String openAdminPage(Model model) {
        model.addAttribute("adminRegisterDto", new AdminRegisterDto());
        return "admin/adminRegisterPage";
    }

    @PostMapping
    public String createAdmin(@Valid @ModelAttribute AdminRegisterDto adminRegisterDto,
                              BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                sendEmailComponents.sendEmail(adminRegisterDto.getEmail(),
                        "Registration",
                        "Hello Mr/Mrs. "+adminRegisterDto.getName()+"\n As a Renter Your Registration has been successful");
                adminRegisterDto = adminRegisterService.save(adminRegisterDto);
                model.addAttribute("message", "Admin register successfully added!");
            } catch (Exception e) {
                model.addAttribute("message", "Admin register failed added!!");
                e.printStackTrace();
            }
        }
        model.addAttribute("adminRegisterDto", adminRegisterDto);
        return "admin/adminRegisterPage";
    }


}
