package com.bailiban.mvc.interceptor;

import com.bailiban.mvc.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 不拦截 login
        String url = request.getRequestURI();
        if (url.contains("/login") || url.contains("/register")) {
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 转发
//            request.getRequestDispatcher("/user/login").forward(request, response);
            // 重定向
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }
}
