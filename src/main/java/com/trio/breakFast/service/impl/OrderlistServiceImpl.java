package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.CommodityDao;
import com.trio.breakFast.dao.OrderdetailDao;
import com.trio.breakFast.dao.OrderlistDao;
import com.trio.breakFast.dao.UserDao;
import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.model.User;
import com.trio.breakFast.service.CommodityService;
import com.trio.breakFast.service.OrderdetailService;
import com.trio.breakFast.service.OrderlistService;
import com.trio.breakFast.service.UserService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    OrderdetailService orderdetailService;

    @Autowired
    CommodityService commodityService;
    @Autowired
    CommodityDao commodityDao;

    //购物车   ****千万不要设置id ，id是自增长的
    @Override
    public Integer shopingCar(String username, Double amount, String datetime, String deliverymethod,
                              String paymentmethod, Integer orderstatus, String remark, String adress,
                              String receivername,String phone)
    {

        User user = userService.getUser(username);


        Integer orderid=0;

        Orderlist orderlist=new Orderlist();
        orderlist.setUser(user);
        orderlist.setUsername(username);
        orderlist.setAmount(amount);
        orderlist.setDatetime(datetime);
        orderlist.setDeliverymethod(deliverymethod);
        orderlist.setPaymentmethod(paymentmethod);
        orderlist.setOrderstatus(orderstatus);
        orderlist.setRemark(remark);
        orderlist.setAdress(adress);
        orderlist.setReceivername(receivername);
        orderlist.setPhone(phone);

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
        params.put("orderid", orderid);

        Orderlist orderlists=orderlistDao.get(hql, params);


        if(orderlists==null){
            throw new ServiceException("未搜索到订单记录" );
        }

        return orderlists;
    }


    //根据username返回订单记录  状态  为1
    @Override
    public List<Orderlist> getOrderlistByUsername(String username, Integer page, Integer rows, String type) {
        String hql = "from Orderlist c where c.username=:username and c.orderstatus=:orderstatus";
        Map<String, Object> params = new HashMap<String, Object>();
        Integer totype = Integer.parseInt(type);
        params.put("username", username);
        params.put("orderstatus", totype);

        List<Orderlist> orderlists = orderlistDao.find(hql, params, page, rows);
        List<Orderlist> orderlists1 = new ArrayList<Orderlist>();

        if (orderlists == null) {
            throw new ServiceException("未搜索到订单列表记录");
        }

        for (int i = 0; i < orderlists.size(); i++) {
            Orderlist orderlist = null;
            orderlist = orderlists.get(i);

            //orderlists.
            System.out.println("*********** 订单时间：" + orderlist.getDatetime());
        }
        return orderlists;
    }


    //根据username返回订单明细记录  状态为 1
    @Override
    public List<Orderdetail> getDetailByList(String username, Integer page, Integer rows, String type) {

        List<Orderdetail> ods = new ArrayList<Orderdetail>();
        List<Orderlist> orderlists = getOrderlistByUsername(username, page, rows, type);

        System.out.println("orderlists  size=" + orderlists.size());
        for (Integer i = 0; i < orderlists.size(); i++) {
            Orderlist orderlist = orderlists.get(i);
            Integer orderid = orderlist.getOrderid();
            List<Orderdetail> orderdetails = orderdetailService.getOrderdetailOn(orderid, type);
            System.out.println("****************orderdetails size=" + orderdetails.size());
            for (Integer j = 0; j < orderdetails.size(); j++) {
                // orderdetails.
                Orderdetail orderdetail = orderdetails.get(j);
                System.out.println("###############" + orderdetail.getCommodityname());
                ods.add(orderdetail);
            }
        }

        System.out.println("ods.size=" + ods.size());
        if (ods.size() == 0) {
            throw new ServiceException("订单明细列表获取失败");
        }

        return ods;
    }


    //取消订单
    @Override
    public void cancelOrder(Integer orderid, String remark) {



        Orderlist orderlist=ServiceHelper.get(orderlistDao,Orderlist.class,orderid);
        Integer orderstatus = orderlist.getOrderstatus();

        if(orderlist==null){
            throw new ServiceException("该订单不存在" );
        }
        if (orderstatus == 0)
            throw new ServiceException("该订单已取消");
        if (orderstatus == 2)
            throw new ServiceException("该订单已完成，不能取消");
        orderlist.setOrderstatus(0);
        orderlist.setRemark(remark);

        ServiceHelper.update(orderlistDao,Orderlist.class,orderlist);
    }


    //确认收货  改变 状态 商品销售量
    public void confirmOrder(Integer orderid) {
        Orderlist orderlist = ServiceHelper.get(orderlistDao, Orderlist.class, orderid);
        Integer orderstatus = orderlist.getOrderstatus();

        if (orderlist == null) {
            throw new ServiceException("该订单不存在");
        }
        if (orderstatus == 0)
            throw new ServiceException("该订单已取消不能确认收货");
        if (orderstatus == 2)
            throw new ServiceException("该订单已完成");
        orderlist.setOrderstatus(2);
        ServiceHelper.update(orderlistDao, Orderlist.class, orderlist);
        List<Orderdetail> orderdetails = orderdetailService.showOrder(orderid);
        for (int i = 0; i < orderdetails.size(); i++) {
            Orderdetail orderdetail = orderdetails.get(i);
            String name = orderdetail.getCommodityname();
            Commodity commodity = commodityService.getFoodByRightname(name);
            commodity.setSales(commodity.getSales() + orderdetail.getCommodityquantity());

            ServiceHelper.update(commodityDao, Commodity.class, commodity);
        }

    }
}
