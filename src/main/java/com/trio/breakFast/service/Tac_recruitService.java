package com.trio.breakFast.service;

import com.trio.breakFast.model.Tac_recruit;

import java.util.List;

/**
 * Created by ienovo on 2016/10/26.
 */
public interface Tac_recruitService {

    public void createRecruit(Integer userid,String username,String title,String workplace ,String deadline,String phone,String workInfo ,String displaytime,Integer   needpeopleNum);
    public List<Tac_recruit> getRecruiting(Integer userid,Integer page, Integer rows);
    public List<Tac_recruit> getRecruited(Integer userid,Integer page, Integer rows);
    public void changeStatusOfRecruit(Integer recruitid,Integer status);
    public Tac_recruit getRecruitByID(Integer recruitid);
    public  List<Tac_recruit> getRecruit(Integer page, Integer rows);
    public  List<Tac_recruit> getRecruitForManager(Integer page, Integer rows);
    public  List<Tac_recruit> getRecruitForRecruitor(Integer userid,Integer page, Integer rows,Integer rstatus);
    public  List<Tac_recruit> getRecruitForManagerAndApplicator(Integer page, Integer rows,Integer rstatus);
    public void changeStatusOfRecruitAndReason(Integer recruitid,Integer status,String reason);


}
