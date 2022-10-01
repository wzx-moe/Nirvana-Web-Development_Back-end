package com.wzx.nirvana.interceptor;

import com.wzx.nirvana.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jinbin
 * @date 2018-07-08 22:37
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {

        final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
        logger.warn(e.toString());

        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "Internal Error";
        }
        return CommonResult.errorReturn(500, msg);
    }
}
