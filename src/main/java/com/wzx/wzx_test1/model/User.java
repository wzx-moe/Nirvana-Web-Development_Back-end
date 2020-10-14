package com.wzx.wzx_test1.model;

import java.util.Date;
import com.wzx.wzx_test1.enums.UserSexEnum;

public class User {
    private String id;
    private Date birthday;
    private UserSexEnum userSex;
    private int phone;
    private int phone2;
    private String email;
    private String password;
    private boolean loginState;
    private boolean inService;
    private boolean admin;
    private int sessionId;
    private String name;


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone2() {
        return phone2;
    }

    public void setPhone2(int phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }

    public boolean isInService() {
        return inService;
    }

    public void setInService(boolean inService) {
        this.inService = inService;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", birthday=" + birthday +
                ", userSex=" + userSex +
                ", phone=" + phone +
                ", phone2=" + phone2 +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loginState=" + loginState +
                ", inService=" + inService +
                ", admin=" + admin +
                ", sessionId=" + sessionId +
                ", name='" + name + '\'' +
                '}';
    }
}
