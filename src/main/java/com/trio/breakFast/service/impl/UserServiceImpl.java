package com.trio.breakFast.service.impl;

import com.trio.breakFast.dao.UserDao;
import com.trio.breakFast.model.User;
import com.trio.breakFast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2016/7/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean judgeLogin(String name, String psw) {

        String hql = "from User u where u.username=:name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        User user = userDao.get(hql, params);
        if (user.getPassword().equals(psw)) {
            return true;
        } else {
            return false;
        }
    }
}
