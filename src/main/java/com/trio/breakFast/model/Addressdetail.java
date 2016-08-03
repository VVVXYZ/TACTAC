package com.trio.breakFast.model;

/**
 * Created by asus on 2016/7/25.
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
@Table(name = "address_detail")
public class Addressdetail implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Integer addressid;//地址编号

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//用户

    @Column(name = "username")
    private String username;//用户名

    @Column(name = "address_content")
    private String address;//地址

    @Column(name = "receivername")
    private String receivername;//收货人姓名

    @Column(name ="phone")
    private String phone;//手机号码

}