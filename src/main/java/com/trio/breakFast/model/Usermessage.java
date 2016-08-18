package com.trio.breakFast.model;

/**
 * Created by asus on 2016/7/26.
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
@Table(name = "user_message")
public class Usermessage implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "usermessageid")
    private Integer usermessageid;//用户消息号

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//用户


    @Column(name = "type")
    private int type;//类型

    @Column(name = "username")
    private String username;//用户名

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orderlist order;//订单

    @Column(name = "datetime")
    private String datetime;//时间

    @Column(name = "messagecontent")
    private String messagecontent;//消息内容


}