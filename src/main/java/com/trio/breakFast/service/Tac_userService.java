package com.trio.breakFast.service;

import com.trio.breakFast.model.Tac_user;

/**
 * Created by ienovo on 2016/10/26.
 */
public interface Tac_userService {

    public void updatePassword(String username,String password);
    public String checkEmail(String getterEmail);
    public  void create(String name ,String password,String phone,String email);
    public Tac_user get(String username);
    public Tac_user judgeLogin(String name, String psw);
    public Tac_user getUserByID(Integer userid);
    public  void update(Integer useid,String name ,String password,String phone,String email);
}
