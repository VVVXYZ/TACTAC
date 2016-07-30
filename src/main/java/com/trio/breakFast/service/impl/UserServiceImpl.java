package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.UserDao;
import com.trio.breakFast.model.Security;
import com.trio.breakFast.model.User;
import com.trio.breakFast.pageModel.FilterGroup;
import com.trio.breakFast.pageModel.FilterRule;
import com.trio.breakFast.service.UserService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.HqlUtil;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 2016/7/26.
 */
@Service
public class UserServiceImpl implements UserService {



    //根据用户名查询用户
    @Override
    public User getUser(String username)
    {
        User user=get(username);
        if(user==null)
        {
            throw new ServiceException("显示接口用户查询失败");
        }

        return user;
    }

    @Override
    //根据用户名查询该用户
    public User get(String username) {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("username", HqlUtil.EQUAL, username));
        return ServiceHelper.get(userDao, User.class, filterGroup);
    }

    //注册
    @Override
    public void put(Security securityquestionid,String username,String password,String securitypsw,String confirmpsw,Boolean yes_or_no) {
        if(yes_or_no == false)
            throw new ServiceException("请同意协议");
       //passwordCheck(password,confirmpsw);
        if (get(username) != null) {
            throw new ServiceException("该用户已存在" + username);
        }
        User user =new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSecuritypsw(securitypsw);
        user.setSecurityquestionid(securityquestionid);

//        Integer flag=ServiceHelper.create(userDao, User.class, user);
       ServiceHelper.create(userDao, User.class, user);


//
//       if(flag == null)
//           throw new ServiceException("注册失败");
//        else
//           throw new ServiceException("注册成功" );

    }

    //密码与确认密码匹配
    public void passwordCheck(String password,String confirmpsw) {
        if (!password.equals(confirmpsw)) {
            throw new ServiceException( "两次输入密码不同" );
        }
    }

    @Autowired
    UserDao userDao;

    //验证登录
    @Override
    public User judgeLogin(String name, String psw) {

        String hql = "from User u where u.username=:name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        User user = userDao.get(hql, params);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());


        if (user.getPassword().equals(psw)) {

        } else {
            throw new ServiceException("登录失败" );
        }
        return user;
    }

    //验证密保
    @Override
    public void checkSecurity (String username,Security securityquestionid,String securitypsw) {
       User user= get(username);
        if(user==null)
        {
            throw new ServiceException("用户不存在" );
        }
        if(!user.getSecuritypsw().equals(securitypsw))
        {
            throw new ServiceException("答案错误" );
        }

    }

    //忘记密码(要验证密保后在修改) /记得密码直接修改  修改密码
    @Override
    public void changePassword(String username,String password,String confirmPsw){
        passwordCheck(password,confirmPsw);
        User user =get(username);
        user.setPassword(password);
        ServiceHelper.update(userDao, User.class, user);
    }


}
