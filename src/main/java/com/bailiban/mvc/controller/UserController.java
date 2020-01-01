package com.bailiban.mvc.controller;

import com.bailiban.mvc.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1, "Jim"));
        userList.add(new User(2, "Lily"));
        userList.add(new User(3, "Kate"));
        userList.add(new User(4, "David"));
    }

    @RequestMapping(value = "get")
    public User get(@RequestParam(value = "id", defaultValue = "-1") Integer id) {
        System.out.println(id);
        return userList.stream().filter(u -> u.getId().equals(id)).findAny().orElse(null);
    }

    @PostMapping("add")
    public String add(User user) {
        System.out.println(user);
        userList.add(user);
        return "success";
    }

    @PostMapping("add2")
    public String add2(@RequestBody User user) {
        System.out.println(user);
        userList.add(user);
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
