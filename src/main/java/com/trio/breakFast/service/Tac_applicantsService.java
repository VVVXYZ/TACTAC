package com.trio.breakFast.service;

import com.trio.breakFast.model.Tac_applicants;

import java.util.List;

/**
 * Created by ienovo on 2016/10/26.
 */
public interface Tac_applicantsService {

    public List<Tac_applicants> getApplicant(Integer recruitid, Integer page, Integer rows);
    public void chooseApplicant(Integer recruitid,Integer userid);
    public void checkChoosen(Integer applicantsid);
    public List<Tac_applicants> getChoosenApplicant(Integer recruitid, Integer page, Integer rows);
    public List<Tac_applicants> getApplicantForApplicant(Integer recruitid, Integer page, Integer rows);
    public  Tac_applicants getApplicants(Integer recruitid,Integer userid);
    public  void createApplicants(Integer recruitid,String ownername,String applicanttime,Integer userid);
    public  Tac_applicants getApplicantsByID(Integer applicantsid);
    public void changeApplicants(Integer applicantsid,Integer status);
    public void CancelChooseApplicant(Integer recruitid,Integer userid);


}
