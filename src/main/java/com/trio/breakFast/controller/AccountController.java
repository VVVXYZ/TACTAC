package com.trio.breakFast.controller;

import com.alibaba.fastjson.JSON;
import com.trio.breakFast.pageModel.FilterGroup;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.sys.entity.Account;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.sys.service.AccountService;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author loser
 * @ClassName AccountController
 * @Description
 * @Date 2016/07/03 14:40
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
    private Logger logger = Logger.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    @RequiresRoles("manage")
    @RequestMapping
    public String index(Model model){
        model.addAttribute("navMenu", "account");
        return "/jsp/account/index.jsp";
    }

    @RequiresRoles("manage")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(String filters, Integer page, Integer rows,String sort, String order){
         return  JSON.toJSONString(accountService.findByPage(FilterGroup.newFilterGroup(filters), page, rows, sort, order));
    }

    @RequiresRoles("manage")
    @ResponseBody
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public MessageHelper resetPassword(Integer accountId, String newPassword){
        MessageHelper messageHelper = new MessageHelper();
        try{
            Account account = accountService.get(accountId);
            accountService.changePassword(account, newPassword);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("修改成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return  messageHelper;
    }

    @RequiresRoles("manage")
    @ResponseBody
    @RequestMapping(value = "/lock", method = RequestMethod.POST)
    public MessageHelper lock(Integer accountId){
        MessageHelper messageHelper = new MessageHelper();
        try{
            Account account = accountService.get(accountId);
            accountService.lock(account);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("锁定成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }

    @RequiresRoles("manage")
    @ResponseBody
    @RequestMapping(value = "/unLock", method = RequestMethod.POST)
    public MessageHelper unLock(Integer accountId){
        MessageHelper messageHelper = new MessageHelper();
        try{
            Account account = accountService.get(accountId);
            accountService.unLock(account);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("解锁成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }

    @RequiresRoles("manage")
    @ResponseBody
    @RequestMapping(value = "/createSaleman", method = RequestMethod.POST)
    public MessageHelper createSaleman(Account account){
        MessageHelper messageHelper = new MessageHelper();
        try{
            accountService.createSaleman(account);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("新增业务员成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }

    @RequiresRoles("manage")
    @ResponseBody
    @RequestMapping(value = "/createDesigner", method = RequestMethod.POST)
    public MessageHelper createDesigner(Account account){
        MessageHelper messageHelper = new MessageHelper();
        try{
            accountService.createDesigner(account);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("新增设计师成功");
        }catch (ServiceException e){
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }
}
