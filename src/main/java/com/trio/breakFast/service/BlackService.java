package com.trio.breakFast.service;

import com.trio.breakFast.model.Black;

/**
 * @author loser
 * @ClassName BlackService
 * @Description
 * @Date 2016/07/03 1:28
 */

public interface BlackService {
    void put(String code);
    void search(String code);
    Black get(String code);
    void remove(String code);
}
