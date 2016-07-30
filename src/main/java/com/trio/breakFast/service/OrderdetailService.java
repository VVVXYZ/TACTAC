package com.trio.breakFast.service;

import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;

import java.util.List;

/**
 * Created by asus on 2016/7/26.
 */
public interface OrderdetailService {
    public List<Orderdetail> showOrder(Orderlist orderid);
    public void addorderDetail(Orderlist orderid,String commodityname,Integer commodityquantity);
}
