package com.example.houserentingsystem.controller.register;

import com.example.houserentingsystem.dto.user.RegisterDto;
import com.example.houserentingsystem.service.impl.user.RegisterServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegisterController {
    private final RegisterServiceImpl registerService;

    public UserRegisterController(RegisterServiceImpl registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerDto", new RegisterDto());

        return "user/registration";
    }

    @PostMapping
    public String createPage(@Valid @ModelAttribute RegisterDto registerDto,
                             BindingResult bindingResult, Model model) {
        //check the binding result
        if (!bindingResult.hasErrors()) {
            try {
                //save the database
                registerDto = registerService.save(registerDto);
                model.addAttribute("message", "register successfully added");
            } catch (Exception e) {
                model.addAttribute("message", "register failed added!!try again ?");
                e.printStackTrace();
            }
        }
        model.addAttribute("registerDto", registerDto);
        //return
//        return "redirect:/registration?success";
        return "user/registration";
    }

}
