package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_adviceDao;
import com.trio.breakFast.model.Tac_advice;
import com.trio.breakFast.model.Tac_applicants;
import com.trio.breakFast.service.Tac_adviceService;

/**
 * Created by ienovo on 2016/10/26.
 */

import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Tac_adviceServiceImpl implements Tac_adviceService{

    @Autowired
    Tac_adviceDao tac_adviceDao;

    //创建建议
    @Override
    public void createAdvice(Integer userid,String username,String time,String phone,String advice)
    {
        Tac_advice tac_advice=new Tac_advice();

        tac_advice.setUserid(userid);
        tac_advice.setUsername(username);
        tac_advice.setTime(time);
        tac_advice.setPhone(phone);
        tac_advice.setAdvice(advice);

        try
        {
            ServiceHelper.create(tac_adviceDao, Tac_advice.class, tac_advice);
        }
        catch (Exception e)
        {
            throw new ServiceException("建议提交失败" );
        }


    }

    //查看建议
    @Override
    public List<Tac_advice> getAdvice(Integer page, Integer rows)
    {
        String hql = "from Tac_advice c  order by c.time desc ";
        Map<String, Object> params = new HashMap<String, Object>();


        List<Tac_advice> tac_adviceList = tac_adviceDao.find(hql, params, page, rows);
        if(tac_adviceList==null)
        {
            throw new ServiceException("查看建议失败" );
        }
        return tac_adviceList;
    }


}
