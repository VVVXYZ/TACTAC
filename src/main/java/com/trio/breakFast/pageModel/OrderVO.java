package com.trio.breakFast.pageModel;

import com.trio.breakFast.enums.OrderStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author loser
 * @ClassName OrderVo
 * @Description
 * @Date 2016/07/03 3:05
 */
@Data
public class OrderVO {
//    public OrderVO(Integer businessOrderId, String salemanName, String designerName, String qq, String cellphone, String zbj, OrderStatus status, Date submitTime, Date payedTime, Date dispatchTime, Date operateTime, String attachmentUrl, String remark) {
//        this.businessOrderId = businessOrderId;
//        this.salemanName = salemanName;
//        this.designerName = designerName;
//        this.qq = qq;
//        this.cellphone = cellphone;
//        this.zbj = zbj;
//        this.status = status;
//        this.submitTime = submitTime;
//        this.payedTime = payedTime;
//        this.dispatchTime = dispatchTime;
//        this.operateTime = operateTime;
//        this.attachmentUrl = attachmentUrl;
//        this.remark = remark;
//    }

    private Integer businessOrderId;//id

    private String salemanName;//业务员


    private String designerName;//设计师


    private String qq;//qq
    private Boolean qqBlack;//qq黑名单


    private String cellphone;//手机
    private Boolean cellphoneBlack;//手机黑名单


    private String zbj;//猪八戒id


    private OrderStatus status;


    private Date submitTime;//提交时间


    private Date payedTime;//付款时间


    private Date dispatchTime;//派单时间

    private Date operateTime;//操作时间

    private String attachmentUrl;//附件下载地址

    private String remark;//备注

    private String chinese;//logo中文名

}
