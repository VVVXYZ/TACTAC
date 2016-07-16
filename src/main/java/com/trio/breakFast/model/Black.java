package com.trio.breakFast.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "black")
public class Black implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "black_id")
    private Integer blackId;//id

    @Column(name = "code")
    private String code;//qq/手机
}