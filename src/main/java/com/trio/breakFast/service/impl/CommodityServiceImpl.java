package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.CommodityDao;
import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.service.CommodityService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by asus on 2016/7/26.
 */
@Service
public class CommodityServiceImpl implements CommodityService {


    @Autowired
    CommodityDao commodityDao;


    //搜索早餐，
    @Override
    public List<Commodity> getFood(String commodityname)
    {

        String hql="from Commodity c where c.commodityname like :commodityname";
        Map<String,Object> params=new HashMap<String,Object>();
        String foodname="%"+commodityname+"%";
        params.put("commodityname",foodname);

        List<Commodity> commodities=commodityDao.find(hql,params);



        if(commodities.size()==0){
            throw new ServiceException("未搜索到结果" );
        }

        return commodities;
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

    //根据价格商品列表
    @Override
    public List<Commodity> getCommodityByCommodityamount(Integer page,Integer rows)
    {
        String hql="from Commodity c order by c.commodityamount ";
        return commodityDao.find(hql,page,rows);
    }

    //根据销量商品列表
    @Override
    public List<Commodity> getCommodityBySales(Integer page,Integer rows)
    {
        String hql="from Commodity c order by c.sales";
        return commodityDao.find(hql,page,rows);
    }

    //根据包子商品列表
    @Override
    public List<Commodity> getCommodityByBaozi(Integer page,Integer rows)
    {
        String hql="from Commodity c where c.commodityname like %包子% ";
        return commodityDao.find(hql,page,rows);
    }


}

