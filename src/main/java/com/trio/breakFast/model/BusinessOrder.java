package com.trio.breakFast.model;

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
@Table(name = "business_order")
public class BusinessOrder implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "business_order_id")
    private Integer businessOrderId;//id

    @ManyToOne
    @JoinColumn(name = "saleman_id")
    private Account saleman;//业务员

    @ManyToOne
    @JoinColumn(name = "designer_id", nullable = true)
    private Account designer;//设计师

    @OneToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;//问卷

    @Column(name = "zbj")
    private String zbj;//猪八戒id

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "submit_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitTime;//提交时间

    @Column(name = "payed_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payedTime;//付款时间

    @Column(name = "dispatch_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dispatchTime;//派单时间

    @Column(name = "operate_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operateTime;//操作时间

    @Column(name = "attachment_url")
    private String attachmentUrl;//附件下载地址

    @Column(name = "remark")
    private String remark;//备注
}