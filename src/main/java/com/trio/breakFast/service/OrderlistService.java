package com.trio.breakFast.service;

import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.model.User;

import java.util.Date;

/**
 * Created by asus on 2016/7/26.
 */
public interface OrderlistService {

    public Integer shopingCar(User userid,Integer amount,Date datetime,String  deliverymethod,
                              String paymentmethod,Integer orderstatus,String remark);
    public void cancelOrder(Integer orderid,String remark,Integer orderstatus);
    public Orderlist getOrderlistByOrderid(Integer orderid);


}
