package com.trio.vmalogo.pageModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author loser
 * @ClassName PageHelper
 * @Description
 * @Date 2016/05/14 5:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageHelper<T> extends ListHelper<T>{
    private int page = 1;// 当前页
    private int rows = 10;// 每页显示记录数
    private int totalPages; //总页数
    private int totalRows; //总记录数
    private String sort = "id";//排序字段
    private String order = "ASC";//排序方式

    public void countTotalPages(){
        totalPages = totalRows / rows;
        if (totalRows % rows != 0) {
            totalPages ++;
        }
    }

    @Override
    public String toString() {
        return "PageHelper{" +
                super.toString()+
                ", page=" + page +
                ", rows=" + rows +
                ", totalPages=" + totalPages +
                ", totalRows=" + totalRows +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
