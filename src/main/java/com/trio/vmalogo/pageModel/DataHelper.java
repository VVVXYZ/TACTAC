package com.trio.vmalogo.pageModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author loser
 * @ClassName DataHelper
 * @Description
 * @Date 2016/05/14 5:10
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DataHelper<T> extends MessageHelper {
    private T data;

    @Override
    public String toString() {
        return "DataHelper{" +
                super.toString()+
                ", data=" + data +
                '}';
    }
}
