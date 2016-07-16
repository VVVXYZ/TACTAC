package com.trio.vmalogo.pageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author loser
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterRule  implements Serializable {
    private String field;
    private String op;
    private Object data;

    @Override
    public String toString() {
        return "FilterRule{" +
                "field='" + field + '\'' +
                ", op='" + op + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
