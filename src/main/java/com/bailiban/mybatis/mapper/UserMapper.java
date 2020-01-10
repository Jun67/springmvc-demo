package com.bailiban.mybatis.mapper;

import com.bailiban.mybatis.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    public User selectUser(int id);
    public User selectUser11(int id);
    public User selectUser12(int id);
    @Select("select * from user where id=#{id}")
    public User selectUser2(int id);
    @Select("select * from user where id=#{id}")
    public User selectUser3(User user);
    public User selectUser4(User user);
    public User selectUser5(int id);
    public User selectUserByIDAndName(int id, String name);
    public User selectUserByIDAndName2(int id, String name);
//    @Select("select name from user where id=#{id}")
    public String selectName(int id);
    public List<User> selectAllUser();
    public User selectUserByName(String name);
    public Integer insertUser(User user);
    public int insertUsers(List<User> users);
}
