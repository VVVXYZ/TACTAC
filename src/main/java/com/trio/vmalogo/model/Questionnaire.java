package com.trio.vmalogo.model;

import com.trio.vmalogo.enums.LogoColor;
import com.trio.vmalogo.enums.LogoComponent;
import com.trio.vmalogo.enums.LogoStartpoint;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "questionnaire")
public class Questionnaire implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "questionnaire_id")
    private Integer questionnaireId;//id

//    @OneToOne(mappedBy = "questionnaire")
//    private BusinessOrder businessOrder;//业务订单

    @Column(name = "chinese")
    private String chinese;//中文

    @Column(name = "english")
    private String english;//英文

    @Column(name = "main_business")
    private String mainBusiness;//公司（或品牌）经营范围和详细内容

    @Column(name = "logo_component")
    private LogoComponent logoComponent;//您希望设计的标志(logo)由

    @Column(name = "logo_style")
    private String logoStyles;//您更倾向于哪种标志(logo)风格

    @Column(name = "logo_colour")
    private LogoColor logoColor;//标志色彩范围选定

    @Column(name = "colour_forbid")
    private Boolean colourForbid;//禁忌色

    @Column(name = "colour_forbid_detail")
    private String colourForbidDetail;//禁忌色详情

    @Column(name = "logo_startpoint")
    private LogoStartpoint logoStartpoint;//标志创作出发点：

    @Column(name = "logo_startpoint_detail")
    private String logoStartpointdetail;//标志创作出发点详情：

    @Column(name = "cellphone")
    private String cellphone;//联系电话

    @Column(name = "qq")
    private String qq;//联系QQ

    @Column(name = "others")
    private String others;//其他要求

    @Column(name = "attachment_urls")
    private String attachmentUrls;//图片附件地址


}