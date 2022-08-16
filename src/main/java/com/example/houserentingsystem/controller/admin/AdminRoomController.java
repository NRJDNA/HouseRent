package com.example.houserentingsystem.controller.admin;

import com.example.houserentingsystem.dto.admin.AdminRoomDto;
import com.example.houserentingsystem.dto.user.UserRoomDto;
import com.example.houserentingsystem.model.User;
import com.example.houserentingsystem.model.admin.adminRoom.AdminRoom;
import com.example.houserentingsystem.service.impl.admin.AdminRoomServiceImpl;
import com.example.houserentingsystem.service.impl.user.RegisterServiceImpl;
import com.example.houserentingsystem.service.impl.user.UserRoomServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/adminRoom")
public class AdminRoomController {
    private final AdminRoomServiceImpl adminRoomService;
    private final RegisterServiceImpl registerService;
    private final UserRoomServiceImpl userRoomService;

    public AdminRoomController(AdminRoomServiceImpl adminRoomService,RegisterServiceImpl registerService,UserRoomServiceImpl userRoomService) {
        this.adminRoomService=adminRoomService;
        this.registerService=registerService;
        this.userRoomService=userRoomService;
    }

    @GetMapping("/home")
    public String openHome(Model model){
               model.addAttribute("adminRoomDto",new AdminRoomDto());
        model.addAttribute("adminRoomList",adminRoomService.findAll());

        return "admin/adminRoomHome";
    }
//    @GetMapping("/home")
//    public String openShowRoom(Model model) {
//        model.addAttribute("userRoomDto", new UserRoomDto());
//        model.addAttribute("userRoomList", userRoomService.findAll());
////        model.addAttribute("userName",registerService.findAll());
//        return "user/userRoomHome";
//    }
    @GetMapping("/page")
    public String adminRoomPage(Model model){
        model.addAttribute("adminRoomDto",new AdminRoomDto());
        return "admin/adminRoomPage";
    }
    @GetMapping("/show")
    public String showRoom(Model model) throws IOException{
        model.addAttribute("userRoomList",userRoomService.findAllUserRoom());
        model.addAttribute("registerList",registerService.findAll());
        return "admin/userRoomHome";
    }

    @GetMapping("/rented/{id}")
    public String verifyRoom(@PathVariable("id") Integer id) {
        UserRoomDto userRoomDto=userRoomService.findById(id);
        UserRoomDto.setUserRoomStatus(UserRoomStatus.RENTED);
        userRoomService.save(userRoomDto);
        return "redirect:/adminRoom/show";
    }

    @PostMapping("/create")
    public String createAdminRoom(@Valid @ModelAttribute AdminRoomDto adminRoomDto, BindingResult bindingResult,Model model){
        if(!bindingResult.hasErrors()){
            try{
                adminRoomDto=adminRoomService.save(adminRoomDto);
                model.addAttribute("message","adminRoom Successfully Added");
            }catch (Exception e){
                model.addAttribute("message","adminRoom Failed to Added");
                e.printStackTrace();
            }
        }
        model.addAttribute("adminRoomDto",adminRoomDto);
        return "admin/adminRoomPage";
    }
    @GetMapping("/view/{id}")
    public String viewAdminRoom(@PathVariable("id") Integer id,Model model)
    {
        model.addAttribute("adminRoomView",adminRoomService.findById(id));
        return "admin/viewAdminRoom";
    }
    @GetMapping("/update/{id}")
    public String updateAdminRoom(@PathVariable ("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        AdminRoomDto adminRoomDto = adminRoomService.findById(id);
        model.addAttribute("adminRoomDto",adminRoomDto);
        return "admin/adminRoomUpdatePage";
    }
    @PostMapping("/update")
    public String updateAdminRoom(@Valid @ModelAttribute AdminRoomDto adminRoomDto,BindingResult bindingResult,Model model){
        if(!bindingResult.hasErrors()){
            try{
                adminRoomDto=adminRoomService.updateAdminRoom(adminRoomDto);
                model.addAttribute("message","AdminRoom Update Successfully");
            }catch (Exception e){
                model.addAttribute("message","AdminRoom Update Failed");
                e.printStackTrace();
            }
        }
        model.addAttribute("adminRoomDto",adminRoomDto);
        return "admin/adminRoomUpdatePage";
    }


    @GetMapping("/delete/{id}")
    public String deleteAdminRoom(@PathVariable("id") Integer id , Model model){
        adminRoomService.deleteById(id);
        return "redirect:/admin/show";
    }

    /*
    @GetMapping("/delete/{id}")
    public String deleteUserRoom(@PathVariable("id") Integer id , Model model){
        userRoomService.deleteById(id);
        return "redirect:/admin/show";
    }
     */
}
