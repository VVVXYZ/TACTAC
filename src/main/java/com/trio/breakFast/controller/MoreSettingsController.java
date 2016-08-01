package com.trio.breakFast.controller;

import com.trio.breakFast.model.User;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.UserService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ienovo on 2016/7/30.
 */
@Controller
@RequestMapping("/settings")
public class MoreSettingsController {


    @Autowired
    private UserService userService;

    //显示用户信息接口，查询用户信息
    @ResponseBody
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public DataHelper getUser(String username)
    {
        DataHelper dataHelper=new DataHelper();
        try{
            User user=userService.getUser(username);
            dataHelper.setData(user);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("显示接口用户查询成功");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());

        }
        return dataHelper;
    }


    //忘记密码(要验证密保后在修改)/记得密码 修改密码接口
    @ResponseBody
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public MessageHelper changePsw (String username,String password,String confirmPsw){
        MessageHelper messageHelper = new MessageHelper();
        try{
            userService.changePassword(username, password);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("修改密码成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }

}
