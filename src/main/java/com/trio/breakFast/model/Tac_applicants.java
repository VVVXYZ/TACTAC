package com.trio.breakFast.model;

/**
 * Created by ienovo on 2016/10/26.
 */
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "tac_applicants")
public class Tac_applicants implements Serializable  {

    @Id
    @GeneratedValue
    @Column(name = "applicantsid")
    private Integer applicantsid;//招聘关系id

    @ManyToOne
    @JoinColumn(name = "userid")
    private Tac_user tac_user;//用户id（）应聘者id


    @Column(name = "applicantname")
    private String applicantname;//应聘者名字

    @Column(name = "haddone")
    private Integer haddone;//曾经做过的项目个数

    @Column(name = "goodat")
    private String goodat;//擅长

    @Column(name = "image")
    private String image;//头像

    @ManyToOne
    @JoinColumn(name = "recruitid")
    private Tac_recruit tac_recruit ;//招聘信息id

    @Column(name = "applicanttime")
    private String applicanttime;//申请时间

    @Column(name = "ownerid")
    private String ownerid;//招聘拥有者id

    @Column(name = "ownername")
    private String ownername;//工作地点招聘拥有者名字

    @Column(name = "title")
    private String title;//招聘标题

    @Column(name = "singleInfo")
    private String singleInfo;//招聘简介

    @Column(name = "deadline")
    private String deadline;//招聘截止时间

    @Column(name = "choosen")
    private Integer choosen;//招聘者是否选择
}
