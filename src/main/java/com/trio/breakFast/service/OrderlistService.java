package com.trio.breakFast.service;

import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;

import java.util.List;

/**
 * Created by asus on 2016/7/26.
 */
public interface OrderlistService {

    public Integer shopingCar(String username, Double amount, String datetime, String deliverymethod,
                              String paymentmethod, Integer orderstatus, String remark, String adress,
                              String receivername,String phone);

    public void cancelOrder(Integer orderid, String remark);

    public Orderlist getOrderlistByOrderid(Integer orderid);

    public List<Orderlist> getOrderlistByUsername(String username, Integer page, Integer rows, String type);

    public List<Orderdetail> getDetailByList(String username, Integer page, Integer rows, String type);

    public void confirmOrder(Integer orderid);

}
