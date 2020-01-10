package com.bailiban.mybatis;

import com.bailiban.dao.UserDao;
import com.bailiban.dao.impl.UserDaoImpl;
import com.bailiban.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("com/bailiban/mybatis/config/mybatis-config.xml"));
        try (SqlSession session = sqlSessionFactory.openSession()) {
//            UserMapper mapper = session.getMapper(UserMapper.class);
//            User user = mapper.selectUser(1);
//            System.out.println(user);
//            User user2 = mapper.selectUser2(1);
//            System.out.println(user == user2);
//            List<User> users = mapper.selectAllUser();
//            users.forEach(System.out::println);
//            System.out.println(mapper.selectUserByName("Lily"));
//            System.out.println(mapper.selectName(1));
//            System.out.println((User) mapper.selectUser3(new User().setId(1)));
//            System.out.println((User) mapper.selectUser4(new User().setId(2)));
//
//            AccountMapper accountMapper = session.getMapper(AccountMapper.class);
//            System.out.println(accountMapper.selectAccount(1));
//            System.out.println(accountMapper.selectAccountsByUserId(1));
//
////            user = new User().setName("David2");
////            mapper.insertUser(user);
////            System.out.println(user);
////
////            List<User> userList = new ArrayList<>();
////            userList.add(new User().setName("a1"));
////            userList.add(new User().setName("a2"));
////            userList.add(new User().setName("a3"));
////            mapper.insertUsers(userList);
////            userList.forEach(System.out::println);
//
//            System.out.println(mapper.selectUser5(1));
//
//            System.out.println(mapper.selectUserByIDAndName(1, "Jim"));
//
//            System.out.println(mapper.selectUser11(2));
//            System.out.println(mapper.selectUser12(1));
//
//            session.commit();

        }
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        System.out.println(userDao.get(1));
        List<User> userList = new ArrayList<>();
        userList.add(new User().setName("a1"));
        userDao.add(userList);
        System.out.println(userList.get(0).getId());
    }
}
