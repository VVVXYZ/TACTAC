package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.Tac_resumeDao;
import com.trio.breakFast.dao.Tac_userDao;
import com.trio.breakFast.dao.impl.Tac_resumeDaoImpl;
import com.trio.breakFast.dao.impl.Tac_userDaoImpl;
import com.trio.breakFast.model.Tac_resume;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class Tac_userServiceImpl implements Tac_userService {


    //@Mock
    @Autowired
    Tac_userDao tac_userDao;

    @Autowired
    Tac_resumeServiceImpl tac_resumeService;

     @Autowired
    Tac_resumeDao tac_resumeDao;


    //发送验证码邮件，并返回验证码
    @Override
    public String checkEmail(String getterEmail)
    {
        if(getterEmail==null)
        {
            throw new ServiceException("邮箱不能为空！" );
        }
        String sendText=null;
        try {
             sendText=SendmailUtil.sendEmail(getterEmail);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ServiceException("邮箱发送失败！" );
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

    @Override
    //根据用户id查询该用户
    public Tac_user getUserByID(Integer userid)
    {
        String hql = "from Tac_user tu where tu.userid=:userid";
        Map<String, Object> params = new HashMap<>();
        params.put("userid", userid);

        Tac_user tac_user = tac_userDao.get(hql, params);

        if(tac_user==null)
        {
            //System.out.println("*************tac_user==null**********");
            throw new ServiceException("用户不存在！" );
        }
        return  tac_user;
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
        tac_user.setPoint(60.0f);
        tac_user.setType(0);//0-普通用户
        try
        {
            ServiceHelper.create(tac_userDao,Tac_user.class,tac_user);
        }
        catch (Exception e)
        {
            throw new ServiceException("添加用户失败" );
        }

        //创建简历
        Tac_resume tac_resume=new Tac_resume();
        tac_resume.setTac_user(tac_user);
        tac_resume.setName(name);
        tac_resume.setPhone(phone);
        tac_resume.setEmail(email);
        try
        {
            ServiceHelper.create(tac_resumeDao,Tac_resume.class,tac_resume);
        }
        catch (Exception e)
        {
            throw new ServiceException("添加简历失败" );
        }



    }

    //更新用户资料
    @Override
    public  void update(Integer useid,String name ,String password,String phone,String email)
    {
        Tac_user tac_user=getUserByID(useid);
        if (tac_user== null) {
            throw new ServiceException("用户不存在" + name);
        }

        tac_user.setName(name);
        tac_user.setPasswd(password);
        tac_user.setPhone(phone);
        tac_user.setEmail(email);

        try
        {
            ServiceHelper.update(tac_userDao, Tac_user.class, tac_user);
        }
        catch (Exception e)
        {
            throw new ServiceException("更新用户资料失败" );
        }
        //ServiceHelper.update(tac_userDao,Tac_user.class,tac_user);

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

        //System.out.println("name="+name);
        Tac_user tac_user = tac_userDao.get(hql, params);


        if(tac_user==null)
        {
            throw new ServiceException("用户不存在！" );
        }
        //System.out.println(user.getUsername());
        //System.out.println(user.getPassword());


        if (!tac_user.getPasswd().equals(psw))
        {

            throw new ServiceException("密码错误！" );
        }
        return tac_user;
    }

     //---------
    //2016-11-10

    //重置密码
    @Override
    public void updatePassword(String username,String password)
    {
        if(username==null)
        {
            throw new ServiceException("用户名不能为空！" );
        }
        if(password==null)
        {
            throw new ServiceException("密码不能为空！" );
        }
        Tac_user tac_user=get(username);
        tac_user.setPasswd(password);

        try
        {
            ServiceHelper.update(tac_userDao,Tac_user.class,tac_user);
        }
        catch (Exception e)
        {
            throw new ServiceException("重置密码失败" );
        }
    }
}
