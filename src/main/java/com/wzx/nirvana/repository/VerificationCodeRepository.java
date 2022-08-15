package com.wzx.nirvana.repository;

public interface VerificationCodeRepository {

    String getSessionId(String sessionId);

    int addVerCode(String verCode, String sessionId);

    int updateVerCode(String verCode, String sessionId);

    int deleteSessionId(String sessionId);

    String getVerCode(String sessionId);
}
