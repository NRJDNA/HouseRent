package com.example.houserentingsystem.controller.user.userRoom;


import com.example.houserentingsystem.dto.user.UserRoomDto;
import com.example.houserentingsystem.service.impl.user.RegisterServiceImpl;
import com.example.houserentingsystem.service.impl.user.UserRoomServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/userRoom")
public class UserRoomController {
    private final UserRoomServiceImpl userRoomService;
    private final RegisterServiceImpl registerService;

    public UserRoomController(UserRoomServiceImpl userRoomService, RegisterServiceImpl registerService) {
        this.userRoomService = userRoomService;
        this.registerService = registerService;
    }

    @GetMapping("/home")
    public String openHome(Model model) {
        model.addAttribute("userRoomDto", new UserRoomDto());
        model.addAttribute("userRoomList", userRoomService.findAll());
//        model.addAttribute("userName",registerService.findAll());
        return "user/userRoomHome";
    }

    @GetMapping("/page")
    public String userRoomPage(Model model) {
        model.addAttribute("complainDto", new UserRoomDto());
        return "user/userRoomPage";
    }

    @PostMapping("/create")
    public String createUserRoom(@Valid @ModelAttribute UserRoomDto userRoomDto,
                                 BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                userRoomDto = userRoomService.save(userRoomDto);
                model.addAttribute("message", "userRoomm successfully added");
            } catch (Exception e) {
                model.addAttribute("message", "userRoom failed added");
                e.printStackTrace();
            }
        }
        model.addAttribute("userRoomDto", userRoomDto);
        return "user/userRoomPage";
    }
}