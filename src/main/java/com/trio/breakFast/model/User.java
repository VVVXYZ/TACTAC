package com.trio.breakFast.model;

/**
 * Created by asus on 2016/7/26.
 */

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private String user_id;//用户编号

    @ManyToOne
    @JoinColumn(name = "securityquestionid")
    private Security securityquestionid;//密保问题编号

    @Column(name = "username")
    private String username;//用户名

    @Column(name = "password")
    private String password;//密码

}