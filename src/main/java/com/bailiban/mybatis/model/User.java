package com.bailiban.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
//@Alias("UserClass")
public class User implements Serializable {
    private int id;
    private String name;
    private List<Account> accounts;
    private Contact contact;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
