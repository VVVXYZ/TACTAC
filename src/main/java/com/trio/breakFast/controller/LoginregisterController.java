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
 * Created by asus on 2016/7/27.
 */

@Controller
@RequestMapping("/user")
public class LoginregisterController extends BaseController {
    @Autowired
    private UserService userService;
//    @Autowired
//    private SecurityService securityService;

    //注册用户接口
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public MessageHelper userRegister(String username,String password,Integer securityquestionid,String securitypsw) {
        MessageHelper messageHelper = new MessageHelper();
        try{
            userService.put(username,password,securityquestionid,securitypsw);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("注册成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }

    //登录接口
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataHelper userLogin(String name, String psw) {
        DataHelper dataHelper=new DataHelper();
        try{
            User user=userService.judgeLogin(name, psw);
            dataHelper.setData(user);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("登录成功");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());

        }
        return dataHelper;
    }

    //验证用户是否存在
    @ResponseBody
    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    public MessageHelper checkUser(String username){
        MessageHelper messageHelper = new MessageHelper();
        try{
            userService.getUser(username);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("用户存在");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }


    //忘记密码密保验证接口
    @ResponseBody
    @RequestMapping(value = "/security", method = RequestMethod.POST)
    public MessageHelper userCheckSecurtiy(String username,Integer securityquestionid,String securitypsw){
        MessageHelper messageHelper = new MessageHelper();
        try{
            userService.checkSecurity(username, securityquestionid, securitypsw);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("验证成功，请重新设置密码");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }

    //忘记密码(要验证密保后在修改)/记得密码 修改密码接口
    @ResponseBody
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public MessageHelper changePsw (String username,String password){
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
