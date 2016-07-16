package com.trio.vmalogo.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by passerby on 2016/7/2.
 */
public enum LogoStyle {
    professionalSteady("A","专业稳重"),
    fashionDynamic("B","时尚动感"),
    warmAffinity("C","热情亲和"),
    ethnicFlavor("D","民族特色"),
    internationalStyle("E","国际化风格"),
    freshNatural("F","清新自然"),
    TraditionalOldShop("G","传统老字号");

    private String name;

    private String choice;
    private LogoStyle(String choice, String name) {
        this.name = name;
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }

    public String getName() {
        return name;
    }

    static public List<LogoStyle> valueOfArray(String logoStyles){
        List<LogoStyle> logoStyleList = new ArrayList<LogoStyle>();
        String[] logoStyleArray = logoStyles.split(",");
        for(String logoStyleString : logoStyleArray){
            logoStyleList.add(valueOf(logoStyleString));
        }

        return logoStyleList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
