package com.trio.breakFast.controller;

import com.trio.breakFast.model.Addressdetail;
import com.trio.breakFast.model.Usermessage;
import com.trio.breakFast.pageModel.DataHelper;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.AddressdetailService;
import com.trio.breakFast.service.UsermessageService;
import com.trio.breakFast.service.FTPffUpAndDownService;
import com.trio.breakFast.sys.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by ienovo on 2016/7/30.
 */

@Controller
@RequestMapping("/personal")
public class PersonalHomeController {

    @Autowired
    AddressdetailService addressdetailService;


    @Autowired
    UsermessageService usermessageService;

    @Autowired
    FTPffUpAndDownService FTPffUpAndDownService;

    //上传头像
    @ResponseBody
    @RequestMapping(value = "/upFile", method = RequestMethod.POST)
    public MessageHelper FileUp(String fileName, String picture) {
        MessageHelper messageHelper = new MessageHelper();
        try {
            FTPffUpAndDownService.FileUp(fileName, picture);
            System.out.println(fileName);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("头像上传成功");

        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());

        }

        return messageHelper;
    }

    //加载头像
    @ResponseBody
    @RequestMapping(value = "/downFile", method = RequestMethod.POST)
    public DataHelper FileDown(String fileName) {
        DataHelper dataHelper = new DataHelper();
        try {
            String picture = FTPffUpAndDownService.FileDown(fileName);
            dataHelper.setData(picture);
            dataHelper.setSuccess(true);

            dataHelper.setMessage("头像加载成功");

        } catch (ServiceException e) {
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());

        }
        return dataHelper;
    }

    //显示系统消息列表接口
    @ResponseBody
    @RequestMapping(value = "/getUsermessageList", method = RequestMethod.POST)
    public DataHelper getUsermessageList(String username, Integer page, Integer rows, Integer type)
    {
        DataHelper dataHelper=new DataHelper();
        try{
            List<Usermessage> usermessages = usermessageService.getUsermessageList(username, page, rows, type);
            dataHelper.setData(usermessages);
            dataHelper.setSuccess(true);

            dataHelper.setMessage("刷新系统信息成功");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());

        }
        return dataHelper;
    }

    //显示地址接口,得到某个人的收货地址集合
    @ResponseBody
    @RequestMapping(value = "/showAddress", method = RequestMethod.POST)
    public DataHelper showAddress(String username) {
        DataHelper dataHelper=new DataHelper();
        try{
            List<Addressdetail> addressdetails = addressdetailService.showAddress(username);
            dataHelper.setData(addressdetails);
            dataHelper.setSuccess(true);
            dataHelper.setMessage("查找到地址");

        }catch (ServiceException e){
            dataHelper.setSuccess(false);
            dataHelper.setMessage(e.getMessage());

        }
        return dataHelper;
    }


    //修改地址接口
    @ResponseBody
    @RequestMapping(value = "/changeAddress", method = RequestMethod.POST)
    public MessageHelper changeAddress(Integer addressid,String address,String receivername,String phone) {
        MessageHelper messageHelper=new MessageHelper();
        try{
            addressdetailService.changeAddress(addressid,address,receivername,phone);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("地址修改成功");

        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());

        }
        return messageHelper;
    }


    //删除地址接口
    @ResponseBody
    @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
    public MessageHelper deleteAddress(Integer addressid) {
        MessageHelper messageHelper=new MessageHelper();
        try{
            addressdetailService.deleteAddress(addressid);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("地址删除成功");

        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());

        }
        return messageHelper;
    }


    //新增收货地址
    @ResponseBody
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public MessageHelper addAddress(String username, String newAddress,String receivername,String phone) {
        MessageHelper messageHelper=new MessageHelper();
        try{
            addressdetailService.addAddress(username, newAddress,receivername,phone);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("添加新地址成功");

        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());

        }

        return messageHelper;
    }


}
