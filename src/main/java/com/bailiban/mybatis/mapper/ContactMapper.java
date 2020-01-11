package com.bailiban.mybatis.mapper;

import com.bailiban.mybatis.model.Contact;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ContactMapper {

    @Select("select * from contact where user_id=#{userId}")
    public Contact findContactByUserId(int userId);

    public List<Contact> findContacts(Contact contact);
}
