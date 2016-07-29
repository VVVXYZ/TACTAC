package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.CommodityDao;
import com.trio.breakFast.dao.OrderdetailDao;
import com.trio.breakFast.dao.OrderlistDao;
import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.model.User;
import com.trio.breakFast.service.CommodityService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by asus on 2016/7/26.
 */
@Service
public class CommodityServiceImpl implements CommodityService {


    @Autowired
    CommodityDao commodityDao;
    OrderlistDao orderlistDao;
    OrderdetailDao orderdetailDao;
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

    //购物车   ****千万不要设置id ，id是自增长的
    @Override
    public void shopingCar(User userid,Integer amount,Date datetime,Integer orderstatus,Orderdetail orderdetail[])
    {

        Orderlist orderlist=new Orderlist();
        orderlist.setUserid(userid);
        orderlist.setAmount(amount);
        orderlist.setDatetime(datetime);
        orderlist.setOrderstatus(orderstatus);
        ServiceHelper.create(orderlistDao,Orderlist.class,orderlist);

        for(int i=0;i<orderdetail.length;i++)
        {
            Orderdetail temp=orderdetail[i];
            ServiceHelper.create(orderdetailDao,Orderdetail.class,temp);
        }



    }

}

