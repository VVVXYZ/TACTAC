package com.trio.breakFast.controller;

import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.pageModel.DataHelper;
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

}
