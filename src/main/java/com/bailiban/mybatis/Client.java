package com.bailiban.mybatis;

import com.bailiban.mybatis.mapper.UserMapper;
import com.bailiban.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("mybatis-config.xml"));

        try (SqlSession session = sqlSessionFactory.openSession(true)){

            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user1 = userMapper.selectUser(1);
            System.out.println(user1);
            user1.setName("Jim2");
            User user2 = userMapper.selectUser(1);
            System.out.println(user2);
        }

    }
}
