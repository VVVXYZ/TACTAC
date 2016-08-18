package com.trio.breakFast.controller;


import com.trio.breakFast.util.StringEscapeEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基础控制器
 * <p/>
 * 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能及其他基础方法
 *
 * @author ercha
 * @date 2015/11/21
 * @desc 修改 date 格式为 yy-MM-dd
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

        /**
         * 防止XSS攻击
         */

        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }

}
