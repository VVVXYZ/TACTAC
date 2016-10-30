package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_resumeDao;
import com.trio.breakFast.model.Tac_resume;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.service.Tac_resumeService;

/**
 * Created by ienovo on 2016/10/26.
 */
import com.trio.breakFast.service.Tac_userService;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Tac_resumeServiceImpl implements Tac_resumeService{


    @Autowired
    Tac_resumeDao tac_resumeDao;
    @Autowired
    Tac_userService tac_userService;

    //创建简历
    //2016-10-29 17
    @Override
    public void  createResume(String name, String nickname,String phone,String email ,
                              String singleResume,String detailResume)
    {
        Tac_resume tac_resume=new Tac_resume();
        Tac_user tac_user=tac_userService.get(name);
        //User不存在
        //提示？？？？？

        tac_resume.setTac_user(tac_user);
        tac_resume.setName(name);
        tac_resume.setNickname(nickname);
        tac_resume.setPhone(phone);
        tac_resume.setEmail(email);
        tac_resume.setSingleResume(singleResume);
        tac_resume.setDetailResume(detailResume);
        //判断是否创建成功
        //？？？？？？？？？

        ServiceHelper.create(tac_resumeDao,Tac_resume.class,tac_resume);

    }

    //根据userid 获取 简历
    @Override
    public Tac_resume getResumeBuID(Integer userid)
    {
        String hql="from Tac_resume c where c.tac_user.userid=:userid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userid", userid);

        Tac_resume tac_resume=tac_resumeDao.get(hql, params);
        return  tac_resume;
    }



    //更新简历
    //2016-10-29 17
    @Override
    public void  updateResume(Integer userid ,String name,String nickname,String phone,String email ,
                              String singleResume,String detailResume)
    {
        Tac_resume tac_resume=getResumeBuID(userid);

        tac_resume.setName(name);
        tac_resume.setNickname(nickname);
        tac_resume.setPhone(phone);
        tac_resume.setEmail(email);
        tac_resume.setSingleResume(singleResume);
        tac_resume.setDetailResume(detailResume);

        ServiceHelper.update(tac_resumeDao, Tac_resume.class, tac_resume);

    }

    //查询简历
    //2016-10-29 17
    @Override
    public Tac_resume  getResume(Integer userid)
    {
        Tac_resume tac_resume=getResumeBuID(userid);
        return  tac_resume;

    }

}
