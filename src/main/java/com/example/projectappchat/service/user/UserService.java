package com.example.projectappchat.service.user;

import com.example.projectappchat.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);
    User findUserByAccount(String userAccount);
    User findUserById(Long userId);
    List<User> search (String keyword);


}
