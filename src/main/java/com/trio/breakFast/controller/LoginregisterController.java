package com.trio.breakFast.controller;

import com.trio.breakFast.model.Security;
import com.trio.breakFast.model.User;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.SecurityService;
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
    @Autowired
    private SecurityService securityService;

    //注册用户接口
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public MessageHelper userRegister(Security securityquestionid,String username,String password,String securitypsw,String confirmpsw,Boolean yes_or_no) {
        MessageHelper messageHelper = new MessageHelper();
        try{
            userService.put(securityquestionid,username,password,securitypsw,confirmpsw, yes_or_no);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("注册成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }

    //登录接口
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataHelper userLogin(String name, String psw) {
        DataHelper<User> dataHelper=new DataHelper<User>();
        System.out.print("denglu1");
        try{
            userService.judgeLogin(name, psw);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("登录成功");
            System.out.print("denglu2");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
            System.out.print("denglu3");

        }
        return dataHelper;
    }

    //忘记密码密保验证接口
    @RequestMapping(value = "/security", method = RequestMethod.POST)
    public MessageHelper userCheckSecurtiy(String username,Security securityquestionid,String securitypsw){
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

    //忘记密码修改密码接口
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public MessageHelper changePsw (String username,String password,String confirmPsw){
        MessageHelper messageHelper = new MessageHelper();
        try{
            userService.changePassword(username, password, confirmPsw);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("修改密码成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }
}
