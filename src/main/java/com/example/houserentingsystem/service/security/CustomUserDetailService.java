package com.example.houserentingsystem.service.security;

import com.example.houserentingsystem.config.configUser.CustomUserDetails;
import com.example.houserentingsystem.model.User;
import com.example.houserentingsystem.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //find the user name
        User userByEmail = userRepo.findUserByEmail(username);
        //if user by email null
        if (userByEmail == null) {
            //print the user not fount expection
            throw new UsernameNotFoundException("User not found!!!");
        }
        //return the custome user
        return new CustomUserDetails(userByEmail);
    }
}