package com.trio.breakFast.controller;

/**
 * Created by ienovo on 2016/10/26.
 */

import com.trio.breakFast.model.Tac_atoocomment;
import com.trio.breakFast.model.Tac_otoacomment;
import com.trio.breakFast.model.Tac_resume;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.*;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.SendmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tacpersonal")
public class Tac_personalController {

    @Autowired
    Tac_userService tac_userService;
    @Autowired
    Tac_resumeService tac_resumeService;
    @Autowired
    Tac_atoocommentService tac_atoocommentService;
    @Autowired
    Tac_otoacommentService tac_otoacommentService;
    @Autowired
    Tac_adviceService tac_adviceService;
    @Autowired
    FTPffUpAndDownService FTPffUpAndDownService;

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
    //2016-10-31 19


    //查看应聘者对我评价
    @ResponseBody
    @RequestMapping(value = "/getAToOComment", method = RequestMethod.POST)
    public DataHelper getAToOComment(Integer ownerid,Integer page, Integer rows)
    {
        DataHelper dataHelper=new DataHelper();
        try{
            List<Tac_atoocomment> tac_atoocommentList=tac_atoocommentService.getComment(ownerid, page, rows);
            dataHelper.setData(tac_atoocommentList);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("获取应聘者对我评价成功");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }


    //查看招聘者对我评价
    @ResponseBody
    @RequestMapping(value = "/getOToAComment", method = RequestMethod.POST)
    public DataHelper getOToAComment(Integer applicantid,Integer page, Integer rows)
    {
        DataHelper dataHelper=new DataHelper();
        try{
            List<Tac_otoacomment> tac_otoacommentList=tac_otoacommentService.getComment(applicantid, page, rows);
            dataHelper.setData(tac_otoacommentList);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("获取招聘者对我评价成功");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //招聘者评价应聘者 举报
    @ResponseBody
    @RequestMapping(value = "/tipOToAComment", method = RequestMethod.POST)
    public MessageHelper tipOToAComment(Integer commentid)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_otoacommentService.tipComment(commentid);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("招聘者对我评价 举报成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return  messageHelper;
    }




    //应聘者评价招聘者举报
    @ResponseBody
    @RequestMapping(value = "/tipAToOComment", method = RequestMethod.POST)
    public MessageHelper tipAToOComment(Integer commentid)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_atoocommentService.tipComment(commentid);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("应聘者对我评价 举报成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return  messageHelper;
    }

    //账户信息的修改
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public  MessageHelper update(Integer useid,String name ,String password,String phone,String email)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_userService.update( useid, name , password, phone, email);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("账户信息修改成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return  messageHelper;
    }

    //反馈
    @ResponseBody
    @RequestMapping(value = "/createAdvice", method = RequestMethod.POST)
    public MessageHelper createAdvice(Integer userid,String username,String time,String phone,String advice)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_adviceService.createAdvice( userid, username, time, phone, advice);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("反馈成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return  messageHelper;
    }


    //上传头像
    @ResponseBody
    @RequestMapping(value = "/upFile", method = RequestMethod.POST)
    public MessageHelper FileUp(String fileName, String picture) {
        MessageHelper messageHelper = new MessageHelper();
        try {
            FTPffUpAndDownService.FileUp(fileName, picture);
            System.out.println(fileName);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("头像上传成功");

        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());

        }

        return messageHelper;
    }

    //获取头像
    //加载头像
    @ResponseBody
    @RequestMapping(value = "/downFile", method = RequestMethod.POST)
    public DataHelper FileDown(String fileName) {
        DataHelper dataHelper = new DataHelper();
        try {
            String picture = FTPffUpAndDownService.FileDown(fileName);
            dataHelper.setData(picture);
            dataHelper.setSuccess(true);

            dataHelper.setMessage("头像加载成功");

        } catch (ServiceException e) {
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());

        }
        return dataHelper;
    }

    //-----------
    //2016-11-10 19

    //根据用户名查询该用户
    @ResponseBody
    @RequestMapping(value = "/getUserByName", method = RequestMethod.POST)
    public DataHelper  getUserByName(String username)
    {
        DataHelper dataHelper = new DataHelper();
        try {
            Tac_user tac_user=tac_userService.get(username);
            dataHelper.setData(tac_user);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("用户信息获取成功");

        } catch (ServiceException e) {
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());

        }
        return dataHelper;
    }

    //重置密码
    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public MessageHelper updatePassword(String username,String password)
    {
        MessageHelper messageHelper = new MessageHelper();
        try {
            tac_userService.updatePassword(username,password);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("密码更改成功");

        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());

        }

        return messageHelper;
    }

}
