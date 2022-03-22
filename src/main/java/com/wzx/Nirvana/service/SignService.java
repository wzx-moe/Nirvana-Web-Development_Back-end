package com.wzx.Nirvana.service;


import com.wf.captcha.SpecCaptcha;
import com.wzx.Nirvana.controller.SignController;
import com.wzx.Nirvana.mapper.SignMapper;
import com.wzx.Nirvana.mapper.UserMapper;
import com.wzx.Nirvana.model.Captcha;
import com.wzx.Nirvana.model.User;
import com.wzx.Nirvana.utils.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(SignController.class);

    @Autowired
    SignMapper signMapper;
    @Autowired
    UserMapper userMapper;


    public Captcha createCaptcha(HttpServletRequest request, HttpServletResponse response) {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String sessionId = getSessionId(request);
        System.out.println(sessionId);
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
        Cookie cookie = new Cookie(SESSION_KEY, sessionId);
        cookie.setMaxAge(36000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public boolean sessionIdIsExist(String sessionId) {
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

    public void setAttribute(HttpSession session, String username, String uid) {
        if (session == null) {
            logger.warn("session is null, username#{}, uid#{}", username, uid);
        } else {
            session.setAttribute("name", username);
            session.setAttribute("id", uid);
        }
    }

    public void removeAttribute(HttpSession session) {
        if (session != null) {
            session.removeAttribute("name");
            session.removeAttribute("id");
        }
    }

    public Integer checkLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, String userName, String passwd, String verCode) {
        String sessionId = getSessionId(request);
        User user = userMapper.getOne(userName);
        System.out.println(signMapper.getVerCode(sessionId));
        if (!verCode.equals(signMapper.getVerCode(sessionId))) {
            return -1;
        } else if (!user.getPassword().equals(passwd)) {
            return -2;
        }
        user.setLoginState(true);
        user.setSessionId(sessionId);
        userMapper.update(user);
        setAttribute(session, user.getName(), user.getId());
        if (user.isAdmin()){
            return 1;
        }
        return 0;
    }

    public Integer checkLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = userMapper.getOne(session.getAttribute("id").toString());
        user.setLoginState(false);
        user.setSessionId(null);
        userMapper.update(user);
        removeAttribute(session);
        removeSessionId(request);
        return 0;
    }
}
