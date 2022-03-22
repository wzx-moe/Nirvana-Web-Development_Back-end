package com.wzx.Nirvana.controller;

import com.wzx.Nirvana.annotation.UserLoginToken;
import com.wzx.Nirvana.mapper.UserMapper;
import com.wzx.Nirvana.model.User;
import com.wzx.Nirvana.model.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @UserLoginToken
    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = userMapper.getAll();
        return users;
    }

    @UserLoginToken
    @RequestMapping("/getUser")
    public User getUser(String id) {
        User user = userMapper.getOne(id);
        return user;
    }

    @RequestMapping("/addUser")
    public void save(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        //user.setPhone(Integer.parseInt(userVO.getPhone()));
        userMapper.insert(user);
    }

    @UserLoginToken
    @RequestMapping(value = "/updateUser")
    public void update(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        userMapper.update(user);
    }

//    @RequestMapping(value="/delete/{id}")
//    public void delete(@PathVariable("id") Long id) {
//    	userMapper.delete(id);
//    }


}