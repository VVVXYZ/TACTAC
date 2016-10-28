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
@Table(name = "tac_recruit")

public class Tac_recruit implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "recruitid")
    private Integer recruitid;//招聘信息id

    @ManyToOne
    @JoinColumn(name = "userid")
    private Tac_user tac_user;//用户id（）发布者id


    @Column(name = "owner")
    private String owner;//发布者名字

    @Column(name = "title")
    private String title;//招聘标题

    @Column(name = "workplace")
    private String workplace;//工作地点

    @Column(name = "dealdine")
    private String dealdine;//招聘截止日期

    @Column(name = "phone")
    private String phone;//发布者手机

    @Column(name = "email")
    private String email;//发布者邮箱

    @Column(name = "needpeopleNum")
    private Integer needpeopleNum;//招聘需要人数

    @Column(name = "status")
    private Integer status;//招聘的状态

    @Column(name = "applypeopleNum")
    private Integer applypeopleNum;//已经报名的人数

    @Column(name = "requrire")
    private String requrire;//语言要求（java  php）

    @Column(name = "singleInfo")
    private String singleInfo;//一句话简介

    @Column(name = "workInfo")
    private String workInfo;//详细介绍

    @Column(name = "displaytime")
    private String displaytime;//招聘提交时间

    @Column(name = "isOk")
    private Integer isOk;//是否通过审核

    @Column(name = "OKtime")
    private String OKtime;//审核时间

    @Column(name = "reason")
    private String reason;//理由



}
