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

    public List<Addressdetail> showAddress(User user_id);
    public  void changeAddress(Integer userid,String address_content,String newaddress);
    public  void deleteAddress(Integer userid,String address_content);
    public void  addAddress(User userid,String newAddress);

}