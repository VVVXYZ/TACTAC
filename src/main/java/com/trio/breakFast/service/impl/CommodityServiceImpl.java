package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.CommodityDao;
import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.service.CommodityService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by asus on 2016/7/26.
 */
@Service
public class CommodityServiceImpl implements CommodityService {


    @Autowired
    CommodityDao commodityDao;


    //搜索早餐，
    @Override
    public Commodity getFood(Integer commid)
    {
//        String commodityid=commid+"";
//        String hql="from Commodity c where c.commodityid=:commid";
//        Map<String,Object> params=new HashMap<String,Object>();
//        params.put("commodityid",commid);
//        commodity=commodityDao.get(hql,params);


        Commodity commodity= ServiceHelper.get(commodityDao,Commodity.class,commid);

        if(commodity==null){
            throw new ServiceException("该商品不存在" );
        }

        return commodity;
    }

    //点赞
    @Override
    public void addNumberofpoints(Integer commodity_id)
    {
        Commodity commodity= ServiceHelper.get(commodityDao,Commodity.class,commodity_id);

        if(commodity==null){
            throw new ServiceException("该商品不存在" );
        }

        commodity.setNumberofpoints(commodity.getNumberofpoints()+1);
        ServiceHelper.update(commodityDao,Commodity.class,commodity);

    }



}

