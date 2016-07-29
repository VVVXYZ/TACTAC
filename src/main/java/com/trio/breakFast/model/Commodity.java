package com.trio.breakFast.model;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by asus on 2016/7/26.
 */
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "commodity")
public class Commodity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "commodityid")
    private Integer commodityid;//商品编号

    @Column(name = "commodityname")
    private String commodityname;//商品名称

    @Column(name = "commodityamount")
    private Double commodityamount;//商品单价

    @Column(name = "sales")
    private Integer sales;//销售量

    @Column(name = "numberofpoints")
    private Integer numberofpoints;//点赞数

    @Column(name = "commoditypicture")
    private String commoditypicture;//商品图片

    @Column(name = "commoditydiscription")
    private String commoditydiscription;//商品描述

    @Column(name = "commoditycategory")
    private String commoditycategory;//商品类别
}
