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
@Table(name = "tac_atoocomment")

public class Tac_atoocomment implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "commentid")
    private Integer commentid;//招聘信息id

    @ManyToOne
    @JoinColumn(name = "applicantsid")
    private Tac_applicants tac_applicants;//用户id（）发布者id


    @Column(name = "ownerid")
    private String ownerid;//招聘发布者id

    @Column(name = "ownername")
    private Integer ownername;//招聘发布者名字

    @Column(name = "applicantid")
    private String applicantid;//申请者id

    @Column(name = "applicantname")
    private String applicantname;//申请者名字

    @Column(name = "recruitid")
    private String recruitid;//招聘信息id

    @Column(name = "aTOoComment")
    private String aTOoComment;//应聘者对招聘者评价

    @Column(name = "aTOoPoint")
    private Integer aTOoPoint;//应聘者对招聘者评分

    @Column(name = "aTOotime")
    private String aTOotime;//应聘者对招聘者评价发布时间

    @Column(name = "aTOoStatus")
    private Integer aTOoStatus;//应聘者对招聘者评价的状态

    @Column(name = "aTOoTipoff")
    private Integer aTOoTipoff;//应聘者对招聘者评价是否被举报

    @Column(name = "aTOoChecktime")
    private String aTOoChecktime;//应聘者对招聘者评价审核时间



}
