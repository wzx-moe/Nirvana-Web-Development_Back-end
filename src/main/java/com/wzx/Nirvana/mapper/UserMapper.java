package com.wzx.Nirvana.mapper;

import com.wzx.Nirvana.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    @Select("SELECT * FROM userDtl")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "phone2", column = "phone2"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "loginState", column = "loginState"),
            @Result(property = "inService", column = "inService"),
            @Result(property = "admin", column = "admin"),
            @Result(property = "sessionId", column = "sessionId")
    })
    List<User> getAll();

    @Select("SELECT * FROM userDtl WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "phone2", column = "phone2"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "loginState", column = "loginState"),
            @Result(property = "inService", column = "inService"),
            @Result(property = "admin", column = "admin"),
            @Result(property = "sessionId", column = "sessionId")
    })
    User getOne(String id);

    @Insert("INSERT INTO userDtl(id,name,gender,password,email, phone) VALUES(#{id}, #{name}, #{gender},#{password}, #{email}, #{phone})")
    void insert(User user);

    @Update("UPDATE userDtl SET birthday=#{birthday}, gender=#{gender}, phone=#{phone}, phone2=#{phone2}, email=#{email}, password=#{password}, loginState=#{loginState}, inService=#{inService}, admin=#{admin}, sessionId=#{sessionId}, name=#{name} WHERE id =#{id}")
    void update(User user);


}
