package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.OrderdetailDao;
import com.trio.breakFast.dao.OrderlistDao;
import com.trio.breakFast.dao.UserDao;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.model.User;
import com.trio.breakFast.service.OrderlistService;
import com.trio.breakFast.service.UserService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 2016/7/26.
 */
@Service
public class OrderlistServiceImpl implements OrderlistService {

    @Autowired
    OrderlistDao orderlistDao;
    @Autowired
    OrderdetailDao orderdetailDao;

    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    //购物车   ****千万不要设置id ，id是自增长的
    @Override
    public Integer shopingCar(String username, Double amount, String datetime, String deliverymethod,
                              String paymentmethod,Integer orderstatus,String remark)
    {

        User user = userService.getUser(username);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sdf.parse(datetime);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer orderid=0;

        Orderlist orderlist=new Orderlist();
        orderlist.setUser(user);
        orderlist.setUsername(username);
        orderlist.setAmount(amount);
        orderlist.setDatetime(date);
        orderlist.setDeliverymethod(deliverymethod);
        orderlist.setPaymentmethod(paymentmethod);
        orderlist.setOrderstatus(orderstatus);
        orderlist.setRemark(remark);


        System.out.println("****************");
        Serializable flag=orderlistDao.save(orderlist);
        if(flag==null){
            throw new ServiceException("下单失败" );
        }

        System.out.println("####################");
        //  ServiceHelper.create(orderlistDao,Orderlist.class,orderlist);

        orderid=(Integer)flag;
        System.out.println("orderid" + orderid);
        return orderid;

    }


    //根据orderid返回订单记录
    @Override
    public Orderlist getOrderlistByOrderid(Integer orderid)
    {
        String hql="from Orderlist c where c.orderid=:orderid";
        Map<String,Object> params=new HashMap<String,Object>();
        String orderID=orderid+"";
        params.put("orderid",orderID);

        Orderlist orderlists=orderlistDao.get(hql, params);

        if(orderlists==null){
            throw new ServiceException("未搜索到订单记录" );
        }

        return orderlists;
    }


    //取消订单
    @Override
    public void cancelOrder(Integer orderid,String remark,Integer orderstatus){
        if(orderstatus != 1)
            throw new ServiceException("该订单不能取消");

        Orderlist orderlist=ServiceHelper.get(orderlistDao,Orderlist.class,orderid);

        if(orderlist==null){
            throw new ServiceException("该订单不存在" );
        }

        orderlist.setOrderstatus(0);
        orderlist.setRemark(remark);

        ServiceHelper.update(orderlistDao,Orderlist.class,orderlist);
    }
}
