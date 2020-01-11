package com.bailiban.mybatis.mapper;

import com.bailiban.mybatis.model.Account;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountMapper {

    @Select("select * from account where id=#{id}")
    public Account selectAccount(int id);

    @Select("select * from account where user_id=#{userId}")
    public List<Account> selectAccountsByUserId(int userId);

    public List<Account> findAccounts(Account account);

    @Select("<script>" +
            "select * from account " +
            "<trim prefix='where' prefixOverrides='and|or'>" +
                "<choose>" +
                    "<when test='userId != null'> user_id=#{userId}</when>" +
                    "<when test='type != null'> type=#{type}</when>" +
                    "<otherwise> money>=#{money}</otherwise>" +
                "</choose>" +
            "</trim>" +
            "</script>")
    public List<Account> findAccounts2(Account account);

}
