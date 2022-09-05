package com.example.houserentingsystem.controller.admin;

//import com.example.houserentingsystem.component.SendEmailComponents;
import com.example.houserentingsystem.dto.admin.AdminRegisterDto;
import com.example.houserentingsystem.dto.admin.AdminRoomDto;
import com.example.houserentingsystem.dto.user.UserRoomDto;
import com.example.houserentingsystem.enums.RoomStatus;
import com.example.houserentingsystem.service.impl.admin.AdminRegisterServiceImpl;
import com.example.houserentingsystem.service.impl.admin.AdminRoomServiceImpl;
import com.example.houserentingsystem.service.impl.user.RegisterServiceImpl;
import com.example.houserentingsystem.service.impl.user.UserRoomServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/adminRoom")
public class AdminRoomController {
    private final AdminRoomServiceImpl adminRoomService;
    private final RegisterServiceImpl registerService;
    private final UserRoomServiceImpl userRoomService;
    private final AdminRegisterServiceImpl adminRegisterService;

    public AdminRoomController(AdminRoomServiceImpl adminRoomService, RegisterServiceImpl registerService, UserRoomServiceImpl userRoomService, AdminRegisterServiceImpl adminRegisterService) {
        this.adminRoomService=adminRoomService;
        this.registerService=registerService;
        this.userRoomService=userRoomService;
        this.adminRegisterService = adminRegisterService;
    }

    @GetMapping("/home")
    public String openHome(Model model) throws IOException {
               model.addAttribute("adminRoomDto",new AdminRoomDto());
        model.addAttribute("adminRoomList",adminRoomService.findAll());

        return "admin/adminRoomHome";
    }

    @GetMapping("/about")
    public String openAbout(Model model){
        model.addAttribute("adminRegisterDto",new AdminRegisterDto());
        model.addAttribute("adminRegisterList",adminRegisterService.findAll());
        return "admin/about";
    }
    @GetMapping("/page")
    public String adminRoomPage(Model model){
        model.addAttribute("adminRoomDto",new AdminRoomDto());
        return "admin/adminRoomPage";
    }
    @GetMapping("/show")
    public String showRoom(Model model) throws IOException{
        model.addAttribute("userRoomList",userRoomService.findAllUserRoom());
        model.addAttribute("registerList",adminRegisterService.findAll());
        return "admin/userRoomHome";
    }

    @GetMapping("/rented/{id}")
    public String verifyRoom(@PathVariable("id") Integer id) throws ParseException, IOException {
        UserRoomDto userRoomDto=userRoomService.findById(id);
        userRoomDto.setRoomStatus(RoomStatus.RENTED);
        userRoomService.save(userRoomDto);
        return "redirect:/adminRoom/show";
    }
    @GetMapping("/unrented/{id}")
    public String unverifyRoom(@PathVariable("id") Integer id) throws ParseException, IOException {
        UserRoomDto userRoomDto=userRoomService.findById(id);
        userRoomDto.setRoomStatus(RoomStatus.AVAILABLE);
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
    public String viewAdminRoom(@PathVariable("id") Integer id,Model model) throws IOException {
        model.addAttribute("adminRoomView",adminRoomService.findById(id));
        return "admin/viewAdminRoom";
    }
    @GetMapping("/uview/{id}")
    public String viewUserRoom(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("userRoomView",userRoomService.findById(id));
        return "admin/viewUserRoom";
    }
    @GetMapping("/update/{id}")
    public String updateAdminRoom(@PathVariable ("id") Integer id, Model model, RedirectAttributes redirectAttributes) throws IOException {
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
        return "redirect:/adminRoom/home";
    }


}
