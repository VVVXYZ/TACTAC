package com.trio.breakFast.enums;

/**
 * Created by passerby on 2016/7/2.
 */
public enum LogoStartpoint {

    brandName("A","从品牌名称出发"),
    industryCharacteristics("B","从行业特征性事物出发"),
    outstandingIdea("C","从突出理念出发"),
    others("D","其他");

    private String name;
    private String choice;
    private LogoStartpoint(String choice, String name) {
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
