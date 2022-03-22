package com.wzx.Nirvana.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface SignMapper {
    @Select("SELECT `sessionId` FROM `verificationCode` WHERE `sessionId`=#{sessionId}")
    String getSessionId(String sessionId);

    @Insert("INSERT into `verificationCode` (`verificationCode`, `sessionId`) VALUE (#{verCode}, #{sessionId})")
    int addVerCode(String verCode, String sessionId);

    @Update("UPDATE `verificationCode` SET `verificationCode`=#{verCode} WHERE sessionId=#{sessionId}")
    int updateVerCode(String verCode, String sessionId);

    @Update("UPDATE `userDtl` SET sessionId=null WHERE sessionId=#{sessionId}")
    int deleteSessionId(String sessionId);

    @Select("SELECT `verificationCode` FROM `verificationCode` WHERE `sessionId`=#{sessionId}")
    String getVerCode(String sessionId);
}
