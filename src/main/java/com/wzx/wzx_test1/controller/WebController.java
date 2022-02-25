package com.wzx.wzx_test1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class WebController {

    @RequestMapping("/")
    public String index() {
        log.info("here index");
        return "index";
    }

    @RequestMapping("/check")
    public String check() {
        System.out.println();
        return "check";
    }

    @RequestMapping("/insert")
    public String insert() {
        System.out.println();
        return "insert";
    }

    @RequestMapping("/change")
    public String change() {
        System.out.println();
        return "change";
    }

    @RequestMapping("/inquiries")
    public String inquiries() {
        System.out.println();
        return "inquiries";
    }

    @RequestMapping("/delete")
    public String delete() {
        System.out.println();
        return "delete";
    }


}
