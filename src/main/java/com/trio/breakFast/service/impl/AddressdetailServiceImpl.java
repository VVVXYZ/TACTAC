package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.AddressdetailDao;
import com.trio.breakFast.model.Addressdetail;
import com.trio.breakFast.model.User;
import com.trio.breakFast.service.AddressdetailService;
import com.trio.breakFast.service.UserService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus on 2016/7/26.
 */

@Service
public class AddressdetailServiceImpl implements AddressdetailService {
//    @Autowired
//    @Override

    @Autowired
    AddressdetailDao addressdetailDao;

    @Autowired
    UserService userService;


    //显示地址,得到某个人的收货地址集合
    @Override
    public List<Addressdetail> showAddress(String username)
    {
        if(username == null || username.length()<=0)
            throw new ServiceException("用户名不能为空");

        String hql = "from Addressdetail a where a.user.username=:username order by a.addressid desc";
        Map<String,Object> params=new HashMap<String,Object>();
        //String str=user_id+"";
        params.put("username", username);

        List<Addressdetail> addressdetails=addressdetailDao.find(hql,params);


        Addressdetail addressdetail =new Addressdetail();

        if(addressdetails.size()==0){
            throw new ServiceException("未找到该用户的地址" );
        }

        return addressdetails;
    }

    //返回默认地址
    @Override
    public Addressdetail getDefaultAdresss(String username) {
        if(username == null || username.length() <= 0)
            throw new ServiceException("用户名不能为空");

        Addressdetail addressdetail = null;

        List<Addressdetail> addressdetails = showAddress(username);

        addressdetail = addressdetails.get(0);

        if (addressdetail == null) {
            throw new ServiceException("未找到该用户的默认地址");
        }

        return addressdetail;
    }

    //修改某条地址
    @Override
    public void changeAddress(Integer addressid, String address,String receivername,String phone)
    {
        if(addressid == null || addressid <= 0)
            throw new ServiceException("地址编号有误");
        if(address == null || address.length() <= 0)
            throw new ServiceException("地址不能为空");
        if(receivername == null || receivername.length() <= 0)
            throw new ServiceException("收货人姓名不能为空");
        if(phone == null || phone.length() <= 0)
            throw new ServiceException("手机号不能为空");
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        if (!m.matches()) {
            throw new ServiceException("错误手机号");
        }

        String hql = "from Addressdetail a where a.addressid=:addressid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("addressid", addressid);
        Addressdetail  addressdetail=addressdetailDao.get(hql,params);

        if(addressdetail==null)
        {
            throw new ServiceException("未找到该条地址" );
        }

        addressdetail.setAddress(address);
        addressdetail.setPhone(phone);
        addressdetail.setReceivername(receivername);
        ServiceHelper.update(addressdetailDao,Addressdetail.class,addressdetail);


    }

    //删除收货地址
    @Override
    public void deleteAddress(Integer addressid)
    {
        if(addressid == null || addressid <= 0)
            throw new ServiceException("地址编号有误");

        String hql = "from Addressdetail a where a.addressid=:addressid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("addressid", addressid);
        Addressdetail  addressdetail=addressdetailDao.get(hql,params);

        if(addressdetail==null)
        {
            throw new ServiceException("该条地址并不存在" );
        }

        ServiceHelper.delete(addressdetailDao,Addressdetail.class,addressdetail);

    }

    //添加一条新的收货地址
    @Override
    public void addAddress(String username, String newAddress,String receivername,String phone)
    {
        if(username == null || username.length()<=0)
            throw new ServiceException("用户名不能为空");
        if(newAddress == null || newAddress.length()<=0)
            throw new ServiceException("地址不能为空");
        if(receivername == null || receivername.length()<=0)
            throw new ServiceException("收货人姓名不能为空");
        if(phone == null || phone.length()<=0)
            throw new ServiceException("手机号不能为空");

        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        if (!m.matches()) {
            throw new ServiceException("错误手机号");
        }

        System.out.println(newAddress);
        System.out.println("*************");
        User user = userService.getUser(username);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        Addressdetail addressdetail=new Addressdetail();
        addressdetail.setUsername(username);
        addressdetail.setAddress(newAddress);
        addressdetail.setReceivername(receivername);
        addressdetail.setPhone(phone);
        addressdetail.setUser(user);


        Integer flag=ServiceHelper.create(addressdetailDao, Addressdetail.class, addressdetail);

        if(flag==-1)
        {
            throw new ServiceException("添加新地址失败" );
        }
    }

}
