package com.wzx.wzx_test1;

public class User {
    private String UserName;

    public String getUserName() {
        return UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    private String PassWord;



    public void setUserName( String userName ){
        this.UserName = userName;
    }

    public void setPassWord( String passWord ){
        this.PassWord = passWord;
    }
}
