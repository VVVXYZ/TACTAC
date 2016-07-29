package com.trio.breakFast.controller;

import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.User;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.CommodityService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by asus on 2016/7/29.
 */
@Controller
@RequestMapping("/homepage")
public class HomepageController extends BaseController {

    @Autowired
    private CommodityService commodityService;

    //搜索早餐接口
    @ResponseBody
    @RequestMapping(value = "/searchFood", method = RequestMethod.POST)
    public DataHelper userRegister(Integer commodity_id) {
        DataHelper dataHelper = new DataHelper();
        try{
           Commodity commodity= commodityService.getFood(commodity_id);
            dataHelper.setData(commodity);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("找到早餐");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //搜索早餐接口
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
    public MessageHelper shopingCar(User userid,Integer amount,Date datetime,Integer orderstatus,Orderdetail orderdetail[]){
        MessageHelper messageHelper = new MessageHelper();
        try{
            commodityService.shopingCar(userid, amount, datetime, orderstatus, orderdetail);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("订单提交成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }


}