package com.trio.breakFast.service;

import com.trio.breakFast.model.Tac_otoacomment;

import java.util.List;

/**
 * Created by ienovo on 2016/10/26.
 */
public interface Tac_otoacommentService {
    public Tac_otoacomment getTac_otoacomment(Integer applicantsid);
    public void createComment(Integer applicantsid,Integer recruitid,Integer applicantid,Integer ownerid,
                              String ownername,String comment,float point, String cmmentTime);
    public List<Tac_otoacomment> getComment(Integer applicantid,Integer page, Integer rows);
    public List<Tac_otoacomment> getCommentForManager(Integer page, Integer rows);
    public Tac_otoacomment getTac_otoacommentByID(Integer commentid);
    public void tipComment(Integer commentid);
    public void checkComment(Integer commentid,String checktime,Integer status);
}
