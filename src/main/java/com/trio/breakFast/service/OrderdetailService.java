package com.trio.breakFast.service;

import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;

import java.util.List;

/**
 * Created by asus on 2016/7/26.
 */
public interface OrderdetailService {
    public List<Orderdetail> showOrder(Integer orderid);

    public void addorderDetail(Integer orderid, String commodityname, Integer commodityquantity, Double price);


    public List<Orderdetail> getOrderdetailOn(Integer orderid, String type);
}

