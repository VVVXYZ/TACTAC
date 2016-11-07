package com.trio.breakFast.service;

import com.trio.breakFast.model.Tac_atoocomment;

import java.util.List;

/**
 * Created by ienovo on 2016/10/26.
 */
public interface Tac_atoocommentService {

    public Tac_atoocomment getTac_atoocomment(Integer applicantsid);
    public Tac_atoocomment getTac_atoocommentByID(Integer commentid);
    public void createComment(Integer applicantsid,Integer recruitid,Integer applicantid,Integer ownerid,
                              String ownername,String comment,float point, String cmmentTime);
    public List<Tac_atoocomment> getComment(Integer ownerid,Integer page, Integer rows);
    public List<Tac_atoocomment> getCommentForManager(Integer page, Integer rows);
    public void tipComment(Integer commentid);
    public void checkComment(Integer commentid,String checktime,Integer status);
}
