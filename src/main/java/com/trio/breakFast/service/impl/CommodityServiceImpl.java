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

    String str;


    //根据准确的商品名返回商品信息，商品名是唯一的
    @Override
    public Commodity getFoodByRightname(String commodityname)
    {
        String hql="from Commodity c where c.commodityname=:commodityname";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("commodityname",commodityname);
        Commodity commodity=commodityDao.get(hql,params);
        if (commodity == null) {
            throw new ServiceException("没有该商品");
        }

        return commodity;
    }


    //搜索早餐，
    @Override
    public List<Commodity> getFood(String commodityname,Integer page,Integer rows)
    {
        if(commodityname == null || commodityname.length()<=0)
            throw new ServiceException("商品名不能为空");
        if(rows <= 0)
            throw new ServiceException("条数应大于0");
        if(page <= 0)
            throw new ServiceException("页数应大于0");

        String hql="from Commodity c where c.commodityname like :commodityname";
        Map<String,Object> params=new HashMap<String,Object>();
        String foodname="%"+commodityname+"%";
        params.put("commodityname",foodname);

        List<Commodity> commodities=commodityDao.find(hql,params,page,rows);

        if(commodities.size()==0){
            throw new ServiceException("未搜索到结果" );
        }

        return commodities;
    }

    //点赞
    @Override
    public void addNumberofpoints(Integer commodity_id)
    {
        if(commodity_id == null || commodity_id <= 0)
            throw new ServiceException("商品编号有误");

        Commodity commodity= ServiceHelper.get(commodityDao,Commodity.class,commodity_id);

        if(commodity==null){
            throw new ServiceException("该商品不存在，点赞失败" );
        }

        commodity.setNumberofpoints(commodity.getNumberofpoints()+1);
        ServiceHelper.update(commodityDao,Commodity.class,commodity);

    }

    //根据价格商品列表
    @Override
    public List<Commodity> getCommodityByCommodityamount(Integer page,Integer rows)
    {
        if(rows <= 0)
            throw new ServiceException("条数应大于0");
        if(page <= 0)
            throw new ServiceException("页数应大于0");

        String hql="from Commodity c order by c.commodityamount asc";
        List<Commodity> commodities=commodityDao.find(hql,page,rows);
        if(commodities.size()==0)
            throw new ServiceException("根据价格返回商品列表失败" );
        return commodities;
    }

    //根据销量商品列表
    @Override
    public List<Commodity> getCommodityBySales(Integer page,Integer rows)
    {
        if(rows <= 0)
            throw new ServiceException("条数应大于0");
        if(page <= 0)
            throw new ServiceException("页数应大于0");

        String hql="from Commodity c order by c.sales desc";
        List<Commodity> commodities=commodityDao.find(hql,page,rows);
        if(commodities.size()==0)
            throw new ServiceException("根据销量返回商品列表失败" );
        return commodities;
    }



    //根据包子商品列表
    @Override
    public List<Commodity> getCommodityByBaozi(Integer page,Integer rows)
    {
        if(rows <= 0)
            throw new ServiceException("条数应大于0");
        if(page <= 0)
            throw new ServiceException("页数应大于0");

        String commodityname="包";
        String food="'%"+commodityname+"%'";

        String hql="from Commodity c where c.commodityname like  "+food;
        List<Commodity> commodities=commodityDao.find(hql,page,rows);

        System.out.println("有返回");
        if(commodities.size()==0)
            throw new ServiceException("根据包子返回商品列表失败" );
        return commodities;
    }


}

