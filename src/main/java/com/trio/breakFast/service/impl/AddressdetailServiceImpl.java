package com.trio.breakFast.service.impl;

import com.trio.breakFast.service.AddressdetailService;
import org.springframework.stereotype.Service;

/**
 * Created by asus on 2016/7/26.
 */

@Service
public class AddressdetailServiceImpl implements AddressdetailService {
//    @Autowired
//    private AddressdetailDao addressdetailDao;
//
//    @Override
//    public void put(String addressid) {
//        if (get(addressid) != null) {
//            throw new ServiceException("黑名单中已存在" + addressid);
//        }
//
//        Addressdetail addressiddetail = new Addressdetail();
//        addressiddetail.setAddressid(addressid);
//        ServiceHelper.create(AddressdetailDao, Addressdetail.class, addressiddetail)
//
//    }
//
//    @Override
//    public Black get(String code) {
//        FilterGroup filterGroup = new FilterGroup();
//        filterGroup.addRules(new FilterRule("code", HqlUtil.EQUAL, code));
//        return ServiceHelper.get(blackDao, Black.class, filterGroup);
//    }
//
//    @Override
//    public void search(String code) {
//        if (get(code) == null) {
//            throw new ServiceException("黑名单中未找到" + code);
//        }
//    }
//
//    @Override
//    public void remove(String code) {
//        Black black = get(code);
//        if (black == null) {
//            throw new ServiceException("黑名单中未找到" + code);
//        }
//
//        black = new Black();
//        black.setCode(code);
//        ServiceHelper.delete(blackDao, Black.class, black);
//  }
}
