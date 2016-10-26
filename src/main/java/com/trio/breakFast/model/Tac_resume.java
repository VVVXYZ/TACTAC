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
@Table(name = "tac_resume")
public class Tac_resume implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resumeid")
    private String resumeid;//个人简历id

    @ManyToOne
    @JoinColumn(name = "userid")
    private Tac_user tac_user;//用户id


    @Column(name = "nickname")
    private String nickname;//用户昵称

    @Column(name = "name")
    private String name;//用户名字


    @Column(name = "phone")
    private String phone;//手机号

    @Column(name = "email")
    private String email;//邮箱

    @Column(name = "haddone")
    private Integer haddone;//曾经做过的项目个数

    @Column(name = "goodat")
    private String goodat;//擅长

    @Column(name = "singleResume")
    private String singleResume;//简历简介

    @Column(name = "detailResume")
    private String detailResume;//详细简历

    @Column(name = "url")
    private String url;//曾经做过的项目url


}
