package com.bailiban.mvc.controller;

import com.bailiban.mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
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
    public String get(@RequestParam(value = "id", defaultValue = "-1") Integer id,
                      String name,
                      ModelMap model) {
        System.out.println(id + ", " + name);
        model.addAttribute("user",
                userList.stream().filter(u -> u.getId().equals(id)).findAny().orElse(null));
        return "user";
    }

    @PostMapping("update")
    public String update(User user) {
        System.out.println(user);
        return "user";
    }

}
