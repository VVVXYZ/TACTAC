package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_atoocommentDao;
import com.trio.breakFast.dao.Tac_userDao;
import com.trio.breakFast.model.Tac_applicants;
import com.trio.breakFast.model.Tac_atoocomment;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.service.Tac_applicantsService;
import com.trio.breakFast.service.Tac_atoocommentService;

/**
 * Created by ienovo on 2016/10/26.
 */
import com.trio.breakFast.service.Tac_userService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Tac_atoocommentServiceImpl implements Tac_atoocommentService {


    @Autowired
    Tac_atoocommentDao tac_atoocommentDao;
    @Autowired
    Tac_userDao tac_userDao;
    @Autowired
    Tac_userService tac_userService;
    @Autowired
    Tac_applicantsService tac_applicantsService;

     //获取评价 根据 tac_applicants  id
     @Override
     public Tac_atoocomment getTac_atoocomment(Integer applicantsid)
     {

         if(applicantsid==null)
         {
             throw new ServiceException("用户ID不能为空" );
         }
         String hql="from Tac_atoocomment c " +
                 " where c.tac_applicants.applicantsid=:applicantsid";
         Map<String,Object> params=new HashMap<String,Object>();
         params.put("applicantsid", applicantsid);
         Tac_atoocomment tac_atoocomment;
         try
         {
              tac_atoocomment=tac_atoocommentDao.get(hql, params);
         }
         catch (Exception e)
         {
             throw new ServiceException("获取aTOo评价失败" );
         }

         return  tac_atoocomment;
     }


    //获取评价 根据 commentid  id
    @Override
    public Tac_atoocomment getTac_atoocommentByID(Integer commentid)
    {
        if(commentid==null)
        {
            throw new ServiceException("commentid不能为空" );
        }
        String hql="from Tac_atoocomment c where c.commentid=:commentid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("commentid", commentid);

        Tac_atoocomment tac_atoocomment;

        try
        {
            tac_atoocomment=tac_atoocommentDao.get(hql, params);
        }
        catch (Exception e)
        {
            throw new ServiceException("获取aTOo评价失败" );
        }


        return  tac_atoocomment;
    }


    //评价前要查看是否已评价，是否已选中，对被评价者的评分进行调整
    //
    @Override
    public void createComment(Integer applicantsid,Integer recruitid,Integer applicantid,Integer ownerid,
                              String ownername,String comment,float point, String cmmentTime)
    {

        if(getTac_atoocomment(applicantsid)!=null)
        {
            throw new ServiceException("不能重复评价" );
        }

        Tac_atoocomment tac_atoocomment=new Tac_atoocomment();

        Tac_user tac_user;
        Tac_user ownertac_user;
        Tac_applicants tac_applicants;

        try
        {
            tac_user=tac_userService.getUserByID(applicantid);
            ownertac_user=tac_userService.getUserByID(ownerid);
            tac_applicants=tac_applicantsService.getApplicantsByID(applicantsid);
        }
        catch (Exception e)
        {
            throw new ServiceException("评价失败" );
        }


        if(tac_applicants.getChoosen()!=1)
        {
            throw new ServiceException("未被选择不能评价" );
        }

        tac_atoocomment.setTac_applicants(tac_applicants);
        tac_atoocomment.setOwnerid(ownerid);
        tac_atoocomment.setOwnername(ownername);
        tac_atoocomment.setApplicantname(tac_user.getName());
        tac_atoocomment.setApplicantid(applicantid);
        tac_atoocomment.setATOoComment(comment);
        tac_atoocomment.setATOoPoint(point);
        tac_atoocomment.setATOotime(cmmentTime);
        tac_atoocomment.setRecruitid(recruitid);
        tac_atoocomment.setATOoStatus(0);//0-可显示  1-被屏蔽
        tac_atoocomment.setATOoTipoff(0);//0-未被举报 1-被举报  2-举报已处理

        try
        {
            ServiceHelper.create(tac_atoocommentDao, Tac_atoocomment.class, tac_atoocomment);

        }
        catch (Exception e)
        {
            throw new ServiceException("评价失败" );
        }


        float tmppoint=ownertac_user.getPoint();
        tmppoint=(point-tmppoint)/100+tmppoint;
        ownertac_user.setPoint(tmppoint);
        try
        {

            ServiceHelper.update(tac_userDao,Tac_user.class,ownertac_user);
        }
        catch (Exception e)
        {
            throw new ServiceException("计算评分失败" );
        }



    }


    //查询的时候 查看是否被屏蔽  查看应聘者对我的评价
    @Override
    public List<Tac_atoocomment> getComment(Integer ownerid,Integer page, Integer rows)
    {
        String hql = "from Tac_atoocomment c " +
                "where  c.ownerid=:ownerid and c.aTOoStatus=:aTOoStatus " +
                "order by c.aTOotime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ownerid", ownerid);
        params.put("aTOoStatus", 0);

        List<Tac_atoocomment> tac_atoocommentList ;

        try
        {

            tac_atoocommentList = tac_atoocommentDao.find(hql, params, page, rows);
        }
        catch (Exception e)
        {
            throw new ServiceException("查看应聘者对我的评价失败" );
        }


        return tac_atoocommentList;
    }

    //管理员查询的时候 查看是否被屏蔽  查看应聘者对我的评价
    @Override
    public List<Tac_atoocomment> getCommentForManager(Integer page, Integer rows)
    {
        String hql = "from Tac_atoocomment c " +
                "where   c.aTOoTipoff=:aTOoTipoff " +
                "order by c.aTOotime desc ";
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("aTOoTipoff", 1);

        List<Tac_atoocomment> tac_atoocommentList ;
        try
        {

            tac_atoocommentList=tac_atoocommentDao.find(hql, params, page, rows);
        }
        catch (Exception e)
        {
            throw new ServiceException("管理员查询评价失败" );
        }

        return tac_atoocommentList;
    }

    //举报
    @Override
    public void tipComment(Integer commentid)
    {

        Tac_atoocomment tac_atoocomment=getTac_atoocommentByID(commentid);
        if(tac_atoocomment==null)
        {
            throw new ServiceException("评价为空" );
        }
        tac_atoocomment.setATOoTipoff(1);
        try
        {

            ServiceHelper.update(tac_atoocommentDao,Tac_atoocomment.class,tac_atoocomment);
        }
        catch (Exception e)
        {
            throw new ServiceException("举报失败" );
        }


    }

    //处理举报  屏蔽（调用此函数）/不屏蔽
    //2016-10-30 21
    @Override
    public void checkComment(Integer commentid,String checktime,Integer status)
    {
        Tac_atoocomment tac_atoocomment=getTac_atoocommentByID(commentid);
        if(tac_atoocomment==null)
        {
            throw new ServiceException("评价为空" );
        }
        if(status==1)
        {
            tac_atoocomment.setATOoStatus(1);
        }
        tac_atoocomment.setATOoTipoff(2);
        tac_atoocomment.setATOoChecktime(checktime);
        try
        {

            ServiceHelper.update(tac_atoocommentDao,Tac_atoocomment.class,tac_atoocomment);
        }
        catch (Exception e)
        {
            throw new ServiceException("处理举报失败" );
        }

    }



}
