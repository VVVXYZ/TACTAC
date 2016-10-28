package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_userDao;
import com.trio.breakFast.model.Tac_user;
import com.trio.breakFast.pageModel.FilterGroup;
import com.trio.breakFast.pageModel.FilterRule;
import com.trio.breakFast.service.Tac_userService;

/**
 * Created by ienovo on 2016/10/26.
 */
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.HqlUtil;
import com.trio.breakFast.util.SendmailUtil;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class Tac_userServiceImpl implements Tac_userService {

    @Autowired
    Tac_userDao tac_userDao;
    //发送验证码邮件，并返回验证码
    @Override
    public String checkEmail(String getterEmail)
    {
        String sendText=null;
        try {
             sendText=SendmailUtil.sendEmail(getterEmail);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  sendText;
    }

    @Override
    //根据用户名查询该用户
    public Tac_user get(String username) {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("name", HqlUtil.EQUAL, username));
        return ServiceHelper.get(tac_userDao, Tac_user.class, filterGroup);
    }
    //添加一条用户记录
    @Override
    public  void create(String name ,String password,String phone,String email)
    {
        if (get(name) != null) {
            throw new ServiceException("该用户已存在" + name);
        }
        Tac_user tac_user=new Tac_user();
        tac_user.setName(name);
        tac_user.setPasswd(password);
        tac_user.setPhone(phone);
        tac_user.setEmail(email);

        ServiceHelper.create(tac_userDao,Tac_user.class,tac_user);
    }

    //验证登录
    @Override
    public Tac_user judgeLogin(String name, String psw) {

        if(name == null || name.equals(""))
            throw new ServiceException("用户名不能为空");
        if(psw == null || psw.equals(""))
            throw new ServiceException("密码不能为空");

        //System.out.println(name);
        //System.out.println(psw);

        //String hql = "from User u where u.username="+name;
        String hql = "from Tac_user tu where tu.name=:name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        Tac_user tac_user = tac_userDao.get(hql, params);

        //User user = userDao.get(hql);

        if(tac_user==null)
        {
            throw new ServiceException("用户不存在！" );
        }
        //System.out.println(user.getUsername());
        //System.out.println(user.getPassword());


        if (tac_user.getPasswd().equals(psw)) {

        } else {
            throw new ServiceException("密码错误！" );
        }
        return tac_user;
    }


}
