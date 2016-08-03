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
        String hql = "from Addressdetail a where a.user.username=:username";
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


    //修改某条地址
    @Override
    public void changeAddress(String username, String address_content, String newaddress,String receivername,String phone)
    {
        String hql = "from Addressdetail a where a.username=:username and a.address=:address";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("username", username);
        params.put("address",address_content);
        Addressdetail  addressdetail=addressdetailDao.get(hql,params);

        if(addressdetail==null)
        {
            throw new ServiceException("未找到该条地址" );
        }

        addressdetail.setAddress(newaddress);
        addressdetail.setPhone(phone);
        addressdetail.setReceivername(receivername);
        ServiceHelper.update(addressdetailDao,Addressdetail.class,addressdetail);

    }

    //删除收货地址
    @Override
    public void deleteAddress(String username, String address_content)
    {
        String hql = "from Addressdetail a where a.username=:username and a.address=:address";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("username", username);
        params.put("address",address_content);
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
