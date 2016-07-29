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
@Table(name = "order_detail")
public class Orderdetail implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "orderdetailid")
    private Integer orderdetailid;//订单明细编号

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orderlist orderid;//订单编号

    @Column(name = "commodityname")
    private String commodityname;//商品名称

    @Column(name = "commodityquantity")
    private Integer commodityquantity;//商品数量

}