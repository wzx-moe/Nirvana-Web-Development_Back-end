package com.wzx.nirvana.controller;


import com.wzx.nirvana.annotation.UserLoginToken;
import com.wzx.nirvana.model.Contact;
import com.wzx.nirvana.repository.ContactRepository;
import com.wzx.nirvana.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/contact")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    JavaMailSender javaMailSender;


    @Value("${Nirvana.mail.subject}")
    public String subject;

    @Value("${Nirvana.mail.text}")
    public String text;


    @RequestMapping("add")
    @ResponseBody
    public CommonResult<Contact> addContact(@RequestBody Contact contact) {
        //logger.info(page.toString());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(contact.getEmail());
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
        contact = contactRepository.addContact(contact);
        if (contact != null) {
            return CommonResult.successReturn(contact, "Add success");
        }
        return CommonResult.errorReturn(400, "Add failed");
    }

    @UserLoginToken
    @RequestMapping("getContacts")
    @ResponseBody
    public CommonResult<List<Contact>> getContacts() {
        return CommonResult.successReturn(contactRepository.getContacts());
    }
}
