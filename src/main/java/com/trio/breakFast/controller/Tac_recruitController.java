package com.trio.breakFast.controller;

/**
 * Created by ienovo on 2016/10/26.
 */
import com.trio.breakFast.model.Tac_applicants;
import com.trio.breakFast.model.Tac_recruit;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.Tac_applicantsService;
import com.trio.breakFast.service.Tac_otoacommentService;
import com.trio.breakFast.service.Tac_recruitService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tacrecruit")
public class Tac_recruitController {

    @Autowired
    Tac_recruitService recruitService;
    @Autowired
    Tac_applicantsService tac_applicantsService;
    @Autowired
    Tac_otoacommentService tac_otoacommentService;

    @ResponseBody
    @RequestMapping(value = "/createRecruit", method = RequestMethod.POST)
    public MessageHelper createRecruit(Integer userid,String username,String title,String workplace ,
                                       String deadline,String phone,String workInfo ,String displaytime,
                                       Integer   needpeopleNum)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            recruitService.createRecruit(userid,username,title,workplace,deadline,phone,workInfo,displaytime,needpeopleNum);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("招聘提交成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }

    //根据recruitid 查看招聘消息
    //2016-10-28 21  VV
    @ResponseBody
    @RequestMapping(value = "/getRecruitByID", method = RequestMethod.POST)
    public DataHelper getRecruitByID(Integer recruitid)
    {
        DataHelper dataHelper =new DataHelper();
        try {

            Tac_recruit tac_recruit=recruitService.getRecruitByID(recruitid);
            dataHelper.setData(tac_recruit);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("根据recruitid 查看招聘成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //查看正在进行的招聘消息 包括 待审核和进行中  （rstatus=0）
    //2016-10-28 21  VV
    @ResponseBody
    @RequestMapping(value = "/getRecruiting", method = RequestMethod.POST)
    public DataHelper getRecruiting(Integer userid,Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {

            List<Tac_recruit> tac_recruitLists=recruitService.getRecruiting( userid,  page,  rows);
            dataHelper.setData(tac_recruitLists);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("查看正在进行的招聘成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //查看已失效的招聘信息，包括  审核未通过、已截止、取消招聘  （rstatus=1）
    //2016-10-28 21  VV
    @ResponseBody
    @RequestMapping(value = "/getRecruited", method = RequestMethod.POST)
    public DataHelper getRecruited(Integer userid,Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {

            List<Tac_recruit> tac_recruitLists=recruitService.getRecruited(userid, page, rows);
            dataHelper.setData(tac_recruitLists);
            dataHelper.setSuccess(true);

            dataHelper.setMessage("查看已失效招聘成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //合并了   ********************
    //查看正在进行的招聘消息
    //包括待审核和进行中（rstatus=0）   包括审核未通过、已截止、取消招聘(rstatus=1)
    //2016-11-7
    @ResponseBody
    @RequestMapping(value = "/getRecruitForRecruitor", method = RequestMethod.POST)
    public DataHelper getRecruitForRecruitor(Integer userid,Integer page, Integer rows,Integer rstatus)
    {
        DataHelper dataHelper =new DataHelper();
        try {

            List<Tac_recruit> tac_recruitLists=recruitService.getRecruitForRecruitor(userid, page, rows, rstatus);
            dataHelper.setData(tac_recruitLists);
            dataHelper.setSuccess(true);
            if(rstatus==0)
            {
                dataHelper.setMessage("查看进行中招聘成功（进行中）");
            }
            if(rstatus==1)
            {
                dataHelper.setMessage("查看已失效招聘成功（已失效）");
            }

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }


    //取消招聘，   改变招聘的状态 Status-> 4  重新评分
    //2016-10-28 21  VV
    @ResponseBody
    @RequestMapping(value = "/changeStatusOfRecruit", method = RequestMethod.POST)
    public MessageHelper changeStatusOfRecruit(Integer recruitid,Integer status)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            recruitService.changeStatusOfRecruit(recruitid,status);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("招聘状态修改成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }

    //----------------------------



    //查看报名列表
    //2016-10-29 13  VV
    @ResponseBody
    @RequestMapping(value = "/getApplicant", method = RequestMethod.POST)
    public DataHelper getApplicant(Integer recruitid, Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {

            List<Tac_applicants> tac_recruitLists=tac_applicantsService.getApplicant(recruitid, page, rows);
            dataHelper.setData(tac_recruitLists);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("查看报名列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //查看被选中的报名列表
    //2016-10-29 13  VV
    @ResponseBody
    @RequestMapping(value = "/getChoosenApplicant", method = RequestMethod.POST)
    public DataHelper getChoosenApplicant(Integer recruitid, Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {

            List<Tac_applicants> tac_recruitLists=tac_applicantsService.getChoosenApplicant(recruitid, page, rows);
            dataHelper.setData(tac_recruitLists);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("查看被选中报名列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //选择某个应聘者
    //2016-10-29 13  VV
    @ResponseBody
    @RequestMapping(value = "/chooseApplicant", method = RequestMethod.POST)
    public MessageHelper chooseApplicant(Integer recruitid,Integer userid)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_applicantsService.chooseApplicant(recruitid, userid);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("应聘者选择成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }



     ///------------------------

    // 招聘者评价应聘者
    //2016-10-29 16  VV
    @ResponseBody
    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    public MessageHelper createComment(Integer applicantsid,Integer recruitid,Integer applicantid,Integer ownerid,
                              String ownername,String comment,float point, String cmmentTime)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_otoacommentService.createComment( applicantsid, recruitid, applicantid, ownerid,
                                   ownername, comment, point,  cmmentTime);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("oTOa 评价成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }








    //检查是否已经选择
    //2016-10-29 13  VV
    @ResponseBody
    @RequestMapping(value = "/checkChoosen", method = RequestMethod.POST)
    public MessageHelper checkChoosen(Integer applicantsid)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_applicantsService.checkChoosen(applicantsid);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("应聘者已被选择");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }

    //招聘者取消选择某个应聘者 0-未选择 1-招聘者选择 2-招聘者取消
    //2016-11-15 19  VV
    @ResponseBody
    @RequestMapping(value = "/CancelChooseApplicant", method = RequestMethod.POST)
    public MessageHelper CancelChooseApplicant(Integer recruitid,Integer userid)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_applicantsService.CancelChooseApplicant(recruitid, userid);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("应聘者选择取消成功（招聘者）");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }



}
