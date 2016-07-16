package com.trio.vmalogo.sys.filter;

import com.alibaba.fastjson.JSON;
import com.trio.vmalogo.pageModel.MessageHelper;
import com.trio.vmalogo.sys.entity.Account;
import com.trio.vmalogo.sys.service.AccountService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    Logger logger = Logger.getLogger(FormAuthenticationFilter.class);
    @Autowired
    private AccountService accountService;

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        MessageHelper messageHelper = new MessageHelper();
        messageHelper.setSuccess(false);
        e.printStackTrace();
        if(e instanceof UnknownAccountException){
            messageHelper.setMessage("用户不存在");
        }else if(e instanceof IncorrectCredentialsException){
            messageHelper.setMessage("用户名或密码错误！");
        }else if(e instanceof LockedAccountException){
            messageHelper.setMessage("账号被锁定！");
        }else if(e instanceof ExcessiveAttemptsException){
            messageHelper.setMessage("登录失败次数过多！请5分钟后再试！！");
        }else{
            messageHelper.setMessage("登录失败，未知错误，请联系系统管理员！");
        }
        try {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(JSON.toJSONString(messageHelper));
        }catch (Exception ee){
            logger.error("登录失败写入流失败",ee);
        }
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        Account account = accountService.getByUsername((String) token.getPrincipal());
        accountService.renewLoginTime(account);
        MessageHelper messageHelper = new MessageHelper();
        messageHelper.setSuccess(true);
        messageHelper.setMessage("登录成功");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(JSON.toJSONString(messageHelper));
        return false;
    }
}
