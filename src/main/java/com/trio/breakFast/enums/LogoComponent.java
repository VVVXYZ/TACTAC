package com.trio.breakFast.enums;

/**
 * Created by passerby on 2016/7/2.
 */
public enum LogoComponent {
    chinese("A","英文字体设计"),english("B","中文字体组成"),picture("C","图案+标准字");
    private String name;

    public String getChoice() {
        return choice;
    }

    private String choice;
    private LogoComponent(String choice, String name) {
        this.name = name;
        this.choice = choice;
    }

    public String getName() {
        return name;
    }
}
