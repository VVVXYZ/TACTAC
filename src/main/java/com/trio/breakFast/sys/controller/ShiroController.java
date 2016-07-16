package com.trio.breakFast.sys.controller;

import com.trio.breakFast.controller.BaseController;
import com.trio.breakFast.pageModel.MessageHelper;
import com.trio.breakFast.service.FileUploadService;
import com.trio.breakFast.sys.bind.annotation.CurrentUser;
import com.trio.breakFast.sys.entity.Account;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.sys.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author loser
 * @ClassName ShiroController
 * @Description
 * @Date 2016/07/02 20:50
 */
@Controller
@RequestMapping
public class ShiroController extends BaseController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping
    public String index() {
        return "/jsp/common/index.jsp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/jsp/common/loginAndRegister.jsp";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword() {
        return "/jsp/common/changePassword.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public MessageHelper changePassword(@CurrentUser Account account, String password, String newPassword) {
        MessageHelper messageHelper = new MessageHelper();
        try {
            accountService.changePassword(account, password, newPassword);
            messageHelper.setSuccess(true);
            messageHelper.setMessage("修改成功");
        } catch (ServiceException e) {
            messageHelper.setSuccess(false);
            messageHelper.setMessage(e.getMessage());
        }

        return messageHelper;
    }

}
