package com.trio.breakFast.controller;

/**
 * Created by ienovo on 2016/10/26.
 */
import com.trio.breakFast.model.Tac_applicants;
import com.trio.breakFast.model.Tac_recruit;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.Tac_applicantsService;
import com.trio.breakFast.service.Tac_atoocommentService;
import com.trio.breakFast.service.Tac_recruitService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tacapplication")
public class Tac_applicationController {

    @Autowired
    Tac_recruitService recruitService;
    @Autowired
    Tac_applicantsService tac_applicantsService;
    @Autowired
    Tac_atoocommentService tac_atoocommentService;
    //-----------------
    //应聘的人查看招聘列表
    //2016-10-29 13  VV
    @ResponseBody
    @RequestMapping(value = "/getRecruit", method = RequestMethod.POST)
    public DataHelper getRecruit(Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {

            List<Tac_recruit> tac_recruitLists=recruitService.getRecruit(page, rows);
            dataHelper.setData(tac_recruitLists);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("查看招聘成功(应聘者)");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }


    //查看某条招聘
    //2016-10-29 16  VV
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


    //申请应聘
    //2016-10-29 16  VV
    @ResponseBody
    @RequestMapping(value = "/createApplicants", method = RequestMethod.POST)
    public  MessageHelper createApplicants(Integer recruitid,String ownername,
                                  String applicanttime,Integer userid)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_applicantsService.createApplicants( recruitid, ownername, applicanttime, userid);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("申请应聘成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }



    // 查看申请列表
    //2016-10-29 16  VV
    @ResponseBody
    @RequestMapping(value = "/getApplicantForApplicant", method = RequestMethod.POST)
    public DataHelper getApplicantForApplicant(Integer userid, Integer page, Integer rows)
    {
        DataHelper dataHelper =new DataHelper();
        try {

            List<Tac_applicants> tac_applicantsList=tac_applicantsService.getApplicantForApplicant( userid,  page,  rows);
            dataHelper.setData(tac_applicantsList);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("应聘者查看申请列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    // 取消申请
    //2016-10-29 16  VV
    @ResponseBody
    @RequestMapping(value = "/cancelApplicants", method = RequestMethod.POST)
    public MessageHelper cancelApplicants(Integer applicantsid)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_applicantsService.changeApplicants(applicantsid,2);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("取消应聘申请成功");
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

    //------------

    // 应聘者评价招聘者  评价前需要检查自己是否被选择
    //2016-10-29 16  VV
    @ResponseBody
    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    public MessageHelper createComment(Integer applicantsid,Integer recruitid,Integer applicantid,Integer ownerid,
                              String ownername,String comment,float point, String cmmentTime)
    {
        MessageHelper messageHelper=new MessageHelper();
        try {
            tac_atoocommentService.createComment( applicantsid, recruitid, applicantid, ownerid,
                                  ownername, comment, point,  cmmentTime);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("aTOo 评价成功");

        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }








}
