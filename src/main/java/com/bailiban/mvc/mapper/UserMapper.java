package com.bailiban.mvc.mapper;

import com.bailiban.mvc.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where id=#{id}")
    User selectUser(int id);

    @Select("select * from user where name=#{name} and password=#{password}")
    User login(User user);

    int update(User user);
}
