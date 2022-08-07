package com.example.houserentingsystem.component.authorizeUser;

import com.example.houserentingsystem.enums.UserStatus;
import com.example.houserentingsystem.model.admin.AdminRegister;
import com.example.houserentingsystem.model.register.Register;

public class AuthorizeUser {
    private static Register register;
    private static AdminRegister adminRegister;
    private static UserStatus userStatus;

    public static  Register getRegister() {
        return register;
    }

    public static void setRegister(Register register) {
        AuthorizeUser.register = register;
        AuthorizeUser.userStatus = userStatus.USER;

    }

    public static AdminRegister getAdminRegister() {
        return adminRegister;
    }

    public static void setAdminRegister(AdminRegister adminRegister) {
        AuthorizeUser.adminRegister = adminRegister;
        AuthorizeUser.userStatus = userStatus.ADMIN;
    }

    public static UserStatus getUserStatus() {
        return userStatus;
    }

    public static void setUserStatus(UserStatus userStatus) {
        AuthorizeUser.userStatus = userStatus;
    }


}
