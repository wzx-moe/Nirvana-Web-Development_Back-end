package com.wzx.nirvana.controller;

import com.wzx.nirvana.model.Code;
import com.wzx.nirvana.repository.CodeRepository;
import com.wzx.nirvana.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/code")
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    CodeRepository codeRepository;


    @RequestMapping("{code}")
    @ResponseBody
    public CommonResult<Code> getCode(@PathVariable String code) {
        Code result = codeRepository.getOne(code);
        if (result != null) {
            return CommonResult.successReturn(codeRepository.getOne(code));
        }
        return CommonResult.errorReturn(404, "Code not found");
    }

    @RequestMapping("add")
    @ResponseBody
    public CommonResult<Code> addCode(@RequestBody Code code) {
        //logger.info(page.toString());
        code = codeRepository.addCode(code);
        if (code != null) {
            return CommonResult.successReturn(code, "Add success");
        }
        return CommonResult.errorReturn(400, "Add failed");
    }

    @RequestMapping("update")
    @ResponseBody
    public CommonResult<String> updateCode(@RequestBody Code code) {
        if (codeRepository.updateCode(code) == 1) {
            return CommonResult.successReturn("0", "Update success");
        }
        return CommonResult.errorReturn(400, "Update failed");
    }
}
