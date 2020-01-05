package com.bailiban.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(@NotEmpty(message = "不能为空") String msg) {
        System.out.println(msg);
        return msg;
    }

}
