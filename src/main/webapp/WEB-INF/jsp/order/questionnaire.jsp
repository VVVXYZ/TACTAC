<%@ page import="com.trio.breakFast.enums.LogoComponent" %>
<%@ page import="com.trio.breakFast.enums.LogoStyle" %>
<%@ page import="com.trio.breakFast.enums.LogoColor" %>
<%@ page import="com.trio.breakFast.enums.LogoStartpoint" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"--%>
<%--"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>

<html>
<head lang="en">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=no">
    <meta name="format-detection" content="telephone=no"/>
    <title>微码Logo设计需求</title>
    <script type="text/javascript">
        if (window.location.hash) {
            window.location.hash = "";
            window.location.href = window.location.href.replace("#", "");
        }
        var isWeiXin = 0;
    </script>
    <link rel="stylesheet" href="${ctx}/assets/css/jqmobo.css?v=5"/>
    <link href="${ctx}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/assets/js/mobile/jquery-1.10.1.min.js"></script>
    <script src="${ctx}/assets/js/hintinfo.js?v=1" type="text/javascript"></script>
    <script src="${ctx}/assets/js/jqmobo2.js?v=127" type="text/javascript"></script>
    <style>

        .header {
            background: none repeat scroll 0 0 #0067ac;
            color: #fff;
            display: inline-block;
            font-size: 20px;
            height: 50px;
            line-height: 50px;
            overflow: hidden;
            padding: 0 70px;
            text-align: center;
            text-overflow: ellipsis;
            white-space: nowrap;
            width: 100%;
            z-index: 999;
            position: relative;
        }

        .header .header_text {
            display: inline-block;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            width: 100%;
        }

        .header_left {
            position: absolute;
            left: 0px;
            top: 0px;
            width: 60px;
            height: 50px;
        }

        .header_left a {
            display: block;
            width: 100%;
            height: 100%;
        }

        .back {
            display: inline-block;
            width: 21px;
            height: 21px;
            margin: 15px auto 0 auto;
            background-position: 0 0;
            background-repeat: no-repeat;
            background-image: url('${ctx}/assets/img/weixin/left.png');
            background-size: contain;
        }

        .header_right {
            position: absolute;
            right: 0px;
            top: 0px;
            min-width: 60px;
            height: 50px; /*background-color: #3DA4DE;*/
        }

        .header_right a {
            display: block;
            width: 100%;
            height: 100%;
        }

        .header_right a .miniButton {
            float: left;
            margin-top: 10px;
        }

        .miniButton {
            border-radius: 2px;
            color: #fff;
            display: inline-block;
            font-size: 14px;
            line-height: 21px;
            padding: 5px 10px;
        }

        .rm10 {
            margin-right: 10px;
        }

        .More_set {
            background-color: #fdfbcb;
            border: 1px solid #f3f3f3;
            padding: 10px;
            text-align: right;
        }

        .More_set p {
            color: #757774;
            font-size: 16px;
            margin: 0;
            padding: 0;
            padding-bottom: 5px;
        }


    </style>
</head>
<body>

<div class="main">
    <div id="divTopHeader">
        <div class="header">
            <!--<div class="header_left">-->
            <!--<a href="mobile/template.aspx" id="hrefBack"><i class="back"></i>-->
            <!--</a>-->
            <!--</div>-->
            <h3>
                微码Logo设计需求提交</h3>

        </div>


    </div>


    <!--<div id="toptitle" style='display:none;'>-->
    <!--<h1 class="htitle">-->
    <!--聚会报名表</h1>-->
    <!--</div>-->
    <!---->


    <div id="divContent">

        <div class="formfield">
            <div style="color:#ed6459; font-size: 16px;">初稿提交时间:LOGO设计时间是2-4个工作日（工作日是不包含周末的）</div>
            <div style="color:#0067ac; font-size: 16px;margin-top: 15px;">【注意事项】</div>
            <div style="font-size: 14px;color:#0067ac;margin-top: 5px;">1.提供3套初稿方案</div>
            <div style="font-size: 14px;color:#0067ac;margin-top: 5px;">2.选择其中一套初稿，修改到满意为止，确认好的意见不得无故推翻</div>

            <div style="font-size: 14px;color:#0067ac;margin-top: 5px;">初稿阶段：雇主对初稿不满意，单方交易终止。我司承诺退款70%</div>
            <div style="font-size: 14px;color:#0067ac;margin-top: 5px;">修稿阶段：二稿不满意可退款50%，二稿继续修改后可退款30%</div>
            <div style="font-size: 14px;color:#0067ac;margin-top: 5px;">定稿阶段：定稿并交接设计源文件后不予退款</div>
            <div style="font-size: 14px;color:#0067ac;margin-top: 5px;">3.以上退款协议均为不进行文档索要的情况下适用</div>
            <div style="font-size: 14px;color:#0067ac;margin-top: 5px;">
                另任何时候客户出现不满意情况且未提出退款申请之前，我司均承诺修稿到满意为止。如需总监亲自操刀设计，需额外加收费用800元。
            </div>

        </div>

        <!--<div id="divDesc" class="formfield">-->
        <!--<span class="description">-->
        <!--<span style="font-size:14px;">注：为我们合作的更加愉快，设计前您如有特殊设计要求（比如融入指定图形，规定指定颜色等等），您一定要在表格中反映出来，一旦设计方案出来后，我们将不接受附加要求。</span></span>-->
        <!--</div>-->


        <div id="divQuestion">
            <fieldset class='fieldset' style='' id='fieldset1'>
                <form id="questionnaireForm" method="post" action="${ctx}/order/updateQuestionnaire">
                    <input type="hidden" name="questionnaireId" value="${questionnaire.questionnaireId}"/>

                    <div id="attachmentUrlDiv">
                        <c:if test="${not empty pics}">
                            <c:forEach var="pic" items="${pics}">
                                <input type="hidden" name="attachmentUrl" value="${pic}"/>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class='field ui-field-contain' id='div1' req='1' topic='1' data-role='fieldcontain' type='1'>
                        <div class='field-label'>1. 标志(logo)中文名称：<span class='req'>*</span></div>
                        <div class='ui-input-text'><input type='text' id='q1' name='chinese' verify='中文名称'
                                                          value="${questionnaire.chinese}">
                        </div>
                    </div>
                    <div class='field ui-field-contain' req='1' topic='1' data-role='fieldcontain' type='1'>

                        <div class='field-label'>&nbsp;&nbsp;&nbsp;标志(logo)英文名称：</div>
                        <div class='ui-input-text'><input type='text' id='q12' name='english'
                                                          value="${questionnaire.english}"></div>

                    </div>

                    <div class="field ui-field-contain" id="div22" topic="6" data-role="fieldcontain" type="2">
                        <div class="field-label">2. 公司（或品牌）经营范围和详细内容:</div>
                        <div class="ui-input-text">
                            <textarea rows="3" style="max-height:100px;" id="q22" name="mainBusiness"
                                      value="">${questionnaire.mainBusiness}</textarea></div>
                        <div class="errorMessage"></div>
                    </div>


                    <div class='field ui-field-contain' id='div33' req='1' topic='5' data-role='fieldcontain' type='3'>
                        <div class='field-label'>3. 您希望设计的标志(logo)由：<span class='req'>*</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;<span id="remark"
                                                          style="text-decoration: underline;color:#ed6459;cursor: pointer;">点击此处查看样例</span>
                        </div>
                        <div class='ui-controlgroup'>
                            <div class='ui-radio'>
                                <span class='jqradiowrapper'>
                                <input type='radio' value='<%=LogoComponent.english.name()%>' name='logoComponent'
                                       id='q3_1' style='display:none;'/>
                                <a class='jqradio' href='javascript:;'></a>
                                </span>
                                <label for='q3_1'><%=LogoComponent.english.getName()%>
                                </label>
                            </div>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='<%=LogoComponent.chinese.name()%>' name='logoComponent'
                                       id='q3_2' style='display:none;'/><a class='jqradio'
                                                                           href='javascript:;'></a></span><label
                                    for='q3_2'><%=LogoComponent.chinese.getName()%>
                            </label>
                            </div>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' id='q3_3' value='<%=LogoComponent.picture.name()%>'
                                       name='logoComponent' style='display:none;'/><a class='jqradio'
                                                                                      href='javascript:;'></a></span><label
                                    for='q3_3'><%=LogoComponent.picture.getName()%>
                            </label>
                            </div>
                        </div>

                    </div>
                    <div class='field ui-field-contain' id='div43' topic='43' data-role='fieldcontain' type='4'>
                        <div class='field-label'>4. 您更倾向于哪种标志(logo)风格：</div>
                        <div class='ui-controlgroup'>
                            <div class='ui-checkbox'><span class='jqcheckwrapper'>
                                <input type='checkbox' value='<%=LogoStyle.professionalSteady.name()%>' name='logoStyle'
                                       id='q44_1' style='display:none;'/><a class='jqcheck'
                                                                            href='javascript:;'></a></span><label
                                    for='q44_1'><%=LogoStyle.professionalSteady.getName()%>
                            </label></div>
                            <div class='ui-checkbox'><span class='jqcheckwrapper'>
                                <input type='checkbox' value='<%=LogoStyle.fashionDynamic.name()%>' name='logoStyle'
                                       id='q44_2' style='display:none;'/><a class='jqcheck'
                                                                            href='javascript:;'></a></span><label
                                    for='q44_2'><%=LogoStyle.fashionDynamic.getName()%>
                            </label></div>
                            <div class='ui-checkbox'><span class='jqcheckwrapper'>
                                <input type='checkbox' value='<%=LogoStyle.warmAffinity.name()%>' name='logoStyle'
                                       id='q44_3' style='display:none;'/><a class='jqcheck'
                                                                            href='javascript:;'></a></span><label
                                    for='q44_3'><%=LogoStyle.warmAffinity.getName()%>
                            </label></div>
                            <div class='ui-checkbox'><span class='jqcheckwrapper'>
                                <input type='checkbox' value='<%=LogoStyle.ethnicFlavor.name()%>' name='logoStyle'
                                       id='q44_4' style='display:none;'/><a class='jqcheck'
                                                                            href='javascript:;'></a></span><label
                                    for='q44_4'><%=LogoStyle.ethnicFlavor.getName()%>
                            </label></div>
                            <div class='ui-checkbox'><span class='jqcheckwrapper'>
                                <input type='checkbox' value='<%=LogoStyle.internationalStyle.name()%>' name='logoStyle'
                                       id='q44_5' style='display:none;'/><a class='jqcheck'
                                                                            href='javascript:;'></a></span><label
                                    for='q44_5'><%=LogoStyle.internationalStyle.getName()%>
                            </label>
                            </div>
                            <div class='ui-checkbox'><span class='jqcheckwrapper'>
                                <input type='checkbox' value='<%=LogoStyle.freshNatural.name()%>' name='logoStyle'
                                       id='q44_6' style='display:none;'/><a class='jqcheck'
                                                                            href='javascript:;'></a></span><label
                                    for='q44_6'><%=LogoStyle.freshNatural.getName()%>
                            </label></div>
                            <div class='ui-checkbox'><span class='jqcheckwrapper'>
                                <input type='checkbox' value='<%=LogoStyle.TraditionalOldShop.name()%>' name='logoStyle'
                                       id='q44_7' style='display:none;'/><a class='jqcheck'
                                                                            href='javascript:;'></a></span><label
                                    for='q44_7'><%=LogoStyle.TraditionalOldShop.getName()%>
                            </label>
                            </div>
                        </div>

                    </div>


                    <div class='field ui-field-contain' id='div45' topic='49' data-role='fieldcontain' type='3'>
                        <div class='field-label'>5. 标志色彩范围选定：</div>
                        <div class='ui-controlgroup'>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='<%=LogoColor.cool.name()%>' name='logoColor' id='q45_1'
                                       style='display:none;'/><a class='jqradio' href='javascript:;'></a></span><label
                                    for='q45_1'>
                                <div class="boxs">
                                    <div class=""><%=LogoColor.cool.getName()%>
                                    </div>
                                    <div>&nbsp;&nbsp;如:</div>
                                    <div class="five1"></div>
                                    <div class="box-1"></div>
                                </div>

                            </label></div>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='<%=LogoColor.warm.name()%>' name='logoColor' id='q45_2'
                                       style='display:none;'/><a class='jqradio' href='javascript:;'></a></span><label
                                    for='q45_2'>
                                <div class="boxs">
                                    <div class=""><%=LogoColor.warm.getName()%>
                                    </div>
                                    <div>&nbsp;&nbsp;如:</div>
                                    <div class="five2"></div>
                                    <div class="box-1"></div>
                                </div>

                            </label></div>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='<%=LogoColor.neutral.name()%>' name='logoColor' id='q45_3'
                                       style='display:none;'/><a class='jqradio' href='javascript:;'></a></span><label
                                    for='q45_3'>
                                <div class="boxs">
                                    <div class=""><%=LogoColor.neutral.getName()%>
                                    </div>
                                    <div>&nbsp;&nbsp;如:</div>
                                    <div class="five3"></div>
                                    <div class="box-1"></div>
                                </div>

                            </label></div>

                        </div>


                    </div>

                    <div class='field ui-field-contain' id='div75' topic='79' data-role='fieldcontain' type='3'>

                        <div class='field-label'>&nbsp;&nbsp; 禁忌色：</div>
                        <div class='ui-controlgroup'>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='true'
                                       name='colourForbid' id='q62_1'
                                       style='display:none;'><a
                                    class='jqradio' href='javascript:;'></a></span><label for='q62_1'>限制颜色</label>

                                <div class='ui-text'>
                                    <input type='text' rel='q4_5' name="colourForbidDetail" placeholder="此处请填入您禁忌的颜色"
                                           value="${questionnaire.colourForbidDetail}" class='OtherText'/></div>
                                <br/></div>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='false' name='colourForbid' id='q62_2' style='display:none;'/><a
                                    class='jqradio' href='javascript:;'></a></span><label
                                    for='q62_2'>无限制，设计师自由发挥</label>

                            </div>


                        </div>

                    </div>

                    <div class='field ui-field-contain' id='div46' topic='59' type='3'>
                        <div class='field-label'>6. 标志创作出发点：</div>
                        <div class='ui-controlgroup'>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='<%=LogoStartpoint.brandName.name()%>' name='logoStartpoint'
                                       id='q46_1' style='display:none;'><a class='jqradio'
                                                                           href='javascript:;'/></span><label
                                    for='q46_1'>
                                <div class="boxs">
                                    <div class=""><%=LogoStartpoint.brandName.getName()%>
                                    </div>
                                    <div>&nbsp;&nbsp;如:</div>
                                    <div class="six1"></div>
                                    <div class="box-1"></div>
                                </div>
                            </label>

                            </div>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='<%=LogoStartpoint.industryCharacteristics.name()%>'
                                       name='logoStartpoint' id='q46_2' style='display:none;'><a class='jqradio'
                                                                                                 href='javascript:;'/></span><label
                                    for='q46_2'>
                                <div class="boxs">
                                    <div class=""><%=LogoStartpoint.industryCharacteristics.getName()%>
                                    </div>
                                    <div>&nbsp;&nbsp;如:</div>
                                    <div class="six2"></div>
                                    <div class="box-1"></div>
                                </div>
                            </label></div>
                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='<%=LogoStartpoint.outstandingIdea.name()%>'
                                       name='logoStartpoint' id='q46_3' style='display:none;'/><a class='jqradio'
                                                                                                  href='javascript:;'></a></span><label
                                    for='q46_3'>
                                <div class="boxs">
                                    <div class=""><%=LogoStartpoint.outstandingIdea.getName()%>
                                    </div>
                                    <div>&nbsp;&nbsp;如:</div>
                                    <div class="six3"></div>
                                    <div class="box-1"></div>
                                </div>

                            </label></div>

                            <div class='ui-radio'><span class='jqradiowrapper'>
                                <input type='radio' value='<%=LogoStartpoint.others.name()%>' name='logoStartpoint'
                                       id='q46_4' style='display:none;'/><a class='jqradio'
                                                                            href='javascript:;'></a></span><label
                                    for='q46_4'><%=LogoStartpoint.others.getName()%>
                            </label>

                                <div class='ui-text'>
                                    <input type='text' rel='q4_5' name="logoStartpointdetail" placeholder="如果有指定，请填写"
                                           value="${questionnaire.logoStartpointdetail}" class='OtherText'/></div>
                                <br/></div>

                        </div>


                    </div>


                    <div class='field ui-field-contain' req='1' topic='2' data-role='fieldcontain' type='1'>
                        <div class='field-label'>7、您的联系电话：<span class='req'>*</span></div>
                        <div class='ui-input-text'>
                            <input type='number' pattern='[0-9]*' id='q2' value="${questionnaire.cellphone}"
                                   name='cellphone' verify='手机'/></div>
                    </div>
                    <div class='field ui-field-contain' req='1' topic='2' data-role='fieldcontain' type='1'>
                        <div class='field-label'>&nbsp;&nbsp;&nbsp;&nbsp;您的联系QQ：<span class='req'>*</span></div>
                        <div class='ui-input-text'>
                            <input type='number' pattern='[0-9]*' id='q3' name='qq' value="${questionnaire.qq}"/></div>

                    </div>

                    <div class="field ui-field-contain" id="div24" topic="26" data-role="fieldcontain" type="2">
                        <div class="field-label">8. 其他要求:</div>
                        <div class="ui-input-text">
                            <textarea rows="3" style="max-height:100px;" id="q26" name="others"
                                      value="">${questionnaire.others}</textarea></div>
                        <div class="errorMessage"></div>
                    </div>
                </form>
                <div class="field ui-field-contain" id="div74" topic="76" data-role="fieldcontain" type="2">
                    <div class="field-label" style="color:#ed6459;">9.
                        请提供您喜欢的LOGO样式，或者同行业中您欣赏的LOGO案例，有利于设计师准确定位（必填项,可截图）：
                    </div>
                    <form id="fileUpload" action="${ctx}/questionnaire/pictureUpload" method="post"
                          enctype="multipart/form-data">
                        <div class="">
                            <ul class="piclist ">
                                <c:if test="${not empty pics}">
                                    <c:forEach var="pic" items="${pics}">
                                        <li class="prelative"><img class="width-100" src="${pic}">
                                            <shiro:hasRole name="saleman"><i onclick="deletePicture(this)" class="closePicIcon"></i></shiro:hasRole>
                                        </li>                                    </c:forEach>
                                </c:if>
                                <shiro:hasRole name="saleman">
                                    <li class="prelative">
                                        <a href="javascript:;" class="file pic-margin">
                                            <input type="file" name="picture" id="picture">

                                            <div class=""><img class="width-100"
                                                               src="${ctx}/assets/img/mobile/addPhotosBtn.png"></div>
                                        </a>
                                    </li>
                                </shiro:hasRole>
                            </ul>
                        </div>
                    </form>
                    <div class="errorMessage"></div>
                </div>

            </fieldset>
        </div>


        <div class="footer">
            <div class="ValError">
            </div>
            <div id="divSubmit" style="padding: 10px; display:none;">

                <!--<a id="ctlNext" href="javascript:;" class="button blue">-->

                <div style="margin:0px 0 10px; padding-top:10px;">
                    <!--<a href="urlreport.aspx?url=3741019" style="float: right;color:#aaa;font-size:13px;"-->
                    <!--class="reportto">-->
                    <!--技术支持：微码科技&nbsp;&nbsp; QQ:365703546</a>-->

                    <div style="clear: both;">
                    </div>
                </div>
            </div>


        </div>

    </div>


</div>

<div class="am-modal"><i class="suit_icon"></i><img src="${ctx}/assets/img/mobile/remark.jpg" alt=""/></div>
<div class="am-dimmer "></div>
<a id='lnkCity' style="display: none;"></a>
<a href="javascript:;" class="scrolltop" style="display:none;"></a>
<script type="text/javascript">
    var activityId = 3741019;
    var isPub = 0;
    var sojumpParm = '';
    var lastTopic = 0;
    var Password = "";
    var guid = "";
    var udsid = '';
    var langVer = 0;
    var cProvince = "";
    var cCity = "";
    var cIp = "";
    var divTip = document.getElementById("divTip");
    var displayPrevPage = "none";
    var inviteid = '';
    var access_token = "";
    var openid = "";
    var wxthird = 0;
    var hashb = 0;
    var sjUser = '';
    var sourceurl = '';
    var jiFenBao = 0;
    var cAlipayAccount = "";
    var isRunning = 1;
    var SJBack = '';
    var jiFen = "0";
    var ItemDicData = "";
    var rndnum = "3029962374.11947505";
    var totalPage = 1;
    var totalCut = 0;
    var cepingCandidate = "";
    var cpid = "";


</script>


<script type="text/javascript">
    var needAvoidCrack = 0;
    var tdCode = "tdCode";
    var imgCode = $("#imgCode")[0];
    var submit_text = $("#yucinput")[0];
    var tCode = $("#" + tdCode)[0];
</script>
<script type="application/javascript">
    $("#remark").on("click", function () {
        $(".am-modal").show();
        $(".am-dimmer").show();
    });
    $(".suit_icon").on("click", function () {
        $(".am-modal").hide();
        $(".am-dimmer").hide();
    });
</script>
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.form.js"></script>
<script>
    $(function () {
        <c:if test="${not empty questionnaire.logoComponent}">
        $('input:radio[name=logoComponent][value=${questionnaire.logoComponent}]').prop("checked", "checked");
        $('input:radio[name=logoComponent][value=${questionnaire.logoComponent}]').parent().find("a").addClass("jqchecked");
        </c:if>

        <c:if test="${not empty styles}">
        <c:forEach var="var" items="${styles}">
        $('input:checkbox[name=logoStyle][value=${var}]').attr("checked", "checked");
        $('input:checkbox[name=logoStyle][value=${var}]').parent().find("a").addClass("jqchecked");
        </c:forEach>
        </c:if>
        <c:if test="${not empty questionnaire.logoColor}">
        $('input:radio[name=logoColor][value=${questionnaire.logoColor}]').prop("checked", "checked");
        $('input:radio[name=logoColor][value=${questionnaire.logoColor}]').parent().find("a").addClass("jqchecked");
        </c:if>
        <c:if test="${not empty questionnaire.colourForbid}">
        $('input:radio[name=colourForbid][value=${questionnaire.colourForbid}]').prop("checked", "checked");
        $('input:radio[name=colourForbid][value=${questionnaire.colourForbid}]').parent().find("a").addClass("jqchecked");
        </c:if>
        <c:if test="${not empty questionnaire.logoStartpoint}">
        $('input:radio[name=logoStartpoint][value=${questionnaire.logoStartpoint}]').prop("checked", "checked");
        $('input:radio[name=logoStartpoint][value=${questionnaire.logoStartpoint}]').parent().find("a").addClass("jqchecked");
        </c:if>

        <shiro:hasRole name="saleman">

        $("#questionnaireForm").validate({
            rules: {
                chinese: {
                    required: true
                },
                qq: {
                    required: true
                },
                cellphone: {
                    required: true
                }
            },
            messages: {
                chinese: {
                    required: "&nbsp;* 中文名不能为空！"
                },
                qq: {
                    required: "&nbsp;* 联系QQ不能为空！"
                },
                cellphone: {
                    required: "&nbsp;* 联系电话不能为空！"
                }
            },
            errorClass: "errorMessage",
            highlight: function (element) { // hightlight error inputs
                $(element).parent().parent().css("border", "solid 2px #ff9900");
            },
            errorElement: "div",
            success: function (label) {
                $(label).parent().css("border", "solid 2px #f7f7f7");
                label.remove();
            },
            errorPlacement: function (error, element) {                             //错误信息位置设置方法
                element.parent().after(error);
            },
            submitHandler: function (form) {

                $(form).ajaxSubmit({
                    success: function (json) {
                        if (json.success) {
                            $("#questionnairePart").load("${ctx}/order/questionnaireDetail/${questionnaire.questionnaireId}");
                        }

                        bootbox.dialog({
                            message: "<span class='bigger-110'>" + json.message + "</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {

                                    }
                                }
                            }
                        });
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>错误代码" + XMLHttpRequest.status + "，请联系管理员！</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });

        $("#update").bind("click", function () {
            $("#questionnaireForm").submit();
        });
        </shiro:hasRole>
    });

    <shiro:lacksRole name="saleman">
    $("#divQuestion input,#divQuestion textarea").attr("disabled", "disabled");
    </shiro:lacksRole>

    <shiro:hasRole name="saleman">
    $("#picture").bind("change", function () {
        if (picCheck("#picture")) {
            $("#fileUpload").ajaxSubmit({
                success: function (json) {
                    if (json.success) {
                        $("#picture").parent().parent().before('<li class="prelative"><img class="width-100" src="' + json.message + '"><i onclick="deletePicture(this)" class="closePicIcon"></i></li>');
                        $("#attachmentUrlDiv").append('<input type="hidden" value="' + json.message + '" name="attachmentUrl"/>')
                    } else {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>" + json.message + "</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {

                                    }
                                }
                            }
                        });
                    }
                    $("#picture").val("");

                },
                error: function (XMLHttpRequest, errorThrown) {
                    bootbox.dialog({
                        message: "<span class='bigger-110'>错误代码" + XMLHttpRequest.status + "，请联系管理员！</span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {
                                }
                            }
                        }
                    });
                }
            });
        }
    })
    function picCheck(selector) {

        var fileName = $(selector).val();

        if (fileName != null && fileName != "") {
            var fileExt = fileName.substr(fileName.lastIndexOf(".")).toLowerCase();
            var supportFile = new Array(".jpg", ".gif", ".bmp", ".png", ".jpeg");

            //lastIndexOf如果没有搜索到则返回为-1
            if ($.inArray(fileExt, supportFile) == -1) {
                var file = $(selector);
                file.val("");
                bootbox.dialog({
                    message: "<span class='bigger-110'>文件类型不合法,只能是 jpg、gif、bmp、png、jpeg 类型！</span>",
                    buttons: {
                        "success": {
                            "label": "<i class='icon-ok'></i> 确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                            }
                        }
                    }
                });
                return false;
            } else {
                return true;
            }

        }
    }

    function deletePicture(selector) {
        $(selector).parent().remove();
        $('[name=attachmentUrl][value="' + $(selector).parent().find("img").attr("src") + '"]').remove();
    }
    </shiro:hasRole>
</script>

</body>
</html>

