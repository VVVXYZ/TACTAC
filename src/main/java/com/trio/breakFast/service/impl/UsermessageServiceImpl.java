package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.UsermessageDao;
import com.trio.breakFast.model.Usermessage;
import com.trio.breakFast.service.UsermessageService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2016/7/26.
 */
@Service
public class UsermessageServiceImpl implements UsermessageService {



    @Autowired
    UsermessageDao usermessageDao;

    //显示系统消息，列表
    @Override
    public List<Usermessage> getUsermessageList(String username, Integer page, Integer rows) {
        String hql = "from Usermessage  u where u.user.username=:username  order by  u.datetime      ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        List<Usermessage> usermessages = usermessageDao.find(hql, params, page, rows);
        // Usermessage usermessage=usermessages.get(1);
        //String usermessage.getDatetime();
        System.out.println("系统消息有返回");

        if (usermessages.size() == 0)
            throw new ServiceException("刷新系统信息失败");

        return usermessages;
    }


    //显示系统消息，得到某条消息
    @Override
    public Usermessage getUsermessage(Integer userid,Integer orderid)
    {
        System.out.println(userid+" "+orderid);
        System.out.println("显示消息接口 + service  1");
        String hql="from Usermessage um where um.userid=:userid and um.orderid=:orderid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userid",userid+"");
        params.put("orderid",orderid+"");
        Usermessage usermessage=usermessageDao.get(hql,params);

        System.out.println("显示消息接口 + service   2");
        if(usermessage==null)
        {
            throw new ServiceException("未找到该信息" );
        }

        System.out.println("显示消息接口 + service  3");
        return usermessage;
    }
}
