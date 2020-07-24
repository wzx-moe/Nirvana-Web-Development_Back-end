package com.wzx.wzx_test1.service;

import com.wf.captcha.SpecCaptcha;
import com.wzx.wzx_test1.mapper.SignMapper;
import com.wzx.wzx_test1.model.Captcha;
import com.wzx.wzx_test1.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class SignService {
    private static final String SESSION_KEY = "sessionId";

    @Autowired
    SignMapper signMapper;

    public Captcha createCaptcha(HttpServletRequest request, HttpServletResponse response) {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String sessionId = getSessionId(request);
        if (sessionId == null || !sessionIdIsExist(sessionId)) {
            sessionId = createNewSessionId(request, verCode);
            createNewCookie(response, sessionId);
        } else {
            signMapper.updateVerCode(verCode, sessionId);
        }

        return new Captcha(specCaptcha.toBase64());
    }

    public String createNewSessionId(HttpServletRequest request, String verCode) {
        new DateConverter();
        String datetime = DateConverter.getSimpleDateFormat().format(new Date());
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_KEY, datetime);
        signMapper.addVerCode(verCode, session.getId());
        session.setMaxInactiveInterval(36000);
        return session.getId();
    }

    public void createNewCookie(HttpServletResponse response, String sessionId) {
        Cookie cookie =  new Cookie(SESSION_KEY, sessionId);
        cookie.setMaxAge(36000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public boolean sessionIdIsExist(String sessionId){
        String sessionIdInSQl = signMapper.getSessionId(sessionId);
        return sessionIdInSQl != null && sessionIdInSQl.length() > 0;
    }

    public void removeSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        signMapper.deleteSessionId(session.getId());
        session.removeAttribute(SESSION_KEY);
        session.invalidate();
    }

    public void cleanCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(SESSION_KEY, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public String getSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (SESSION_KEY.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
