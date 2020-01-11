package com.bailiban.mvc.controller;

import com.bailiban.mvc.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("")
    public String index() {
        return "hello";
    }

    @RequestMapping(value = "/hello")
    public String hello(@Validated User user) {
        System.out.println(user);
        return user.toString();
    }

}
