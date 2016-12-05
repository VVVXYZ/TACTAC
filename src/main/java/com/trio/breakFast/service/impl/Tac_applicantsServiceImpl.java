package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_applicantsDao;
import com.trio.breakFast.dao.Tac_recruitDao;
import com.trio.breakFast.model.Tac_applicants;
import com.trio.breakFast.model.Tac_recruit;
import com.trio.breakFast.model.Tac_resume;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.service.Tac_applicantsService;

/**
 * Created by ienovo on 2016/10/26.
 */
import com.trio.breakFast.service.Tac_recruitService;
import com.trio.breakFast.service.Tac_resumeService;
import com.trio.breakFast.service.Tac_userService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Tac_applicantsServiceImpl implements Tac_applicantsService{

    @Autowired
    Tac_applicantsDao tac_applicantsDao;
    @Autowired
    Tac_resumeService tac_resumeService;
    @Autowired
    Tac_userService tac_userService;
    @Autowired
    Tac_recruitService tac_recruitService;
    @Autowired
    Tac_recruitDao tac_recruitDao;


    //查看报名列表 招聘者用查看应聘者信息   未测试  报名时创建
    //2016-10-28 21  VV
    @Override
    public List<Tac_applicants> getApplicant(Integer recruitid, Integer page, Integer rows)
    {
        String hql = "from Tac_applicants c where c.tac_recruit.recruitid=:recruitid  order by c.applicanttime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("recruitid", recruitid);

        List<Tac_applicants> tac_applicantsLists = tac_applicantsDao.find(hql, params, page, rows);
        return tac_applicantsLists;
    }

    //查看被选中的报名列表 招聘者评价用  1
    //2016-10-29 15 21  VV
    @Override
    public List<Tac_applicants> getChoosenApplicant(Integer recruitid, Integer page, Integer rows)
    {
        String hql = "from Tac_applicants c where c.tac_recruit.recruitid=:recruitid and c.choosen=:choosen order by c.applicanttime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("recruitid", recruitid);
        params.put("choosen", 1);

        List<Tac_applicants> tac_applicantsLists = tac_applicantsDao.find(hql, params, page, rows);
        return tac_applicantsLists;
    }

    //选择某个应聘者
    //2016-10-28 21  VV
    @Override
    public void chooseApplicant(Integer recruitid,Integer userid)
    {
        String hql = "from Tac_applicants c where c.tac_recruit.recruitid=:recruitid and c.tac_user.userid=:userid ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("recruitid", recruitid);
        params.put("userid", userid);

        Tac_applicants tac_applicants=tac_applicantsDao.get(hql, params);
        if(tac_applicants==null)
        {
            throw new ServiceException("该条申请不存在，无法选择" );
        }
        Integer status=tac_applicants.getChoosen();
        if(status==2)
        {
            throw new ServiceException("申请被应聘者取消，无法选择" );
        }
        tac_applicants.setChoosen(1);
        ServiceHelper.update(tac_applicantsDao,Tac_applicants.class,tac_applicants);


    }

    //招聘者取消选择某个应聘者
    // 0-未选择 1-招聘者选择 2-应聘者取消 3-招聘者取消

    //2016-11-15 19  VV
    @Override
    public void CancelChooseApplicant(Integer recruitid,Integer userid)
    {
        String hql = "from Tac_applicants c where c.tac_recruit.recruitid=:recruitid and c.tac_user.userid=:userid ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("recruitid", recruitid);
        params.put("userid", userid);

        Tac_applicants tac_applicants=tac_applicantsDao.get(hql, params);
        if(tac_applicants==null)
        {
            throw new ServiceException("该条申请不存在，无法取消选择" );
        }
        Integer status=tac_applicants.getChoosen();
        if(status==0)
        {
            throw new ServiceException("申请尚未选择，无法取消选择" );
        }

        if(status==2)
        {
            throw new ServiceException("申请被应聘者取消，无法取消选择" );
        }
        tac_applicants.setChoosen(3);
        ServiceHelper.update(tac_applicantsDao,Tac_applicants.class,tac_applicants);


    }

    //检查是否已经选择   评价前使用 被选择才可以进行评价
    //2016-10-28 21  VV
    @Override
    public void checkChoosen(Integer applicantsid)
    {
        String hql = "from Tac_applicants c where c.applicantsid=:applicantsid  ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("applicantsid", applicantsid);

        Tac_applicants tac_applicants=tac_applicantsDao.get(hql, params);
        if(tac_applicants.getChoosen()==0)
        {
            throw new ServiceException("未被选择" );
        }
        if(tac_applicants.getChoosen()==2)
        {
            throw new ServiceException("申请已取消" );
        }
    }



    ////查看申请列表 应聘者用查看招聘信息
    // 2016-10-29 15
    //*****
    @Override
    public List<Tac_applicants> getApplicantForApplicant(Integer userid, Integer page, Integer rows)
    {
        //choosen
        String hql = "from Tac_applicants c " +
                "where c.tac_user.userid=:userid and (c.choosen=0 or c.choosen=1 or c.choosen=3 )  " +
                " order by c.applicanttime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);

        List<Tac_applicants> tac_applicantsLists = tac_applicantsDao.find(hql, params, page, rows);
        return tac_applicantsLists;
    }

    //获取申请，通过id
    @Override
    public  Tac_applicants getApplicantsByID(Integer applicantsid)
    {
        String hql = "from Tac_applicants c where c.applicantsid=:applicantsid   ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("applicantsid", applicantsid);

        Tac_applicants tac_applicants = tac_applicantsDao.get(hql, params);
        return tac_applicants;
    }

    //根据  recruitid 和 userid 获取申请  验证是否已存在，已存在则不能在申请
    //2016-10-29 13  VV
    @Override
    public  Tac_applicants getApplicants(Integer recruitid,Integer userid)
    {
        String hql = "from Tac_applicants c where c.tac_user.userid=:userid and c.tac_recruit.recruitid=:recruitid  ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        params.put("recruitid", recruitid);

        Tac_applicants tac_applicants = tac_applicantsDao.get(hql, params);
        System.out.println("---------------");
        if(tac_applicants!=null)
        {
            System.out.println(tac_applicants.getOwnername());

        }
        else
        {
            System.out.println("为空。。。。");
        }
        System.out.println("---------------");
        return tac_applicants;
    }

    //根据  创建申请 （创建前要先检查，及先获取，若无在创建）
    //2016-10-29 13  VV
    @Override
    public  void createApplicants(Integer recruitid,String ownername,String applicanttime,Integer userid)
    {
       if(getApplicants(recruitid, userid)!=null)
       {
           throw new ServiceException("您已经申请过了" );
       }
        //获取个人简历  根据userid
        Tac_resume tac_resume=tac_resumeService.getResume(userid);
        if(tac_resume==null)
        {

            System.out.println("tac_resume  为空。。。。");
            throw new ServiceException("简历不存在，不能申请" );
        }
        //获取个人信息  根据ownername 设置ownerid
        Tac_user tac_user=tac_userService.getUserByID(userid);

        if(tac_user==null)
        {
            System.out.println("tac_user  为空。。。。");
            throw new ServiceException("用户不存在，不能申请" );
        }
        else
            System.out.println("tac_user  name"+tac_user.getName());

        //获取招聘
        Tac_recruit tac_recruit=tac_recruitService.getRecruitByID(recruitid);
        if(tac_recruit==null)
        {
            System.out.println("tac_recruit  为空。。。。");
            throw new ServiceException("招聘不存在，不能申请" );
        }
        //应聘人数加1
        if(tac_recruit.getApplypeopleNum()==null)
        {
            throw new ServiceException("应聘人数为空" );
        }

         int num=tac_recruit.getApplypeopleNum();
        System.out.println("-------num="+num);
        tac_recruit.setApplypeopleNum(num+1);
        ServiceHelper.update(tac_recruitDao,Tac_recruit.class,tac_recruit);

        Tac_applicants tac_applicants=new Tac_applicants();
        tac_applicants.setTac_user(tac_user);
        tac_applicants.setApplicantname(tac_resume.getTac_user().getName());
        tac_applicants.setPoint(tac_resume.getTac_user().getPoint());
        tac_applicants.setSingleresume(tac_resume.getSingleResume());
        tac_applicants.setImage(tac_resume.getTac_user().getImage());
        tac_applicants.setTac_recruit(tac_recruit);

        tac_applicants.setApplicanttime(applicanttime);
        tac_applicants.setOwnerid(tac_recruit.getTac_user().getUserid());
        tac_applicants.setOwnername(ownername);
        tac_applicants.setTitle(tac_recruit.getTitle());
        tac_applicants.setSingleInfo(tac_recruit.getSingleInfo());
        System.out.println("tac_recruit.getSingleInfo() ===== "+tac_recruit.getSingleInfo());
        tac_applicants.setDeadline(tac_recruit.getDealdine());
        tac_applicants.setChoosen(0);//设置choosen  0
        ServiceHelper.create(tac_applicantsDao,Tac_applicants.class,tac_applicants);


    }

    //改变申请状态（用户取消申请 ）
    // 0-申请未被选中  1-选中  2-应聘者取消申请 3-招聘者取消选择
    //1. 选中后可以改为 取消申请   2. 取消申请后不可以被选中
    @Override
    public void changeApplicants(Integer applicantsid,Integer status)
    {
        Tac_applicants tac_applicants=getApplicantsByID(applicantsid);

        tac_applicants.setChoosen(status);

        ServiceHelper.update(tac_applicantsDao,Tac_applicants.class,tac_applicants);
    }


}
