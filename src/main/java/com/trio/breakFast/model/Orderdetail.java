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
@Table(name = "order_detail")
public class Orderdetail implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "orderdetailid")
    private Integer orderdetailid;//订单明细编号

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orderlist order;//订单

    @Column(name = "commodityname")
    private String commodityname;//商品名称

    @Column(name = "commodityquantity")
    private Integer commodityquantity;//商品数量

    @Column(name = "price")
    private Double price;//商品价格

}