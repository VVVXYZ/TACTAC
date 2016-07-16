package com.trio.vmalogo.service.impl;

import com.trio.vmalogo.dao.BlackDao;
import com.trio.vmalogo.model.Black;
import com.trio.vmalogo.pageModel.FilterGroup;
import com.trio.vmalogo.pageModel.FilterRule;
import com.trio.vmalogo.service.BlackService;
import com.trio.vmalogo.sys.exception.ServiceException;
import com.trio.vmalogo.util.HqlUtil;
import com.trio.vmalogo.util.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author loser
 * @ClassName BlackServiceImpl
 * @Description
 * @Date 2016/07/03 1:29
 */
@Service
public class BlackServiceImpl implements BlackService {
    @Autowired
    private BlackDao blackDao;

    @Override
    public void put(String code) {
        if (get(code) != null) {
            throw new ServiceException("黑名单中已存在" + code);
        }

        Black black = new Black();
        black.setCode(code);
        ServiceHelper.create(blackDao, Black.class, black);

    }

    @Override
    public Black get(String code) {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("code", HqlUtil.EQUAL, code));
        return ServiceHelper.get(blackDao, Black.class, filterGroup);
    }

    @Override
    public void search(String code) {
        if (get(code) == null) {
            throw new ServiceException("黑名单中未找到" + code);
        }
    }

    @Override
    public void remove(String code) {
        Black black = get(code);
        if (black == null) {
            throw new ServiceException("黑名单中未找到" + code);
        }

        black = new Black();
        black.setCode(code);
        ServiceHelper.delete(blackDao, Black.class, black);
    }
}
