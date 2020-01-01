package com.bailiban.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = {"/", "/hello", "/index"})
    public String hello() {
        return "hello你好";
    }
}
