package com.trio.breakFast.service;

import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2016/7/26.
 */
public interface OrderlistService {

    public Integer shopingCar(String username, Double amount, String datetime, String deliverymethod,
                              String paymentmethod, Integer orderstatus, String remark, String adress);

    public void cancelOrder(Integer orderid, String remark);
    public Orderlist getOrderlistByOrderid(Integer orderid);

    public List<Orderlist> getOrderlistByUsername(String username, Integer page, Integer rows);

    public List<Orderdetail> getDetailByList(String username, Integer page, Integer rows);

    public void confirmOrder(Integer orderid);

}
