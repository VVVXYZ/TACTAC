package com.trio.breakFast.controller;

import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.model.User;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.CommodityService;
import com.trio.breakFast.service.OrderdetailService;
import com.trio.breakFast.service.OrderlistService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2016/7/29.
 */
@Controller
@RequestMapping("/homepage")
public class HomepageController extends BaseController {

    @Autowired
    private CommodityService commodityService;
    @Autowired
    private OrderlistService orderlistService;
    @Autowired
    private OrderdetailService orderdetailService;

    //搜索早餐接口
    @ResponseBody
    @RequestMapping(value = "/searchFood", method = RequestMethod.POST)
    public DataHelper userRegister(String foodname,Integer page,Integer rows) {
        DataHelper dataHelper = new DataHelper();
        try{
              List<Commodity> commodities= commodityService.getFood(foodname,page,rows);
            dataHelper.setData(commodities);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("找到早餐");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //点赞早餐接口
    @ResponseBody
    @RequestMapping(value = "/addNumberOfpoints", method = RequestMethod.POST)
    public MessageHelper addNumberofpoints(Integer commodity_id) {
        MessageHelper messageHelper = new MessageHelper();
        try{
            commodityService.addNumberofpoints(commodity_id);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("点赞成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }


    //订单完成后要改销售量

    //购物车接口
    @ResponseBody
    @RequestMapping(value = "/shopingCar", method = RequestMethod.POST)
    public DataHelper shopingCar(String username, Double amount, String datetime, String deliverymethod,
                                 String paymentmethod, Integer orderstatus, String remark, String adress) {
        DataHelper dataHelper =new DataHelper();
        try{
            Integer orderid = orderlistService.shopingCar(username, amount, datetime, deliverymethod,
                    paymentmethod, orderstatus, remark, adress);
            dataHelper.setData(orderid);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("订单提交成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //购物车订单明细接口，一条订单对应多条明细，要多次调用这个接口添加明细
    @ResponseBody
    @RequestMapping(value = "/addorderDetail", method = RequestMethod.POST)
    public MessageHelper addorderDetail(Integer orderid, String commodityname, Integer commodityquantity, Double price) {
        MessageHelper messageHelper = new MessageHelper();
        try{
            orderdetailService.addorderDetail(orderid, commodityname, commodityquantity, price);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("添加订单明细提交成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }

    //商品列表，根据销量返回商品
    @ResponseBody
    @RequestMapping(value = "/getCommodityBySales", method = RequestMethod.POST)
    public DataHelper getCommodityBySales(Integer page,Integer rows) {
        DataHelper dataHelper = new DataHelper();
        try{
            List<Commodity> commodities= commodityService.getCommodityBySales(page,rows);
            dataHelper.setData(commodities);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("根据销量返回商品列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }


    //商品列表，根据价格返回商品
    @ResponseBody
    @RequestMapping(value = "/getCommodityByCommodityamount", method = RequestMethod.POST)
    public DataHelper getCommodityByCommodityamount(Integer page,Integer rows) {
        DataHelper dataHelper = new DataHelper();
        try{
            List<Commodity> commodities= commodityService.getCommodityByCommodityamount(page, rows);
            dataHelper.setData(commodities);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("根据价格返回商品列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }
    //商品列表，根据包子返回商品
    @ResponseBody
    @RequestMapping(value = "/getCommodityByBaozi", method = RequestMethod.POST)
    public DataHelper getCommodityByBaozi(Integer page,Integer rows) {
        DataHelper dataHelper = new DataHelper();
        try{
            List<Commodity> commodities= commodityService.getCommodityByBaozi(page, rows);
            dataHelper.setData(commodities);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("根据包子返回商品列表成功");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

}