package com.bailiban.dao;

import com.bailiban.mybatis.model.User;

import java.util.List;

public interface UserDao {

    public List<User> list();
    public User get(int id);
    public int add(List<User> users);
}
