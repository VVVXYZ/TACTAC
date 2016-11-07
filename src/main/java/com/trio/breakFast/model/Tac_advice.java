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
@Table(name = "tac_advice")

public class Tac_advice implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "adviceid")
    private Integer adviceid;//建议id


    @Column(name = "userid")
    private Integer userid;//建议人id

    @Column(name = "username")
    private String username;//建议人名字

    @Column(name = "time")
    private String time;//建议时间

    @Column(name = "phone")
    private String phone;//手机号

    @Column(name = "advice")
    private String advice;//建议内容


}
