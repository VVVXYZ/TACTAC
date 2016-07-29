package com.trio.breakFast.model;

/**
 * Created by asus on 2016/7/26.
 */

import com.trio.breakFast.enums.OrderStatus;
import com.trio.breakFast.sys.entity.Account;
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
    private User userid;//用户编号

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orderlist orderid;//订单编号

    @Column(name = "datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;//时间

    @Column(name = "messagecontent")
    private String messagecontent;//消息内容

}