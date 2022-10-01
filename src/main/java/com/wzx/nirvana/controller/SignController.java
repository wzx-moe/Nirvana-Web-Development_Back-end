package com.wzx.nirvana.controller;

import com.auth0.jwt.JWT;
import com.wzx.nirvana.annotation.UserLoginToken;
import com.wzx.nirvana.model.Captcha;
import com.wzx.nirvana.model.User;
import com.wzx.nirvana.model.UserVO;
import com.wzx.nirvana.repository.UserRepository;
import com.wzx.nirvana.service.SignService;
import com.wzx.nirvana.service.TokenService;
import com.wzx.nirvana.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    UserRepository userRepository;

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
        return CommonResult.successReturn(userRepository.getById(JWT.decode(token).getAudience().get(0)));
    }


    @RequestMapping("login")
    @ResponseBody
    public CommonResult<String> userLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestBody UserVO userVO) {
        logger.info("userLogin");
        logger.info("userName: " + userVO.getUserName());
        logger.info("password: " + userVO.getPassword());
        logger.info("verCode: " + userVO.getVerCode().toLowerCase());
        Integer state = signService.checkLogin(session, request, response, userVO.getUserName(), userVO.getPassword(), userVO.getVerCode().toLowerCase());
        if (state < 0) {
            return CommonResult.errorReturn(state == -1 ? "验证码错误" : "用户名或密码错误");
        } else {
            String token = tokenService.getToken(userRepository.getOne(userVO.getUserName()));
            return CommonResult.successReturn(token, "/");
        }

    }

    @UserLoginToken
    @RequestMapping("logout")
    @ResponseBody
    public CommonResult<String> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        signService.checkLogout(session, request, response);
        return CommonResult.successReturn("0");
    }

    @UserLoginToken
    @GetMapping("getMessage")
    @ResponseBody
    public CommonResult<String> getMessage() {
        return CommonResult.successReturn("0", "你已通过验证");
    }


}
