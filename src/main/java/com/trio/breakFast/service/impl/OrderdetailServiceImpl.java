package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.OrderdetailDao;
import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.service.OrderdetailService;
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

    //显示某条订单详情
    @Override
    public List<Orderdetail> showOrder(Orderlist orderid)
    {
        String hql="from Orderdetail o where o,orderid=orderid";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("orderid",orderid);
        List<Orderdetail> orderdetails=orderdetailDao.find(hql,params);

        if(orderdetails.size()==0){
            throw new ServiceException("未找到该用户的订单详情" );
        }
        return orderdetails;
    }

    //添加订单明细
    @Override
    public void addorderDetail(Orderlist orderid,String commodityname,Integer commodityquantity){
        Orderdetail orderdetail=new Orderdetail();
        orderdetail.setOrderid(orderid);
        orderdetail.setCommodityname(commodityname);
        orderdetail.setCommodityquantity(commodityquantity);
        Integer flag=ServiceHelper.create(orderdetailDao, Orderdetail.class, orderdetail);
        if(flag==-1)
            throw new ServiceException("添加订单明细失败");


    }


}
