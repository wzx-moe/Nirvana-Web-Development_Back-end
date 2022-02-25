package com.wzx.wzx_test1.mapper;

import com.wzx.wzx_test1.model.Review;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ReviewMapper {

    @Select("SELECT * FROM review")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productID", column = "productID"),
            @Result(property = "userID", column = "userID"),
            @Result(property = "content", column = "content")
    })
    List<Review> getAll();

    @Select("SELECT * FROM review WHERE userID = #{userID}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productID", column = "productID"),
            @Result(property = "userID", column = "userID"),
            @Result(property = "content", column = "content")
    })
    List<Review> userGetOwn(String userID);

    @Select("SELECT * FROM review WHERE productID = #{productID}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productID", column = "productID"),
            @Result(property = "userID", column = "userID"),
            @Result(property = "content", column = "content")
    })
    List<Review> productGetOwn(String userID);

    @Select("SELECT * FROM review WHERE userID = #{userID}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productID", column = "productID"),
            @Result(property = "userID", column = "userID"),
            @Result(property = "content", column = "content")
    })
    Review getOne(String id);

    @Insert("INSERT INTO productDtl(id,productID,userID,content) VALUES(#{id}, #{productID}, #{userID}, #{content})")
    void insert(Review review);

    @Update("UPDATE productDtl SET productID=#{productID}, userID=#{userID}, content=#{content} WHERE id =#{id}")
    void update(Review review);


}
