package com.wzx.wzx_test1.mapper;

import com.wzx.wzx_test1.model.Appointment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DateMapper {
    @Select("SELECT * FROM orderDtl")
    @Results({
            @Result(property = "ID", column = "ID"),
            @Result(property = "patientName", column = "patientName"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "doctorName", column = "doctorName"),
            @Result(property = "date", column = "date")
    })
    List<Appointment> getAll();

    @Delete("DELETE FROM `order` WHERE `patientID` =#{ID}")
    void delete(Appointment appointment);
}
