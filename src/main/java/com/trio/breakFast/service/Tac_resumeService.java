package com.trio.breakFast.service;

import com.trio.breakFast.model.Tac_resume;

/**
 * Created by ienovo on 2016/10/26.
 */
public interface Tac_resumeService {

    public void  createResume(String name,String nickname,String phone,String email ,
                              String singleResume,String detailResume);
    public Tac_resume getResumeBuID(Integer userid);
    public void  updateResume(Integer userid ,String name,String nickname,String phone,String email ,
                              String singleResume,String detailResume);
    public Tac_resume  getResume(Integer userid);
    public Tac_resume  getResumeByName(String name);
}
