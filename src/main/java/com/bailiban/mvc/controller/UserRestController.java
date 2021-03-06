package com.bailiban.mvc.controller;

import com.bailiban.mvc.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest-user")
public class UserRestController {


    @RequestMapping(value = "get")
    public User get0(int id) {
        System.out.println(id);
        return UserController.userList.stream().filter(u -> u.getId().equals(id)).findAny().orElse(null);
    }

    @RequestMapping(value = "get2")
    public User get(@RequestParam(value = "id", defaultValue = "-1") Integer id) {
        System.out.println(id);
        return UserController.userList.stream().filter(u -> u.getId().equals(id)).findAny().orElse(null);
    }

//    @ModelAttribute
//    public User getUser(int id) {
//        User user = userList.stream().filter(u -> u.getId().equals(id)).findAny().orElse(null);
//        user.setFriends(Arrays.asList("Lily", "Lucy")).setDate(new Date());
//        return user;
//    }

    @PostMapping("add")
    public String add(User user) {
        System.out.println(user);
        UserController.userList.add(user);
        return user.toString();
    }

    @PostMapping("add2")
    public String add2(User user) {
        System.out.println(user);
        UserController.userList.add(user);
        return "success";
    }

    @PostMapping("requestBody1")
    public String requestBody1(@RequestBody User user) {
        System.out.println(user);
        return "success";
    }

    @PostMapping("requestBody2")
    public String requestBody2(@RequestBody String user) {
        System.out.println(user);
        return "success";
    }

}
