package com.wzx.Nirvana.controller;

import com.auth0.jwt.JWT;
import com.wzx.Nirvana.annotation.UserLoginToken;
import com.wzx.Nirvana.mapper.UserMapper;
import com.wzx.Nirvana.model.Captcha;
import com.wzx.Nirvana.model.User;
import com.wzx.Nirvana.model.UserVO;
import com.wzx.Nirvana.service.SignService;
import com.wzx.Nirvana.service.TokenService;
import com.wzx.Nirvana.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class SignController {
    private static final Logger logger = LoggerFactory.getLogger(SignController.class);

    @Autowired
    SignService signService;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("getAuthCode")
    @ResponseBody
    public CommonResult<Captcha> getAuthCode(HttpServletRequest request, HttpServletResponse response) {
        logger.info("getAuthCode");
        return CommonResult.successReturn(signService.createCaptcha(request, response));
    }


//    @RequestMapping("getCurrentUser")
//    @ResponseBody
//    public CommonResult<User> getCurrentUser(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        return CommonResult.successReturn(userMapper.getOne(session.getAttribute("id").toString()));
//    }

    @UserLoginToken
    @RequestMapping("getCurrentUser")
    @ResponseBody
    public CommonResult<User> getCurrentUser(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        return CommonResult.successReturn(userMapper.getOne(JWT.decode(token).getAudience().get(0)));
    }


    @RequestMapping("login")
    @ResponseBody
    public CommonResult<String> userLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, String userName, String password, String verCode) {
        logger.info("userLogin");
        logger.info("userName: " + userName);
        logger.info(password);
        logger.info(verCode);
        Integer state = signService.checkLogin(session, request, response, userName, password, verCode);
        if (state < 0) {
            return CommonResult.errorReturn(state == -1 ? "验证码错误" : "用户名或密码错误");
        } else {
            String token = tokenService.getToken(userMapper.getOne(userName));
            return CommonResult.successReturn(token, state == 1 ? "business_homepage.html" : "/");
        }

    }

    @UserLoginToken
    @RequestMapping("logout")
    @ResponseBody
    public CommonResult<String> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        signService.checkLogout(session, request, response);
        return CommonResult.successReturn("0");
    }

    @RequestMapping("/signup")
    public void save(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        //user.setPhone(Integer.parseInt(userVO.getPhone()));
        userMapper.insert(user);
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    @ResponseBody
    public CommonResult<String> getMessage() {
        return CommonResult.successReturn("0", "你已通过验证");
    }


}
