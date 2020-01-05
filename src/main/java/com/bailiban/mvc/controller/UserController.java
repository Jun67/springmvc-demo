package com.bailiban.mvc.controller;

import com.bailiban.mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
//@Validated
//@SessionAttributes(types = User.class)
public class UserController {

    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1, "Jim"));
        userList.add(new User(2, "Lily"));
        userList.add(new User(3, "Kate"));
        userList.add(new User(4, "David"));
    }

    @RequestMapping("login1")
    public String login(User user) {
        return "forward:/user/login";
    }

    @RequestMapping("login")
    public String login2(User user, HttpSession session) {
        if (session.getAttribute("user") != null)
            return "redirect:/user/home";

        if (user.getId() != null &&
                (userList.stream().anyMatch(u -> u.getId().equals(user.getId()) &&
                        u.getName().equals(user.getName())))) {
                session.setAttribute("user", user);
                return "redirect:/user/home";
        }
        return "login";
    }

    @RequestMapping("home")
    public String home(@SessionAttribute("user") User user) {
        return "home";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        // 清除session，即清除user对象
        session.invalidate();
        return "redirect:/user/login";
    }

    @RequestMapping(value = "get")
    public String get(int id, ModelMap model) {
        model.addAttribute("user",
                userList.stream().filter(u -> u.getId().equals(id)).findAny().orElse(null));
        return "user";
    }

//    @InitBinder
//    public void initBinder(DataBinder binder){
//        binder.replaceValidators(new UserValidator());
//    }

    @PostMapping("update")
    public String update(@Validated User user, HttpSession session){
        System.out.println(user);
        for (int i=0; i<userList.size(); i++) {
            if (userList.get(i).getId().equals(user.getId())) {
                userList.set(i, user);
            }
        }
        session.setAttribute("user", user);
        return "redirect:/user/home";
    }

}
