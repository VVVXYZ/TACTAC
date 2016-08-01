package com.trio.breakFast.pageModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Created by ienovo on 2016/8/1.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MyDataHelper<T> {

    private Boolean success = Boolean.TRUE;
    private String message;
    private T data;
    private T mydata;

    @Override
    public String toString() {
        return "MyDataHelper{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", mydata=" + mydata +
                '}';
    }
}
