package com.bailiban.dao.impl;

import com.bailiban.dao.UserDao;
import com.bailiban.mybatis.mapper.UserMapper;
import com.bailiban.mybatis.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> list() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAllUser();
        session.close();
        return users;
    }

    @Override
    public User get(int id) {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectUser(id);
        session.close();
        return user;
    }

    @Override
    public int add(List<User> users) {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int ret = userMapper.insertUsers(users);
        session.commit();
        session.close();
        return ret;
    }
}
