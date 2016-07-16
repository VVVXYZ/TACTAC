package com.trio.breakFast.pageModel;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author loser
 */
@Data
public class FilterGroup implements Serializable{
    private String groupOp = "AND";
    private ArrayList<FilterRule> rules;
    private ArrayList<FilterGroup> groups;

    public void addRules(FilterRule filterRule){
        if(rules == null){
            rules = new ArrayList<>();
        }
        this.rules.add(filterRule);
    }

    public void addGroups(FilterGroup filters){
        if(groups == null){
            groups = new ArrayList<>();
        }
        this.groups.add(filters);
    }

    @Override
    public String toString(){

        return "Filter{" +
                "groupOp='" + groupOp + '\'' +
                ", rules='" + rules + '\'' +
                ", groups='" + groups + '\'' +
                '}';
    }

    /**
     * 转换FilterGroup为对象
     *
     * @param filters 过滤json串
     * @return FilterGroup对象
     * @Date 2016/5/18 16:03
     * @author loser
     */
    public static FilterGroup newFilterGroup(String filters){
        if (filters != null && !filters.equals("")) {
            try {
                filters = filters.replace("&quot;", "\"");
                return JSON.parseObject(filters, FilterGroup.class);
            } catch (Exception e) {
                return null;
            }
        }

        return new FilterGroup();
    }
}
