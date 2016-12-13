package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_recruitDao;
import com.trio.breakFast.dao.impl.Tac_recruitDaoImpl;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.model.Tac_advice;
import com.trio.breakFast.model.Tac_recruit;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.service.Tac_recruitService;

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
public class Tac_recruitServiceImpl implements Tac_recruitService{

    //设置状态待审核  0-待审核  1-审核未通过  2-进行中  3-已截止 4-取消招聘
    @Autowired
    Tac_userService tac_userService;
    @Autowired

    Tac_recruitDao tac_recruitDao;


     //写个函数刷新状态
    //2016-10-28 21  VV
    //

    //创建一条新的招聘信息  //注意加号

    @Override

    public void createRecruit(Integer userid,String username,
                              String title,String workplace ,String deadline,
                              String phone,String workInfo ,String displaytime,
                               Integer   needpeopleNum )
    {
        //for(int i=0;i<i)
       /* title=java.net.URLDecoder.decode(title);
        workInfo=java.net.URLDecoder.decode(workInfo);


        System.out.println("-------------");
        System.out.println("title"+title);
        System.out.println("workInfo"+workInfo);
        System.out.println("-------------");
        title=title+"++";
        workInfo=workInfo+"++";
        title=java.net.URLEncoder.encode(title);
        workInfo=java.net.URLEncoder.encode(workInfo);

        System.out.println("****************");
        System.out.println("title"+title);
        System.out.println("workInfo"+workInfo);
        System.out.println("***********");*/

        Tac_recruit tac_recruit=new Tac_recruit();
        Tac_user tac_user;
        try
        {
            tac_user=tac_userService.get(username);
        }
        catch (Exception e)
        {
            throw new ServiceException("创建一条新的招聘失败" );
        }
        if(tac_user==null)
        {
            throw new ServiceException("创建一条新的招聘失败" );
        }

        tac_recruit.setTac_user(tac_user);
        tac_recruit.setOwner(username);
        tac_recruit.setTitle(title);
        tac_recruit.setWorkplace(workplace);
        tac_recruit.setWorkInfo(workInfo);
        tac_recruit.setDealdine(deadline);
        tac_recruit.setPhone(phone);
        tac_recruit.setDisplaytime(displaytime);
        //设置状态待审核  0-待审核  1-审核未通过  2-进行中  3-已截止 4-取消招聘
        tac_recruit.setStatus(0);
        tac_recruit.setNeedpeopleNum(needpeopleNum);
        tac_recruit.setApplypeopleNum(0);

        try
        {
            ServiceHelper.create(tac_recruitDao,Tac_recruit.class,tac_recruit);
        }
        catch (Exception e)
        {
            throw new ServiceException("创建一条新的招聘失败" );
        }


    }

    //根据recruitid 查看招聘消息
    //2016-10-28 21  VV
    @Override
    public Tac_recruit getRecruitByID(Integer recruitid)
    {
        String hql="from Tac_recruit c where c.recruitid=:recruitid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("recruitid", recruitid);
        Tac_recruit tac_recruit ;

        try
        {
            tac_recruit=tac_recruitDao.get(hql, params);
        }
        catch (Exception e)
        {
            throw new ServiceException("查看招聘消息失败" );

        }
        if(tac_recruit==null)
        {
            throw new ServiceException("查看招聘消息为空" );
        }
        return tac_recruit;
    }


    //查看正在进行的招聘消息 包括 待审核和进行中
    //2016-10-28 21  VV
    @Override
    public  List<Tac_recruit> getRecruiting(Integer userid,Integer page, Integer rows)
    {
        String hql = "from Tac_recruit c where c.tac_user.userid=:userid and (c.status=:stu1 or c.status=:stu2) order by c.displaytime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        params.put("stu1", 0);
        params.put("stu2", 2);

        List<Tac_recruit> tac_applicantsLists ;

        try
        {
            tac_applicantsLists = tac_recruitDao.find(hql, params, page, rows);
        }
        catch (Exception e)
        {
            throw new ServiceException("查看正在进行的招聘失败" );

        }
        if(tac_applicantsLists==null)
        {
            throw new ServiceException("在进行的招聘消息为空" );
        }

            return tac_applicantsLists;
    }

    //查看已经截止的招聘信息，包括  审核未通过、已截止、取消招聘
    //2016-10-28 21  VV
    @Override
    public List<Tac_recruit> getRecruited(Integer userid,Integer page, Integer rows)
    {
        String hql = "from Tac_recruit c " +
                "where c.tac_user.userid=:userid and (c.status=:stu1 or c.status=:stu2 or c.status=:stu3) " +
                "order by c.displaytime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        params.put("stu1", 1);
        params.put("stu2", 3);
        params.put("stu3", 4);

        List<Tac_recruit> tac_applicantsLists ;
        try
        {
            tac_applicantsLists = tac_recruitDao.find(hql, params, page, rows);
        }
        catch (Exception e)
        {
            throw new ServiceException("查看已经截止的招聘失败" );

        }
        if(tac_applicantsLists==null)
        {
            throw new ServiceException("已经截止的招聘消息为空" );
        }
        return tac_applicantsLists;

    }

    //合并了   ********************
    //查看正在进行的招聘消息
    //包括待审核和进行中（rstatus=0）   包括审核未通过、已截止、取消招聘(rstatus=1)
    //2016-10-28 21  VV
    @Override
    public  List<Tac_recruit> getRecruitForRecruitor(Integer userid,Integer page, Integer rows,Integer rstatus)
    {
        String hql="";
        Map<String, Object> params = new HashMap<String, Object>();

       if(rstatus==0)
       {
           //待审核和进行中（rstatus=0）
           hql = "from Tac_recruit c " +
                   "where c.tac_user.userid=:userid and (c.status=:stu1 or c.status=:stu2) " +
                   "order by c.displaytime desc ";
           params.put("userid", userid);
           params.put("stu1", 0);
           params.put("stu2", 2);
       }
        if(rstatus==1)
        {
            // 审核未通过、已截止、取消招聘(rstatus=1)
            hql = "from Tac_recruit c " +
                    "where c.tac_user.userid=:userid and (c.status=:stu1 or c.status=:stu2 or c.status=:stu3) " +
                    "order by c.displaytime desc ";
            params.put("userid", userid);
            params.put("stu1", 1);
            params.put("stu2", 3);
            params.put("stu3", 4);
        }
        List<Tac_recruit> tac_applicantsLists;

        try
        {
            tac_applicantsLists = tac_recruitDao.find(hql, params, page, rows);
        }
        catch (Exception e)
        {
            throw new ServiceException("查看招聘失败" );

        }
        if(tac_applicantsLists==null)
        {
            throw new ServiceException("招聘消息为空" );
        }
        return tac_applicantsLists;
    }



    //取消招聘，改变招聘的状态 Status-> 4  重新评分
    //2016-10-28 21  VV
    @Override
    public void changeStatusOfRecruit(Integer recruitid,Integer status)
    {
        Tac_recruit tac_recruit=ServiceHelper.get(tac_recruitDao,Tac_recruit.class,recruitid);
        Integer status1=tac_recruit.getStatus();


        tac_recruit.setStatus(status);
        try
        {
            ServiceHelper.update(tac_recruitDao,Tac_recruit.class,tac_recruit);
        }
        catch (Exception e)
        {
            throw new ServiceException("取消招聘失败" );

        }



    }


    //审核招聘，改变招聘的状态 以及理由 Status-> 4  重新评分
    //2016-12-6 21  VV
    @Override
    public void changeStatusOfRecruitAndReason(Integer recruitid,Integer status,String reason)
    {

        Tac_recruit tac_recruit=ServiceHelper.get(tac_recruitDao,Tac_recruit.class,recruitid);
        if(tac_recruit==null)
        {
            throw new ServiceException("招聘为空" );
        }
        Integer status1=tac_recruit.getStatus();
        tac_recruit.setReason(reason);
        tac_recruit.setStatus(status);
        try
        {
            ServiceHelper.update(tac_recruitDao,Tac_recruit.class,tac_recruit);
        }
        catch (Exception e)
        {
            throw new ServiceException("审核招聘失败" );
        }



    }
    //---------------





    //应聘的人查看招聘
    //2016-10-29 13  VV
    @Override
    public  List<Tac_recruit> getRecruit(Integer page, Integer rows)
    {
        String hql = "from Tac_recruit c where  c.status=:stu2 order by c.displaytime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("stu2", 2);

        List<Tac_recruit> tac_applicantsLists ;
        try
        {
            tac_applicantsLists = tac_recruitDao.find(hql, params, page, rows);
        }
        catch (Exception e)
        {
            throw new ServiceException("查看招聘失败" );

        }
        if(tac_applicantsLists==null)
        {
            throw new ServiceException("招聘消息为空" );
        }
        return tac_applicantsLists;
    }

    //管理员查看招聘
    //2016-10-29 13  VV
    @Override
    public  List<Tac_recruit> getRecruitForManager(Integer page, Integer rows)
    {
        String hql = "from Tac_recruit c where  c.status=:stu2 order by c.displaytime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("stu2", 0);

        List<Tac_recruit> tac_applicantsLists ;
        try
        {
            tac_applicantsLists = tac_recruitDao.find(hql, params, page, rows);
        }
        catch (Exception e)
        {
            throw new ServiceException("查看招聘失败" );

        }
        if(tac_applicantsLists==null)
        {
            throw new ServiceException("招聘消息为空" );
        }
        return tac_applicantsLists;
    }

    //合并了 ************
    //管理员查看招聘 (rstatus=0)  应聘的人查看招聘(rstatus=1)
    //2016-11-7 13  VV
    @Override
    public  List<Tac_recruit> getRecruitForManagerAndApplicator(Integer page, Integer rows,Integer rstatus)
    {
        String hql="";
        Map<String, Object> params = new HashMap<String, Object>();
        if(rstatus==0)
        {
            hql = "from Tac_recruit c where  c.status=:stu2 order by c.displaytime desc ";
            params.put("stu2", 0);
        }
        if(rstatus==1)
        {
            hql = "from Tac_recruit c where  c.status=:stu2 order by c.displaytime desc ";
            params.put("stu2", 2);
        }


        List<Tac_recruit> tac_applicantsLists ;
        try
        {
            tac_applicantsLists = tac_recruitDao.find(hql, params, page, rows);
        }
        catch (Exception e)
        {
            throw new ServiceException("查看招聘失败" );

        }
        if(tac_applicantsLists==null)
        {
            throw new ServiceException("招聘消息为空" );
        }
        return tac_applicantsLists;
    }

}
