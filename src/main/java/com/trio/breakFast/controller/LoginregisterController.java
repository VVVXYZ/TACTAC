package com.trio.breakFast.controller;

import com.trio.breakFast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by asus on 2016/7/27.
 */

@Controller
@RequestMapping("/user")
public class LoginregisterController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public boolean userLogin(String name, String psw) {
        boolean result;
        try {
            result = userService.judgeLogin(name, psw);
        } catch (Exception e) {
            return false;
        }
        return result;
    }
}
