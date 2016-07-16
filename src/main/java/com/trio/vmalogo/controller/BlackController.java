package com.trio.vmalogo.controller;

import com.trio.vmalogo.pageModel.MessageHelper;
import com.trio.vmalogo.service.BlackService;
import com.trio.vmalogo.sys.exception.ServiceException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author loser
 * @ClassName BlackController
 * @Description
 * @Date 2016/07/03 14:40
 */
@Controller
@RequestMapping("/black")
public class BlackController extends BaseController {

    @Autowired
    private BlackService blackService;
    @RequiresRoles("manage")
    @RequestMapping
    public String index(Model model){
        model.addAttribute("navMenu", "black");
        return "/jsp/black/index.jsp";
    }
    @RequiresRoles("manage")
    @ResponseBody
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public MessageHelper put(String code){
        MessageHelper messageHelper = new MessageHelper();
        try{
            blackService.put(code);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("移入成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }
    @RequiresRoles("manage")
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public MessageHelper remove(String code){
        MessageHelper messageHelper = new MessageHelper();
        try{
            blackService.remove(code);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("移出成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }
    @RequiresRoles("manage")
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public MessageHelper search(String code){
        MessageHelper messageHelper = new MessageHelper();
        try{
            blackService.search(code);
            messageHelper.setSuccess(true);
            messageHelper.setMessage(code+"在黑名单中");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }
}
