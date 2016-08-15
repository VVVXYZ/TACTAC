package com.trio.breakFast.model;

/**
 * Created by asus on 2016/7/26.
 */

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "orderlist")
public class Orderlist implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Integer orderid;//订单编号

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//用户



    @Column(name = "username")
    private String username;//用户名

    @Column(name = "amount")
    private Double amount;//订单金额


    @Column(name = "datetime")
    private String datetime;//时间

    @Column(name = "deliverymethod")
    private String deliverymethod;//配送方式

    //deliverytime
    @Column(name = "deliverytime")
    private String deliverytime;//送达时间

    @Column(name = "paymentmethod")
    private String paymentmethod;//支付方式

    @Column(name = "orderstatus")
    private Integer orderstatus;//订单状态

    @Column(name = "remark")
    private String remark;//备注

    @Column(name = "adress")
    private String adress;//备注

    @Column(name = "receivername")
    private String receivername;//收货人姓名

    @Column(name = "phone")
    private String phone;//手机号


}