package com.trio.breakFast.service;

import com.trio.breakFast.model.Commodity;

import java.util.List;

/**
 * Created by asus on 2016/7/26.
 */
public interface CommodityService {

    public List<Commodity> getFood(String commodityname);
    public void addNumberofpoints(Integer commodity_id);
    public List<Commodity> getCommodityByCommodityamount(Integer page,Integer rows);
    public List<Commodity> getCommodityBySales(Integer page,Integer rows);
    public List<Commodity> getCommodityByBaozi(Integer page,Integer rows);

}
