package com.bailiban.mvc.model;

import com.bailiban.mvc.validator.NameValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {

    private Integer id;
//    @NotEmpty(message="用户名不能为空!")
    @NameValidation(message="自定义注解校验器：用户名不能为空!")
    private String name;
    private Set<String> friends;
    private Date date;
    private Account account;
    private Account[] accountList;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
