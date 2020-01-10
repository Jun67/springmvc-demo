package com.bailiban.mybatis.mapper;

import com.bailiban.mybatis.model.Account;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountMapper {

    @Select("select * from account where id=#{id}")
    public Account selectAccount(int id);
    @Select("select * from account where user_id=#{userId}")
    public List<Account> selectAccountsByUserId(int userId);
}
