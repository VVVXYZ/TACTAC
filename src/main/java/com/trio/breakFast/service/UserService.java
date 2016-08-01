package com.trio.breakFast.service;

import com.trio.breakFast.model.User;

/**
 * Created by asus on 2016/7/26.
 */
public interface UserService {
    public User get(String username);
    public void put(String username,String password,Integer securityquestionid,String securitypsw);
    public User judgeLogin(String name, String psw);
    public void checkSecurity (String username,Integer  securityquestionid,String securitypsw);
    public void changePassword(String username,String password);
    public User getUser(String username);
    public void checkUser(String username);

    public void judgeUser(String name, String psw);

}
