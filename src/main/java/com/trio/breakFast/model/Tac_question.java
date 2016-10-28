package com.trio.breakFast.model;

/**
 * Created by ienovo on 2016/10/27.
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
@Table(name = "tac_question")
public class Tac_question implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "questionid")
    private Integer questionid;//个人简历id

    @Column(name = "kind")
    private String kind;//用户方向

    @Column(name = "question")
    private String question;//问题

    @Column(name = "choice1")
    private String choice1;//选项1

    @Column(name = "choice2")
    private String choice2;//选项2

    @Column(name = "choice3")
    private String choice3;//选项3

    @Column(name = "choice4")
    private String choice4;//选项4

    @Column(name = "answer")
    private Integer answer;//答案





}
