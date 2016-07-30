package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.UsermessageDao;
import com.trio.breakFast.model.Usermessage;
import com.trio.breakFast.service.UsermessageService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 2016/7/26.
 */
@Service
public class UsermessageServiceImpl implements UsermessageService {


    @Autowired
    UsermessageDao usermessageDao;

    //显示消息，得到某条消息
    @Override
    public Usermessage getUsermessage(Integer userid,Integer orderid)
    {
        String hql="from Usermessage um where um.userid=:userid and um.orderid=:orderid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userid",userid+"");
        params.put("orderid",orderid+"");
        Usermessage usermessage=usermessageDao.get(hql,params);

        if(usermessage==null)
        {
            throw new ServiceException("未找到该信息" );
        }

        return usermessage;
    }
}
