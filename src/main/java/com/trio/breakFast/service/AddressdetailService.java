package com.trio.breakFast.service;

import com.trio.breakFast.model.Addressdetail;

import java.util.List;

/**
 * Created by asus on 2016/7/26.
 */

public interface AddressdetailService {
//    void put(String code);
//    void search(String code);
//    void remove(String code);

    public List<Addressdetail> showAddress(String username);

    public void changeAddress(Integer addressid, String address,String receivername,String phone);
//    public void changeAddress(String username, String address_content, String newaddress,String receivername,String phone);

    public void deleteAddress(Integer addressid);

    public void addAddress(String username, String newAddress,String receivername,String phone);

}