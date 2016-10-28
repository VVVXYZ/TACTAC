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
@Table(name = "tac_admin")
public class Tac_admin implements Serializable {


    @Id
    @GeneratedValue
    @Column(name = "adminid")
    private Integer adminid;//管理员id


    @Column(name = "account")
    private String account;//账号

    @Column(name = "nickname")
    private String nickname;//昵称

    @Column(name = "name")
    private String name;//名字

    @Column(name = "phone")
    private String phone;//手机号

    @Column(name = "passwd")
    private String passwd;//密码

    @Column(name = "email")
    private String email;//邮箱

    @Column(name = "image")
    private String image;//头像

    @Column(name = "type")
    private Integer type;//用户类型



}
