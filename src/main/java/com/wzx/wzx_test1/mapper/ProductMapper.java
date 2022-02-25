package com.wzx.wzx_test1.mapper;

import com.wzx.wzx_test1.model.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductMapper {
    @Select("SELECT * FROM productDtl")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "price", column = "price"),
            @Result(property = "category", column = "category"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "owner", column = "owner")
    })
    List<Product> getAll();

    @Select("SELECT * FROM productDtl WHERE owner = #{owner}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "price", column = "price"),
            @Result(property = "category", column = "category"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "owner", column = "owner")
    })
    List<Product> getOwn(String owner);

    @Select("SELECT * FROM productDtl WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "price", column = "price"),
            @Result(property = "category", column = "category"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "owner", column = "owner")
    })
    Product getOne(String id);

    @Insert("INSERT INTO productDtl(id,name,description,price,category,picture,owner) VALUES(#{id}, #{name}, #{description}, #{price}, #{category}, #{picture}, #{owner})")
    void insert(Product product);

    @Update("UPDATE productDtl SET name=#{name}, description=#{description}, price=#{price}, category=#{category}, picture=#{picture}, owner=#{owner} WHERE id =#{id}")
    void update(Product product);


}
