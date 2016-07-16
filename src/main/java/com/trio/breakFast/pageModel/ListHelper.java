package com.trio.breakFast.pageModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author loser
 * @ClassName ListHelper
 * @Description
 * @Date 2016/05/14 5:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ListHelper<T> extends MessageHelper {
    private List<T> dataList;
}
