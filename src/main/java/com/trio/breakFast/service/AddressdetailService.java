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

    public List<Addressdetail> showAddress(Integer user_id);
    public  void changeAddress(Integer user_id,String address_content,String newaddress);
    public  void deleteAddress(Integer user_id,String address_content);
    public void  addAddress(User user_id,String newAddress);

}