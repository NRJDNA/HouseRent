package com.example.houserentingsystem.config.configUser;

import com.example.houserentingsystem.component.authorizeUser.AuthorizeUser;
import com.example.houserentingsystem.model.User;
import com.example.houserentingsystem.model.admin.AdminRegister;
import com.example.houserentingsystem.model.register.Register;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
        if (user.getUserStatus().ordinal() == 0) {
            Register register = new Register();
            register.setEmail(user.getEmail());
            AuthorizeUser.setRegister(register);

        } else {
            AdminRegister adminRegister = new AdminRegister();
            adminRegister.setEmail(user.getEmail());
            AuthorizeUser.setAdminRegister(adminRegister);
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*return List.of(new SimpleGrantedAuthority(""));*/
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(authority);
        return authorities;
    }

    //get the user password
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //get teh email
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
