package com.wzx.nirvana.controller;

import com.wzx.nirvana.annotation.UserLoginToken;
import com.wzx.nirvana.model.Page;
import com.wzx.nirvana.repository.PageRepository;
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
@RequestMapping("/api/page")
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);


    @Autowired
    PageRepository pageRepository;

    @RequestMapping("{name}")
    @ResponseBody
    public CommonResult<Page> getPage(@PathVariable String name) {
        logger.info("getPage:" + name);
        Page page = pageRepository.getOne(name);
        if (page != null) {
            return CommonResult.successReturn(pageRepository.getOne(name));
        }
        return CommonResult.errorReturn(404, "Page not found");
    }

    @UserLoginToken
    @RequestMapping("update")
    @ResponseBody
    public CommonResult<String> updatePage(@RequestBody Page page) {
        logger.info("updatePage" + page.getName());
        //logger.info(page.toString());
        //pageRepository.update(page);
        if (pageRepository.update(page) == 1) {
            return CommonResult.successReturn("0", "Update success");
        }
        return CommonResult.successReturn("0", "Add success");
    }

}
