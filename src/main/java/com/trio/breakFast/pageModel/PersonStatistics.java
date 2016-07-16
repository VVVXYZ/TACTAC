package com.trio.breakFast.pageModel;

import lombok.Data;

/**
 * @author loser
 * @ClassName DesignerVO
 * @Description
 * @Date 2016/07/03 2:20
 */
@Data
public class PersonStatistics {
    public PersonStatistics(Integer accountId,String name){
        this.accountId = accountId;
        this.name = name;
    }

    private Integer accountId;
    private String name;
    private Integer total = 0;
    private Integer totalFinish = 0;
    private Integer month = 0;
    private Integer monthFinish = 0;
    private Integer monthCancel = 0;
    private String totalRate = "";
    private String monthRate = "";
}
