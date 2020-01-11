package com.bailiban.mvc.controller;

import com.bailiban.mvc.dao.UserDao;
import com.bailiban.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private UserDao userDao;

    static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1, "Jim", "123456"));
        userList.add(new User(2, "Lily", "123456"));
        userList.add(new User(3, "Kate", "123456"));
    }

    @RequestMapping("login1")
    public String login1(User user) {
        return "forward:/user/login";
    }

    @RequestMapping("login")
    public String login(User user, HttpSession session) {
        User u2 = userDao.login(user);
        System.out.println(u2);
        if (u2 != null) {
            session.setAttribute("user", u2);
            return "redirect:/user/home";
        }
//        if (session.getAttribute("user") != null ||
//                user.getName() != null &&
//                userList.stream().anyMatch(u -> {
//                boolean flag = u.getName().equals(user.getName()) &&
//                        u.getPassword().equals(user.getPassword());
//                if (flag) {
//                    session.setAttribute("user", u);
//                }
//                return flag;
//            }))
//                return "redirect:/user/home";

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
        return "update";
    }

//    @InitBinder
//    public void initBinder(DataBinder binder){
//        binder.replaceValidators(new UserValidator());
//    }

    @PostMapping("update")
    public String update(@Validated User user, HttpSession session) {
        System.out.println(user);
//        for (int i = 0; i < userList.size(); i++) {
//            if (userList.get(i).getId().equals(user.getId())) {
//                userList.set(i, user);
//            }
//        }
        session.setAttribute("user", user);
        userDao.update(user);
        return "redirect:/user/home";
    }

    @GetMapping("update")
    public String update() {
        return "update";
    }

    @PostMapping("register")
    public String register(@Validated User user) {
        user.setId(userList.size() + 1);
        userList.add(user);
        return "redirect:/user/login";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

}
