package com.example.houserentingsystem.service.impl;

import com.example.houserentingsystem.model.User;
import com.example.houserentingsystem.repo.UserRepo;
import com.example.houserentingsystem.service.UserService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User save(User user) throws ParseException {
        return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
