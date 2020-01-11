package com.bailiban.mvc.dao;


import com.bailiban.mvc.model.User;

public interface UserDao {

    User get(int id);
    User login(User user);
    int update(User user);
}
