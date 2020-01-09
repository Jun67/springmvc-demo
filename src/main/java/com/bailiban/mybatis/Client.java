package com.bailiban.mybatis;

import com.bailiban.mybatis.mapper.UserMapper;
import com.bailiban.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("com/bailiban/mybatis/config/mybatis-config.xml"));
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(1);
            System.out.println(user);
        }
    }
}
