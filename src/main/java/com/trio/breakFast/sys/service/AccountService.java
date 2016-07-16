package com.trio.breakFast.sys.service;

import com.trio.breakFast.pageModel.*;
import com.trio.breakFast.sys.entity.Account;

/**
 * @author loser
 * @ClassName SmUserInfoService
 * @Description
 * @Date 2016/03/11 14:54
 * @History :
 * <author>
 * <time>
 * <desc>
 */

public interface AccountService {

    int createSaleman(Account account);
    int createDesigner(Account account);
    void changePassword(Account account, String newPassword);
    void changePassword(Account account, String password, String newPassword);
    void checkPassword(Account account, String newPassword);
    Account get(Integer accountId);
    Account getDesignerByUsername(String username);
    Account getSalemanByUsername(String username);
    Account getByUsername(String username);
    void renewLoginTime(Account account);
    void lock(Account account);
    void unLock(Account account);
    long count(FilterGroup filterGroup);
    PageHelper<Account> findByPage(FilterGroup filterGroup,int page, int rows, String sort, String order);
    ListHelper<DesignerVO> findDesigner();
}
