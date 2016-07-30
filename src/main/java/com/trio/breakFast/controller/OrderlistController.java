package com.trio.breakFast.controller;

import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.OrderdetailService;
import com.trio.breakFast.service.OrderlistService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by asus on 2016/7/30.
 */
@Controller
@RequestMapping("/orderlist")
public class OrderlistController extends BaseController{
    @Autowired
    private OrderlistService orderlistService;
    @Autowired
    private OrderdetailService orderdetailService;

    //订单接口
    @ResponseBody
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public DataHelper getOrdermessage(Orderlist orderid)
    {
        DataHelper dataHelper=new DataHelper();
        try{

            dataHelper.setSuccess(true);
            dataHelper.setMessage("查找到该订单信息");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //取消订单接口
    @ResponseBody
    @RequestMapping(value = "/cancelorder", method = RequestMethod.POST)
    public MessageHelper cancelOrder(Integer orderid,String remark,Integer orderstatus)
    {
        MessageHelper messageHelper=new MessageHelper();
        try{
            orderlistService.cancelOrder(orderid,remark,orderstatus);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("取消订单成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }


}
