package com.trio.breakFast.service;

import com.trio.breakFast.model.Usermessage;

import java.util.List;

/**
 * Created by asus on 2016/7/26.
 */

public interface UsermessageService {

    public Usermessage getUsermessage(Integer userid, Integer orderid);

    public List<Usermessage> getUsermessageList(String username, Integer page, Integer rows, Integer type);


}
