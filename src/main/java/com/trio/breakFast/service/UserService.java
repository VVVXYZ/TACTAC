package com.trio.breakFast.service;

import com.trio.breakFast.model.Security;
import com.trio.breakFast.model.User;

/**
 * Created by asus on 2016/7/26.
 */
public interface UserService {
    public User get(String username);
    public void put(Security securityquestionid,String username,String password,String securitypsw,String confirmpsw,Boolean yes_or_no);
    public User judgeLogin(String name, String psw);
    public void checkSecurity (String username,Security securityquestionid,String securitypsw);
    public void changePassword(String username,String password,String confirmPsw);
}
