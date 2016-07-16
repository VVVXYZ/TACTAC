package com.trio.breakFast.enums;

/**
 * Created by passerby on 2016/7/2.
 */
public enum OrderStatus {
    unpaid("未付款"),paid("已付款"),designing("设计中"),designed("设计完成"),finished("定稿"),cancel("退单");
    private String name;
    private OrderStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
