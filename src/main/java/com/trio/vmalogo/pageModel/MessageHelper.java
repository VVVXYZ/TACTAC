package com.trio.vmalogo.pageModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author loser
 * @ClassName MessageHelper
 * @Description
 * @Date 2016/05/14 5:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageHelper {
    private Boolean success = Boolean.TRUE;
    private String message;

    @Override
    public String toString() {
        return "MessageHelper{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
