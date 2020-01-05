package com.bailiban.mvc.validator;


import com.bailiban.mvc.model.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        // 判断是否为User类
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        // 判断name是否为空
        if (StringUtils.isEmpty(user.getName())) {
            errors.rejectValue("name", "-1", "自定义校验器：用户名不能为空!");
        }
    }
}
