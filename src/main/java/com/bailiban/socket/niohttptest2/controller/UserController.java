package com.bailiban.socket.niohttptest2.controller;


import com.bailiban.socket.niohttptest2.annotation.MyRequestMapping;
import com.bailiban.socket.niohttptest2.annotation.MyRestController;
import com.bailiban.socket.niohttptest2.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@MyRestController
public class UserController {

    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1, "Jim"));
        userList.add(new User(2, "Lily"));
    }

    @MyRequestMapping("/get")
    public String get(int id) {
        return userList.stream().filter(u -> u.getId() == id).findAny().orElse(null).toString();
    }

    @MyRequestMapping("/get2")
    public String get2(int id, Map map) {
        map.put("user", userList.stream().filter(u -> u.getId() == id).findAny().orElse(null));
        return "user.html";
    }

    @MyRequestMapping("/getAll")
    public String getAll() {
        return userList.toString();
    }

    @MyRequestMapping("/add")
    public String add(int id, String name, Map map) {
        User user = new User(id, name);
        map.put("user", user);
        map.put("msg", "添加成功。");
        userList.add(user);
        return "user.html";
    }
}
