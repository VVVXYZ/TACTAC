package com.trio.breakFast.controller;

/**
 * Created by ienovo on 2016/10/26.
 */

import com.trio.breakFast.model.Tac_resume;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.Tac_recruitService;
import com.trio.breakFast.service.Tac_resumeService;
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
    @Autowired
    Tac_resumeService tac_resumeService;

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

    //----------------

    //创建简历
    //2016-29-22 VV
    @ResponseBody
    @RequestMapping(value = "/createResume", method = RequestMethod.POST)
    public MessageHelper  createResume(String name,String nickname,String phone,String email ,
                              String singleResume,String detailResume)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_resumeService.createResume(name,nickname, phone, email,singleResume, detailResume);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("创建简历成功");

        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return  messageHelper;
    }

    //更新简历
    //2016-29-22 VV
    @ResponseBody
    @RequestMapping(value = "/updateResume", method = RequestMethod.POST)
    public MessageHelper  updateResume(Integer userid ,String name,String nickname,String phone,String email ,
                              String singleResume,String detailResume)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_resumeService.updateResume(userid, name, nickname, phone, email, singleResume, detailResume);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("更新简历成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return  messageHelper;
    }


    //查询简历
    //2016-29-22 VV
    @ResponseBody
    @RequestMapping(value = "/getResume", method = RequestMethod.POST)
    public DataHelper getResume(Integer userid)
    {
        DataHelper dataHelper=new DataHelper();
        try{
            Tac_resume tac_resume=tac_resumeService.getResume(userid);
            dataHelper.setData(tac_resume);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("获取简历成功");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //--------------
    //账户信息的修改

    //查看应聘者对我评价

    //查看招聘者对我评价


}
