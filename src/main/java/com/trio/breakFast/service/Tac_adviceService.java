package com.trio.breakFast.service;

import com.trio.breakFast.model.Tac_advice;

import java.util.List;

/**
 * Created by ienovo on 2016/10/26.
 */
public interface Tac_adviceService {
    public void createAdvice(Integer userid,String username,
                             String time,String phone,String advice);
    public List<Tac_advice> getAdvice(Integer page, Integer rows);

}
