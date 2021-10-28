package com.example.projectappchat.service.user;

import com.example.projectappchat.entity.User;
import com.example.projectappchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByAccount(String userAccount) {
        return userRepository.findUserAccount(userAccount);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.getById(userId);
    }


        @Override
    public List<User> search (String keyword){
            return userRepository.search(keyword);
    }
}
