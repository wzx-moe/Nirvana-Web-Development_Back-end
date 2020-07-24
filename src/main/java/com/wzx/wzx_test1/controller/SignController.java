package com.wzx.wzx_test1.controller;

import com.wf.captcha.SpecCaptcha;
import com.wzx.wzx_test1.model.Captcha;
import com.wzx.wzx_test1.service.SignService;
import com.wzx.wzx_test1.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api")
public class SignController {
    private static final Logger logger = LoggerFactory.getLogger(SignController.class);

    @Autowired
    SignService signService;

    @RequestMapping("getAuthCode")
    @ResponseBody
    public CommonResult<Captcha> getAuthCode(HttpServletRequest request, HttpServletResponse response) {
        logger.info("getAuthCode");
        return CommonResult.successReturn(signService.createCaptcha(request, response));
    }
}
