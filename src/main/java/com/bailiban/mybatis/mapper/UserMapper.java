package com.bailiban.mybatis.mapper;

import com.bailiban.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User selectUser(int id);
    List<User> selectAllUser();
    Integer insertUser(User user);
    int insertUsers(List<User> users);

//    @Insert("insert into user (name) values (${name})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser2(Map user);

    @Results(id = "user222", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "contact", column = "id", one = @One(select =
                "com.bailiban.mybatis.mapper.ContactMapper.findContactByUserId")),
            @Result(property = "accounts", column = "id", many = @Many(select =
                "com.bailiban.mybatis.mapper.AccountMapper.selectAccountsByUserId"))
    })
    @Select("select * from user where id=#{id}")
    User selectUser2(int id);
}
