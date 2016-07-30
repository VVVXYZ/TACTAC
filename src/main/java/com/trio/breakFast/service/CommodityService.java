package com.trio.breakFast.service;

import com.trio.breakFast.model.Commodity;

/**
 * Created by asus on 2016/7/26.
 */
public interface CommodityService {

    public Commodity getFood(Integer commid);
    public void addNumberofpoints(Integer commodity_id);

}
