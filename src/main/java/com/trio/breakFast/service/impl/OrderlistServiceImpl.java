package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.OrderdetailDao;
import com.trio.breakFast.dao.OrderlistDao;
import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.model.User;
import com.trio.breakFast.service.OrderlistService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asus on 2016/7/26.
 */
@Service
public class OrderlistServiceImpl implements OrderlistService {


    OrderlistDao orderlistDao;
    OrderdetailDao orderdetailDao;
    //购物车   ****千万不要设置id ，id是自增长的
    @Override
    public Integer shopingCar(User userid,Integer amount,Date datetime,String  deliverymethod,
                              String paymentmethod,Integer orderstatus,String remark,Orderdetail orderdetail[])
    {

        Integer orderid=0;
        Orderlist orderlist=new Orderlist();
        orderlist.setUserid(userid);
        orderlist.setAmount(amount);
        orderlist.setDatetime(datetime);
        orderlist.setDistributionmethod(deliverymethod);
        orderlist.setPaymentmethod(paymentmethod);
        orderlist.setOrderstatus(orderstatus);
        orderlist.setRemark(remark);

        Serializable flag=orderlistDao.save(orderlist);
        if(flag==null){
            throw new ServiceException("下单失败" );
        }
        //  ServiceHelper.create(orderlistDao,Orderlist.class,orderlist);



        for(int i=0;i<orderdetail.length;i++)
        {
            Orderdetail temp=orderdetail[i];
            ServiceHelper.create(orderdetailDao, Orderdetail.class, temp);
        }

        orderid=(Integer)flag;
        return orderid;

    }
}
