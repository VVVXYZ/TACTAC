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
@Table(name = "orderlist")
public class Orderlist implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Integer orderid;//订单编号

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;//用户编号

    @Column(name = "amount")
    private Double amount;//订单金额

    @Column(name = "datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;//时间

    @Column(name = "distributionmethod")
    private String distributionmethod;//配送方式

    @Column(name = "paymentmethod")
    private String paymentmethod;//支付方式

    @Column(name = "orderstatus")
    private Integer orderstatus;//订单状态

    @Column(name = "remark")
    private String remark;//备注
}