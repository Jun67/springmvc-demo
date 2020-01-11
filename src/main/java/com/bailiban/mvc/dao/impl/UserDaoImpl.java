package com.bailiban.mvc.dao.impl;

import com.bailiban.mvc.dao.UserDao;
import com.bailiban.mvc.mapper.UserMapper;
import com.bailiban.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private final UserMapper userMapper;

    public UserDaoImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User get(int id) {
        return userMapper.selectUser(id);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }
}
