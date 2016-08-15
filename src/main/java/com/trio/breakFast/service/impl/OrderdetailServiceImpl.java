package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.CommodityDao;
import com.trio.breakFast.dao.OrderdetailDao;
import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.service.CommodityService;
import com.trio.breakFast.service.OrderdetailService;
import com.trio.breakFast.service.OrderlistService;
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
public class OrderdetailServiceImpl implements OrderdetailService {
    @Autowired
    OrderdetailDao orderdetailDao;

    @Autowired
    OrderlistService orderlistService;

    @Autowired
    CommodityService commodityService;

    //显示某条订单的订单明细列表  详情
    @Override
    public List<Orderdetail> showOrder(Integer orderid)
    {
        String hql = "from Orderdetail o where o.order.orderid=:orderid";
        Map<String,Object> params=new HashMap<String,Object>();

        params.put("orderid", orderid);
        List<Orderdetail> orderdetails=orderdetailDao.find(hql,params);

        if(orderdetails.size()==0){
            throw new ServiceException("未找到该用户的订单明细" );
        }
        return orderdetails;
    }

    //添加订单明细
    @Override
    public void addorderDetail(Integer orderid, String commodityname, Integer commodityquantity, Double price) {

        Orderlist orderlist = new Orderlist();
        orderlist = orderlistService.getOrderlistByOrderid(orderid);
        Orderdetail orderdetail=new Orderdetail();

        orderdetail.setOrder(orderlist);
        orderdetail.setCommodityname(commodityname);
        orderdetail.setCommodityquantity(commodityquantity);
        orderdetail.setPrice(price);
        Commodity commodity = commodityService.getFoodByRightname(commodityname);
        String picturename = commodity.getCommoditypicture();
        System.out.println("**********" + picturename);
        orderdetail.setCommoditypicture(picturename);

        Integer flag=ServiceHelper.create(orderdetailDao, Orderdetail.class, orderdetail);
        if(flag==-1)
            throw new ServiceException("添加订单明细失败");


    }

    //根据订单编号查询进行中的订单明细  订单状态
    @Override
    public List<Orderdetail> getOrderdetailOn(Integer orderid, String type) {

        Integer status = Integer.parseInt(type);
        String hql = "from Orderdetail o where o.order.orderid=:orderid and o.order.orderstatus=:status";
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("orderid", orderid);
        params.put("status", status);
        List<Orderdetail> orderdetails = orderdetailDao.find(hql, params);

        return orderdetails;

    }


}
