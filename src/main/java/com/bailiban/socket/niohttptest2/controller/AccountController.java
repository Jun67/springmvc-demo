package com.bailiban.socket.niohttptest2.controller;


import com.bailiban.socket.niohttptest2.annotation.MyRequestMapping;
import com.bailiban.socket.niohttptest2.annotation.MyRestController;

@MyRestController
public class AccountController {

    @MyRequestMapping("/account/get")
    public String get(int id, String name, double money) {
        return "account:" + id + ", " + name + ", " + money;
    }
}
