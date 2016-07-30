package com.trio.breakFast.controller;

import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.User;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.CommodityService;
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

    //搜索早餐接口
    @ResponseBody
    @RequestMapping(value = "/searchFood", method = RequestMethod.POST)
    public DataHelper userRegister(String foodname) {
        DataHelper dataHelper = new DataHelper();
        try{
              List<Commodity> commodities= commodityService.getFood(foodname);
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
            messageHelper.setMessage("找到早餐");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }

    //购物车接口
    @ResponseBody
    @RequestMapping(value = "/shopingCar", method = RequestMethod.POST)
    public MessageHelper shopingCar(User userid,Integer amount,Date datetime,String  deliverymethod,
                                    String paymentmethod,Integer orderstatus,String remark,Orderdetail orderdetail[]){
        MessageHelper messageHelper = new MessageHelper();
        try{
            orderlistService.shopingCar( userid, amount, datetime,  deliverymethod,
                     paymentmethod, orderstatus, remark, orderdetail);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("订单提交成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }

    //商品列表，根据销量返回商品
//    @ResponseBody
//    @RequestMapping(value = "/shopingCar", method = RequestMethod.POST)
//    public DataHelper userRegister(String foodname) {
//        DataHelper dataHelper = new DataHelper();
//        try{
//            List<Commodity> commodities= commodityService.getFood(foodname);
//            dataHelper.setData(commodities);
//            dataHelper.setSuccess(true);
//            dataHelper.setMessage("找到早餐");
//        }catch (ServiceException e){
//            dataHelper.setSuccess(false);
//            dataHelper.setMessage(e.getMessage());
//        }
//        return dataHelper;
//    }


    //商品列表，根据价格返回商品

    //商品列表，根据包子返回商品

}