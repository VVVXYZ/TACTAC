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
@Table(name = "tac_otoacomment")
public class Tac_otoacomment implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "commentid")
    private String commentid;//招聘信息id

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

    @Column(name = "oTOaComment")
    private String oTOaComment;//招聘者对应聘者评价

    @Column(name = "oTOaPoint")
    private Integer oTOaPoint;//招聘者对应聘者评分

    @Column(name = "oTOatime")
    private String oTOatime;//招聘者对应聘者评价发布时间

    @Column(name = "oTOaStatus")
    private Integer oTOaStatus;//招聘者对应聘者评价的状态

    @Column(name = "oTOaTipoff")
    private Integer oTOaTipoff;//招聘者对应聘者评价是否被举报

    @Column(name = "oTOaChecktime")
    private String oTOaChecktime;//招聘者对应聘者评价审核时间

}
