package com.trio.breakFast.service;

import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.User;

import java.util.Date;

/**
 * Created by asus on 2016/7/26.
 */
public interface CommodityService {

    public Commodity getFood(Integer commid);
    public void addNumberofpoints(Integer commodity_id);
    public void shopingCar(User userid,Integer amount,Date datetime,Integer orderstatus,Orderdetail orderdetail[]);
}
