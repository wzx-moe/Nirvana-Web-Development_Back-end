package com.wzx.wzx_test1.mapper;

import com.wzx.wzx_test1.enums.UserSexEnum;
import com.wzx.wzx_test1.model.User;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM userDtl")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "gender", column = "gender", javaType = UserSexEnum.class),
            @Result(property = "phone", column = "phone"),
            @Result(property = "phone2", column = "phone2"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "loginState", column = "loginState"),
            @Result(property = "inService", column = "inService"),
            @Result(property = "admin", column = "admin"),
            @Result(property = "sessionId", column = "sessionId"),
            @Result(property = "name", column = "name")
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "gender", column = "gender", javaType = UserSexEnum.class),
            @Result(property = "phone", column = "phone"),
            @Result(property = "phone2", column = "phone2"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "loginState", column = "loginState"),
            @Result(property = "inService", column = "inService"),
            @Result(property = "admin", column = "admin"),
            @Result(property = "sessionId", column = "sessionId"),
            @Result(property = "name", column = "name")
    })
    User getOne(String id);

    @Insert("INSERT INTO users(id,name,password) VALUES(#{ame}, #{password}, #{id})")
    void insert(User user);

    @Update("UPDATE users SET birthday=#{birthday}, gender=#{gender}, phone=#{phone}, phone2=#{phone2}, email=#{email}, password=#{password} WHERE id =#{id}")
    void update(User user);


}
