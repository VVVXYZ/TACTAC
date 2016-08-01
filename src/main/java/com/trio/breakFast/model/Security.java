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
@Table(name = "security")
public class Security implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "securityquestionid")
    private Integer securityquestionid;//密保问题编号

    @Column(name = "securityquestion")
    private String securityquestion;//密保问题内容


}
