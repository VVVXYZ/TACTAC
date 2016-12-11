package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_otoacommentDao;
import com.trio.breakFast.dao.Tac_userDao;
import com.trio.breakFast.model.Tac_applicants;
import com.trio.breakFast.model.Tac_atoocomment;
import com.trio.breakFast.model.Tac_otoacomment;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.service.Tac_applicantsService;
import com.trio.breakFast.service.Tac_otoacommentService;

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
public class Tac_otoacommentServiceImpl implements Tac_otoacommentService {


    @Autowired
    Tac_otoacommentDao tac_otoacommentDao;
    @Autowired
    Tac_userDao tac_userDao;
    @Autowired
    Tac_userService tac_userService;
    @Autowired
    Tac_applicantsService tac_applicantsService;

    //获取评价 根据 tac_applicants  id
    @Override
    public Tac_otoacomment getTac_otoacomment(Integer applicantsid)
    {

        String hql="from Tac_otoacomment c " +
                " where c.tac_applicants.applicantsid=:applicantsid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("applicantsid", applicantsid);

        Tac_otoacomment tac_otoacomment=tac_otoacommentDao.get(hql, params);
        if(tac_otoacomment==null)
        {
            throw new ServiceException("评价不存在" );
        }
        return  tac_otoacomment;
    }

    //获取评价 根据 commentid  id
    @Override
    public Tac_otoacomment getTac_otoacommentByID(Integer commentid)
    {

        String hql="from Tac_otoacomment c where c.commentid=:commentid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("commentid", commentid);

        Tac_otoacomment tac_otoacomment=tac_otoacommentDao.get(hql, params);
        if(tac_otoacomment==null)
        {
            throw new ServiceException("评价不存在" );
        }

        return  tac_otoacomment;
    }

    //评价前要查看是否已评价，对被评价者的评分进行调整
    //设置状态,tipoff
    //2016-10-30 21
    @Override
    public void createComment(Integer applicantsid,Integer recruitid,Integer applicantid,Integer ownerid,
                              String ownername,String comment,float point, String cmmentTime)
    {
        if(getTac_otoacomment(applicantsid)!=null)
        {
            throw new ServiceException("不能重复评价" );
        }
        Tac_otoacomment tac_otoacomment=new Tac_otoacomment();
        if(tac_otoacomment==null)
        {
            throw new ServiceException("评价不存在" );
        }
        Tac_user tac_user=tac_userService.getUserByID(applicantid);
        if(tac_user==null)
        {
            throw new ServiceException("用户不存在" );
        }
        Tac_applicants tac_applicants=tac_applicantsService.getApplicantsByID(applicantsid);
        if(tac_applicants==null)
        {
            throw new ServiceException("尚未申请" );
        }
        tac_otoacomment.setTac_applicants(tac_applicants);
        tac_otoacomment.setOwnerid(ownerid);
        tac_otoacomment.setOwnername(ownername);
        tac_otoacomment.setApplicantname(tac_user.getName());
        tac_otoacomment.setApplicantid(applicantid);
        tac_otoacomment.setOTOaComment(comment);
        tac_otoacomment.setOTOaPoint(point);
        tac_otoacomment.setOTOatime(cmmentTime);
        tac_otoacomment.setRecruitid(recruitid);
        tac_otoacomment.setOTOaStatus(0);//0-可显示  1-被屏蔽
        tac_otoacomment.setOTOaTipoff(0);//0-未被举报 1-被举报  2-举报已处理

        try
        {

            ServiceHelper.create(tac_otoacommentDao, Tac_otoacomment.class, tac_otoacomment);
        }
        catch (Exception e)
        {
            throw new ServiceException("评论失败" );
        }



        float tmppoint=tac_user.getPoint();

        tmppoint=(point-tmppoint)/100+tmppoint;
        tac_user.setPoint(tmppoint);
        try
        {

            ServiceHelper.update(tac_userDao, Tac_user.class, tac_user);
        }
        catch (Exception e)
        {
            throw new ServiceException("更新评分失败" );
        }



    }


    //查询的时候 查看是否被屏蔽  查看招聘者对我的评价
    //2016-10-30 21
    @Override
    public List<Tac_otoacomment> getComment(Integer applicantid,Integer page, Integer rows)
    {
        String hql = "from Tac_otoacomment c " +
                "where  c.applicantid=:applicantid and c.oTOaStatus=:oTOaStatus " +
                "order by c.oTOatime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("applicantid", applicantid);
        params.put("oTOaStatus", 0);

        List<Tac_otoacomment> tac_otoacommentList = tac_otoacommentDao.find(hql, params, page, rows);
        if(tac_otoacommentList==null)
        {
            throw new ServiceException("查询申请失败" );
        }
        return tac_otoacommentList;
    }


    //管理员查询的时候 查看是否被屏蔽  查看招聘者对我的评价
    //2016-10-30 21
    @Override
    public List<Tac_otoacomment> getCommentForManager(Integer page, Integer rows)
    {
        String hql = "from Tac_otoacomment c " +
                "where  c.oTOaTipoff=:oTOaTipoff " +
                "order by c.oTOatime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("oTOaTipoff", 1);

        List<Tac_otoacomment> tac_otoacommentList = tac_otoacommentDao.find(hql, params, page, rows);
        if(tac_otoacommentList==null)
        {
            throw new ServiceException("查询申请失败" );
        }
        return tac_otoacommentList;
    }

    //举报  招聘者对我的评价
    //2016-10-30 21
    @Override
    public void tipComment(Integer commentid)
    {

        Tac_otoacomment tac_otoacomment=getTac_otoacommentByID(commentid);
        tac_otoacomment.setOTOaTipoff(1);
        try
        {

            ServiceHelper.update(tac_otoacommentDao,Tac_otoacomment.class,tac_otoacomment);
        }
        catch (Exception e)
        {
            throw new ServiceException("举报评价失败" );
        }


    }

    //处理举报  屏蔽（调用此函数）/不屏蔽
    //2016-10-30 21
    //status
    @Override
    public void checkComment(Integer commentid,String checktime,Integer status)
    {
        Tac_otoacomment tac_otoacomment=getTac_otoacommentByID(commentid);
        if(status==1)
        {
            tac_otoacomment.setOTOaStatus(1);
        }

        tac_otoacomment.setOTOaTipoff(2);
        tac_otoacomment.setOTOaChecktime(checktime);
        try
        {

            ServiceHelper.update(tac_otoacommentDao,Tac_otoacomment.class,tac_otoacomment);
        }
        catch (Exception e)
        {
            throw new ServiceException("处理举报评价失败" );
        }

    }


}
