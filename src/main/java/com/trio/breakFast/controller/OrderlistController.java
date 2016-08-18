package com.trio.breakFast.controller;

import com.trio.breakFast.model.Commodity;
import com.trio.breakFast.model.Orderdetail;
import com.trio.breakFast.model.Orderlist;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.pageModel.MyDataHelper;
import com.trio.breakFast.pageModel.NMyDataHelper;
import com.trio.breakFast.service.CommodityService;
import com.trio.breakFast.service.OrderdetailService;
import com.trio.breakFast.service.OrderlistService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    private CommodityService commodityService;

    String str;

    //取消订单接口
    @ResponseBody
    @RequestMapping(value = "/cancelorder", method = RequestMethod.POST)
    public MessageHelper cancelOrder(Integer orderid, String remark)
    {
        MessageHelper messageHelper=new MessageHelper();
        System.out.println("取消订单。。。");

        try{
            orderlistService.cancelOrder(orderid, remark);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("取消订单成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }

    //根据orderid返回订单记录
    @ResponseBody
    @RequestMapping(value = "/getOrderlistByOrderid", method = RequestMethod.POST)
    public DataHelper getOrderlistByOrderid(Integer orderid)
    {
        DataHelper dataHelper=new DataHelper();
        try{
            Orderlist orderlist=orderlistService.getOrderlistByOrderid(orderid);
            dataHelper.setData(orderlist);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("查找到该订单信息");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //根据orderid在订单明细表中返回订单明细记录 列表
    @ResponseBody
    @RequestMapping(value = "/showOrder", method = RequestMethod.POST)
    public DataHelper showOrder(Integer orderid)
    {
        DataHelper dataHelper=new DataHelper();
        try{
            List<Orderdetail> orderdetails=orderdetailService.showOrder(orderid);
            dataHelper.setData(orderdetails);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("查找到该订单的订单明细信息");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //根据商品名返回商品，商品名唯一
    @ResponseBody
    @RequestMapping(value = "/getFoodByRightname", method = RequestMethod.POST)
    public DataHelper getFoodByRightname(String commodityname)
    {
        DataHelper dataHelper=new DataHelper();
        try{
            Commodity commodity=commodityService.getFoodByRightname(commodityname);
            dataHelper.setData(commodity);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("查找到该商品信息");
        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());
        }
        return dataHelper;
    }

    //根据用户名返回进行中订单
    @ResponseBody
    @RequestMapping(value = "/getOrderAndDetail", method = RequestMethod.POST)
    public NMyDataHelper getOrderAndDetail(String username, Integer page, Integer rows, String type) {
        NMyDataHelper myDataHelper = new NMyDataHelper();

        try {
            List<Orderlist> orderlists = orderlistService.getOrderlistByUsername(username, page, rows, type);
            List<Orderdetail> orderdetails = orderlistService.getDetailByList(username, page, rows, type);
            int i = orderlistService.getOrderlistNumByUsername(username, type);
            myDataHelper.setData(orderlists);
            myDataHelper.setMydata(orderdetails);
            myDataHelper.setNmydata(i);
            myDataHelper.setSuccess(true);
            myDataHelper.setMessage("查找到该商品信息");
        } catch (ServiceException e) {
            myDataHelper.setSuccess(false);
            myDataHelper.setMessage(e.getMessage());
        }


        return myDataHelper;
    }


    //确认收货
    @ResponseBody
    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
    public MessageHelper confirmOrder(Integer orderid) {
        MessageHelper messageHelper = new MessageHelper();
        System.out.println("确认订单。。。");
        try {
            orderlistService.confirmOrder(orderid);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("确认订单成功");
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }
        return messageHelper;
    }

}
