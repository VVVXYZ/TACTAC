package com.trio.vmalogo.enums;

/**
 * Created by passerby on 2016/7/2.
 */
public enum Role {
    saleman("业务员"),designer("设计师"),manage("管理员");

    private String name;
    private Role(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
