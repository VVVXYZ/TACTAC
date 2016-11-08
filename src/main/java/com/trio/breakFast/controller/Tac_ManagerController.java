package com.trio.breakFast.controller;

import com.trio.breakFast.model.Tac_advice;
import com.trio.breakFast.model.Tac_atoocomment;
import com.trio.breakFast.model.Tac_otoacomment;
import com.trio.breakFast.model.Tac_recruit;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.*;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ienovo on 2016/10/30.
 */
@Controller
@RequestMapping("/tacManager")
public class Tac_ManagerController {

    @Autowired
    Tac_recruitService recruitService;
    @Autowired
    Tac_applicantsService tac_applicantsService;
    @Autowired
    Tac_otoacommentService tac_otoacommentService;
    @Autowired
    Tac_atoocommentService tac_atoocommentService;
    @Autowired
    Tac_adviceService tac_adviceService;

    //-------------
    //2016-10-31 20
    //查看招聘列表
    //************不要这个了
    //2016-11-7
    @ResponseBody
    @RequestMapping(value = "/getRecruitForManager", method = RequestMethod.POST)
    public DataHelper getRecruitForManager(Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {
            List<Tac_recruit> tac_recruitLists=recruitService.getRecruitForManager(page, rows);
            dataHelper.setData(tac_recruitLists);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("管理员查看被招聘列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;

    }

    //查看某一条招聘  /tacrecruit/getRecruitByID
    //更改招聘状态   /tacrecruit/changeStatusOfRecruit  status 设置为  1-审核未通过


    //查看评论列表  aTOo
    @ResponseBody
    @RequestMapping(value = "/getaTOoCommentForManager", method = RequestMethod.POST)
    public DataHelper getaTOoCommentForManager(Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {
            List<Tac_atoocomment> tac_recruitLists=tac_atoocommentService.getCommentForManager(page, rows);
            dataHelper.setData(tac_recruitLists);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("管理员查看aTOo评论列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //查看评论列表  oTOa
    @ResponseBody
    @RequestMapping(value = "/getoTOaCommentForManager", method = RequestMethod.POST)
    public DataHelper getoTOaCommentForManager(Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {
            List<Tac_otoacomment> tac_otoacommentList=tac_otoacommentService.getCommentForManager(page, rows);
            dataHelper.setData(tac_otoacommentList);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("管理员查看oTOa评论列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //查看某一条评论  aTOo
    @ResponseBody
    @RequestMapping(value = "/getTac_atoocommentByID", method = RequestMethod.POST)
    public DataHelper  getTac_atoocommentByID(Integer commentid)
    {
        DataHelper dataHelper =new DataHelper();
        try {
            Tac_atoocomment tac_atoocomment=tac_atoocommentService.getTac_atoocommentByID(commentid);
            dataHelper.setData(tac_atoocomment);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("管理员查看aTOo评论成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //查看某一条评论 oTOa
    @ResponseBody
    @RequestMapping(value = "/getTac_otoacommentByID", method = RequestMethod.POST)
    public DataHelper getTac_otoacommentByID(Integer commentid)
    {
        DataHelper dataHelper =new DataHelper();
        try {
            Tac_otoacomment tac_otoacomment=tac_otoacommentService.getTac_otoacommentByID(commentid);
            dataHelper.setData(tac_otoacomment);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("管理员查看oTOa评论成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //更该评论状态  aTOo
    @ResponseBody
    @RequestMapping(value = "/checkaTOoComment", method = RequestMethod.POST)
    public MessageHelper checkaTOoComment(Integer commentid,String checktime,Integer status)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_atoocommentService.checkComment( commentid, checktime, status);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("更该aTOo评论状态成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }


    //更该评论状态 oTOa
    @ResponseBody
    @RequestMapping(value = "/checkoTOaComment", method = RequestMethod.POST)
    public MessageHelper checkoTOaComment(Integer commentid,String checktime,Integer status)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_otoacommentService.checkComment( commentid, checktime, status);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("更该oTOa评论状态成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }

    //查看建议
    @ResponseBody
    @RequestMapping(value = "/getAdvice", method = RequestMethod.POST)
    public DataHelper  getAdvice(Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {
            List<Tac_advice> tac_adviceList=tac_adviceService.getAdvice( page,  rows);
            dataHelper.setData(tac_adviceList);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("管理员建议列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }


    //更新根据截止日期

    //登录  直接在个人中心里

}
