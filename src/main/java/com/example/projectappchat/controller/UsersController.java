package com.example.projectappchat.controller;

import com.example.projectappchat.entity.User;
import com.example.projectappchat.service.user.UserService;
import com.example.projectappchat.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("/search")
//    @GetMapping("/registration/{userName}")
//    public ResponseEntity<Void> register(@PathVariable String userName) {
//        System.out.println("handling register user request: " + userName);
//        try {
//            UserStorage.getInstance().setUser(userName);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/fetchAllUsers")
//    public Set<String> fetchAll() {
//        return UserStorage.getInstance().getUsers();
//    }

//    @RequestMapping("/search")
//    public ModelAndView searchpage(@Param("keyword") String keyword){
//        ModelAndView modelAndView = new ModelAndView("addfriend");
//        List<com.example.projectappchat.entity.User> searchResult = userService.search(keyword);
//        return modelAndView;
//    }
    @GetMapping("/search/{user}")
    public ModelAndView search(@Param("keyword") String keyword){
        ModelAndView modelAndView = new ModelAndView("addfriend");
        List<com.example.projectappchat.entity.User> searchResult = userService.search(keyword);
        modelAndView.addObject("keyword",keyword);
        modelAndView.addObject("pageTitle: ","Search result for ' " +keyword+"'");
        modelAndView.addObject("searchResult",searchResult);
        return modelAndView;
    }
}