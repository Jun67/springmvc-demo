package com.bailiban.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {

    private Integer id;
//    @NotEmpty(message="用户名不能为空!")
//    @NameValidation(message="自定义注解校验器：用户名不能为空!")
    private String name;
//    @Size(min = 6, max = 20, message = "请输入6~20位密码。")
    private String password;
    private Integer sex;
//    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号码格式不正确。")
    private String phone;
//    @Email
    private String email;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

//    public User(Integer id, @NotEmpty(message = "用户名不能为空!") String name, @Size(min = 6, max = 20, message = "请输入6~20位密码。") String password) {
//        this.id = id;
//        this.name = name;
//        this.password = password;
//    }

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
