package com.trio.breakFast.sys.filter;

import com.trio.breakFast.Constants;
import com.trio.breakFast.sys.entity.Account;
import com.trio.breakFast.sys.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CurrentUserFilter extends PathMatchingFilter {

    @Autowired
    private AccountService accountService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            Account account = accountService.getByUsername((String) subject.getPrincipal());
            request.setAttribute(Constants.CURRENT_USER, account);
        }
        return true;
    }
}
