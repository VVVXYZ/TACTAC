package com.trio.breakFast.sys.service.impl;

import com.trio.breakFast.Constants;
import com.trio.breakFast.enums.Role;
import com.trio.breakFast.pageModel.*;
import com.trio.breakFast.sys.dao.AccountDao;
import com.trio.breakFast.sys.entity.Account;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.sys.service.AccountService;
import com.trio.breakFast.util.HqlUtil;
import com.trio.breakFast.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author loser
 * @ClassName SmUserInfoServiceImpl
 * @Description
 * @Date 2016/03/11 14:59
 * @History :
 * <author>
 * <time>
 * <desc>
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private EncryptHelper encryptHelper;

    public void usernameCheck(String username) {

        if (username.length() < Constants.USERNAME_MIN_LENGTH) {
            throw new ServiceException( "用户名长度不能少于" + Constants.USERNAME_MIN_LENGTH);
        } else if (username.length() > Constants.USERNAME_MAX_LENGTH) {
            throw new ServiceException( "用户名长度不能多于" + Constants.USERNAME_MAX_LENGTH);
        } else if (Pattern.matches(Constants.USERNAME_PATTERN, username) == false ){
            throw new ServiceException( "用户名不合法");
        } else if (getByUsername(username)!=null) {
            throw new ServiceException( "用户名已被使用");
        }
    }

    public void passwordCheck(String password) {
        System.out.print(password);
        if (password.length() < Constants.PASSWORD_MIN_LENGTH) {
            throw new ServiceException( "密码长度不能少于" + Constants.PASSWORD_MIN_LENGTH);
        } else if (password.length() > Constants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException( "密码长度不能多于" + Constants.PASSWORD_MAX_LENGTH);
        }else if (Pattern.matches(Constants.PASSWORD_PATTERN, password) ==false ){
            throw new ServiceException( "密码不合法");
        }
    }
    
    @Override
    public int createSaleman(Account account) {
        usernameCheck(account.getUsername());
        passwordCheck(account.getPassword());
        account.setRole(Role.saleman);
        account.setSalt(encryptHelper.getSalt());
        account.setPassword(encryptHelper.md5Hash(account.getPassword(), account.getUsername() + account.getSalt()));
        return ServiceHelper.create(accountDao, Account.class, account);
    }

    @Override
    public int createDesigner(Account account) {
        usernameCheck(account.getUsername());
        passwordCheck(account.getPassword());
        account.setRole(Role.designer);
        account.setSalt(encryptHelper.getSalt());
        account.setPassword(encryptHelper.md5Hash(account.getPassword(), account.getUsername() + account.getSalt()));
        return ServiceHelper.create(accountDao, Account.class, account);
    }

    @Override
    public void changePassword(Account account, String newPassword) {
        passwordCheck(newPassword);
        account.setSalt(encryptHelper.getSalt());
        account.setPassword(encryptHelper.md5Hash(newPassword, account.getUsername() + account.getSalt()));
        ServiceHelper.update(accountDao, Account.class, account);
    }

    @Override
    public void changePassword(Account account, String password, String newPassword) {
        checkPassword(account,password);
        changePassword(account,newPassword);
    }

    @Override
    public void checkPassword(Account account, String newPassword) {
        if(!account.getPassword().equals(encryptHelper.md5Hash(newPassword, account.getUsername() + account.getSalt()))){
            throw new ServiceException("密码错误");
        }
    }

    @Override
    public Account get(Integer accountId) {
       return  ServiceHelper.get(accountDao, Account.class, accountId);
    }

    @Override
    public Account getDesignerByUsername(String username) {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("username", HqlUtil.EQUAL, username));
        filterGroup.addRules(new FilterRule("role", HqlUtil.EQUAL, Role.designer));
        return ServiceHelper.get(accountDao, Account.class, filterGroup);
    }

    @Override
    public Account getSalemanByUsername(String username) {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("username", HqlUtil.EQUAL, username));
        filterGroup.addRules(new FilterRule("role", HqlUtil.EQUAL, Role.saleman));
        return ServiceHelper.get(accountDao, Account.class, filterGroup);
    }

    @Override
    public Account getByUsername(String username) {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("username", HqlUtil.EQUAL, username));
        return ServiceHelper.get(accountDao, Account.class, filterGroup);
    }

    @Override
    public void renewLoginTime(Account account) {
        account.setLoginTime(Calendar.getInstance().getTime());
        ServiceHelper.update(accountDao, Account.class, account);
    }

    @Override
    public void lock(Account account) {
        if(account.getRole() == Role.manage){
            throw new ServiceException("管理员帐号不能锁定!");
        }
        account.setLocked(Boolean.TRUE);
        ServiceHelper.update(accountDao, Account.class, account);
    }

    @Override
    public void unLock(Account account) {
        account.setLocked(Boolean.FALSE);
        ServiceHelper.update(accountDao, Account.class, account);
    }

    @Override
    public long count(FilterGroup filterGroup) {
        return ServiceHelper.count(accountDao, Account.class, filterGroup);
    }

    @Override
    public PageHelper<Account> findByPage(FilterGroup filterGroup, int page, int rows, String sort, String order) {
        return ServiceHelper.findByPage(accountDao, Account.class, filterGroup, page, rows, sort, order);
    }

    @Override
    public ListHelper<DesignerVO> findDesigner() {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("role", HqlUtil.EQUAL, Role.designer));
        ListHelper<DesignerVO> designerVOListHelper = new ListHelper<DesignerVO>();
        List<String> propertys = new ArrayList<String>();
        propertys.add("accountId");
        propertys.add("name");
        designerVOListHelper.setDataList(ServiceHelper.findVO(accountDao,Account.class, DesignerVO.class, propertys,filterGroup));
        designerVOListHelper.setSuccess(true);
        designerVOListHelper.setMessage("获取设计师名单成功");
        return designerVOListHelper;
    }

}
