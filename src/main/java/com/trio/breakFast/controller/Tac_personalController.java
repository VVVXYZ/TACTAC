package com.trio.breakFast.controller;

/**
 * Created by ienovo on 2016/10/26.
 */

import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.Tac_userService;
import com.trio.breakFast.service.UserService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.SendmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tacpersonal")
public class Tac_personalController {

    @Autowired
    Tac_userService tac_userService;

    //发送验证码邮件，并返回验证码
    @ResponseBody
    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    public DataHelper checkEmail(String getterEmail)
    {
        DataHelper dataHelper=new DataHelper();
        try
        {
            String code= SendmailUtil.sendEmail(getterEmail);
            dataHelper.setData(code);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("邮箱发送成功");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //注册用户
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public MessageHelper register(String name ,String password,String phone,String email)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_userService.create( name , password, phone, email);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("注册成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return  messageHelper;
    }

    //登录接口
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataHelper userLogin(String name, String psw) {
        DataHelper dataHelper=new DataHelper();
        try{
            Tac_user tac_user=tac_userService.judgeLogin(name, psw);
            dataHelper.setData(tac_user);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("登录成功");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

}
