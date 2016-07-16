package com.trio.vmalogo.sys.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.trio.vmalogo.enums.Role;
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
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Integer accountId;//id

    @Column(name = "username", nullable = false, updatable = false)
    private String username;//账号

    @JSONField(serialize=false)
    @Column(name = "password", nullable = false)
    private String password;//密码

    @JSONField(serialize=false)
    @Column(name = "salt", nullable = false)
    private String salt;//盐

    @Column(name = "name")
    private String name;//姓名

    @Column(name = "role")
    private Role role;//角色

    @Column(name = "locked")
    private Boolean locked;//锁定状态

    @Column(name = "login_time", insertable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;//最后登录时间
}