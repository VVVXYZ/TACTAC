package com.trio.breakFast.enums;

/**
 * Created by passerby on 2016/7/2.
 */
public enum LogoColor {

    warm("A", "暖色为主"),
    cool("B", "冷色为主"),
    neutral("C", "中性色");

    private String name;
    private String choice;

    private LogoColor(String choice, String name) {
        this.name = name;
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }

    public String getName() {
        return name;
    }
}
