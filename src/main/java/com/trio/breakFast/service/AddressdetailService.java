package com.trio.breakFast.service;

import com.trio.breakFast.model.Addressdetail;
import com.trio.breakFast.model.User;

import java.util.List;

/**
 * Created by asus on 2016/7/26.
 */

public interface AddressdetailService {
//    void put(String code);
//    void search(String code);
//    void remove(String code);

    public List<Addressdetail> showAddress(String username);

    public void changeAddress(String username, String address_content, String newaddress);

    public void deleteAddress(String username, String address_content);

    public void addAddress(String username, String newAddress);

}