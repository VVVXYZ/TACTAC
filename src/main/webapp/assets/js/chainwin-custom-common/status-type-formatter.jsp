<%--
  Created by IntelliJ IDEA.
  User: passerby
  Date: 2016/4/12
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" import="com.trio.breakFast.controller.ipsService.callback.HxErrCode" %>
<%@ page language="java" %>
<script>
    //格式化债权状态状态
    function styleAssStatus(cellvalue) {
        var value = "未知错误";
        if (cellvalue == <%=AssStatusConstants.TRANS_SUCCESS%>){
            value = "<%=AssStatusConstants.toString(AssStatusConstants.TRANS_SUCCESS)%>";
        }else if(cellvalue == <%=AssStatusConstants.REVOKE_TRANS%>){
            value = "<%=AssStatusConstants.toString(AssStatusConstants.REVOKE_TRANS)%>";
        }else if(cellvalue == <%=AssStatusConstants.TRANS_FAIL%>){
            value = "<%=AssStatusConstants.toString(AssStatusConstants.TRANS_FAIL)%>";
        }else if(cellvalue == <%=AssStatusConstants.TRANS_ING%>){
            value = "<%=AssStatusConstants.toString(AssStatusConstants.TRANS_ING)%>";
        }else if(cellvalue == <%=AssStatusConstants.NEW_BUILT%>){
            value = "<%=AssStatusConstants.toString(AssStatusConstants.NEW_BUILT)%>";
        }

        return value
    }
    //格式化投标类型
    function styleBidType(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=BidTypeConstants.DEPOSIT_BID%>){
            value = "<%=BidTypeConstants.toString(BidTypeConstants.DEPOSIT_BID)%>";
        }else if(cellvalue == <%=BidTypeConstants.PLATFORM_BID%>){
            value = "<%=BidTypeConstants.toString(BidTypeConstants.PLATFORM_BID)%>";
        }
        return value
    }
    //格式化账单状态
    function styleBillStatus(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=BillStatusConstants.WELL_BILL%>){
            value = "<%=BillStatusConstants.toString(BillStatusConstants.WELL_BILL)%>";
        }else if(cellvalue == <%=BillStatusConstants.BAD_BILL%>){
            value = "<%=BillStatusConstants.toString(BillStatusConstants.BAD_BILL)%>";
        }else if(cellvalue == <%=BillStatusConstants.LATE_BILL%>){
            value = "<%=BillStatusConstants.toString(BillStatusConstants.LATE_BILL)%>";
        }else if(cellvalue == <%=BillStatusConstants.ADVANCE_NO_PAY%>){
            value = "<%=BillStatusConstants.toString(BillStatusConstants.ADVANCE_NO_PAY)%>";
        }else if(cellvalue == <%=BillStatusConstants.ADVANCE_HAVE_PAY%>){
            value = "<%=BillStatusConstants.toString(BillStatusConstants.ADVANCE_HAVE_PAY)%>";
        }
        return value
    }
    //格式化Boolean为是否
    function styleIsOrFalse(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=BooleanConstants.IS%>){
            value = "<%=BooleanConstants.toString(BooleanConstants.IS)%>";
        }else if(cellvalue == <%=BooleanConstants.NO%>){
            value = "<%=BooleanConstants.toString(BooleanConstants.NO)%>";
        }
        return value
    }
    //格式化Boolean为成功/失败
    function styleSuccessOrFail(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=BooleanConstants.IS%>){
            value = "<%=BooleanConstants.successOrFail(BooleanConstants.IS)%>";
        }else if(cellvalue == <%=BooleanConstants.NO%>){
            value = "<%=BooleanConstants.successOrFail(BooleanConstants.NO)%>";
        }
        return value
    }
    //格式化Boolean为已返还/未返还
    function styleFailureBidReturn(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=BooleanConstants.IS%>){
            value = "<%=BooleanConstants.failureBidReturn(BooleanConstants.IS)%>";
        }else if(cellvalue == <%=BooleanConstants.NO%>){
            value = "<%=BooleanConstants.failureBidReturn(BooleanConstants.NO)%>";
        }
        return value
    }
    //格式化Boolean为发送与否
    function styleIsSend(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=BooleanConstants.IS%>){
            value = "已发送";
        }else if(cellvalue == <%=BooleanConstants.NO%>){
            value = "未发送";
        }
        return value
    }
    //格式化认证状态
    function styleCertificateStatus(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=CertificateStatusConstants.NO_CHECK%>){
            value = "<%=CertificateStatusConstants.toString(CertificateStatusConstants.NO_CHECK)%>";
        }else if(cellvalue == <%=CertificateStatusConstants.PASS%>){
            value = "<%=CertificateStatusConstants.toString(CertificateStatusConstants.PASS)%>";
        }else if(cellvalue == <%=CertificateStatusConstants.FAIL%>){
            value = "<%=CertificateStatusConstants.toString(CertificateStatusConstants.FAIL)%>";
        }
        return value
    }
    //是否有效，如用在项目所在城市、项目类型、合同范本等
    function styleIsEffectiveStatus(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=IsEffectiveStatusConstants.NO_EFFECT%>){
            value = "<%=IsEffectiveStatusConstants.toString(IsEffectiveStatusConstants.NO_EFFECT)%>";
        }else if(cellvalue == <%=IsEffectiveStatusConstants.HAVE_EFFECT%>){
            value = "<%=IsEffectiveStatusConstants.toString(IsEffectiveStatusConstants.HAVE_EFFECT)%>";
        }
        return value
    }

    //格式化
    function styleFundRecordType(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=FundRecordTypeConstants.RECHARGE%>){
            value = "<%=FundRecordTypeConstants.toString(FundRecordTypeConstants.RECHARGE)%>";
        }else if(cellvalue == <%=FundRecordTypeConstants.WITHDRAW%>){
            value = "<%=FundRecordTypeConstants.toString(FundRecordTypeConstants.WITHDRAW)%>";
        }else if(cellvalue == <%=FundRecordTypeConstants.INVEST%>){
            value = "<%=FundRecordTypeConstants.toString(FundRecordTypeConstants.INVEST)%>";
        }else if(cellvalue == <%=FundRecordTypeConstants.FREEZE%>){
            value = "<%=FundRecordTypeConstants.toString(FundRecordTypeConstants.FREEZE)%>";
        } else if(cellvalue == <%=FundRecordTypeConstants.DEDUCT%>){
            value = "<%=FundRecordTypeConstants.toString(FundRecordTypeConstants.DEDUCT)%>";
        }else if(cellvalue == <%=FundRecordTypeConstants.UNFREEZE%>){
            value = "<%=FundRecordTypeConstants.toString(FundRecordTypeConstants.UNFREEZE)%>";
        }else if(cellvalue == <%=FundRecordTypeConstants.INCOME%>){
            value = "<%=FundRecordTypeConstants.toString(FundRecordTypeConstants.INCOME)%>";
        }
        return value
    }
    //格式化
    function styleFundRecordOperatePlatform(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=FundRecordOperatePlatformConstants.ALL%>){
            value = "<%=FundRecordOperatePlatformConstants.toString(FundRecordOperatePlatformConstants.ALL)%>";
        }else if(cellvalue == <%=FundRecordOperatePlatformConstants.PLATFORM%>){
            value = "<%=FundRecordOperatePlatformConstants.toString(FundRecordOperatePlatformConstants.PLATFORM)%>";
        }else if(cellvalue == <%=FundRecordOperatePlatformConstants.IPS%>){
            value = "<%=FundRecordOperatePlatformConstants.toString(FundRecordOperatePlatformConstants.IPS)%>";
        }
        return value
    }

    //格式化贷款状态/标的状态
    function styleLoanStatus(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=LoanStatusConstants.TO_BEGIN%>){
            value = "<%=LoanStatusConstants.toString(LoanStatusConstants.TO_BEGIN)%>";
        }else if(cellvalue == <%=LoanStatusConstants.BID_ING%>){
            value = "<%=LoanStatusConstants.toString(LoanStatusConstants.BID_ING)%>";
        }else if(cellvalue == <%=LoanStatusConstants.FULL_BID%>){
            value = "<%=LoanStatusConstants.toString(LoanStatusConstants.FULL_BID)%>";
        }else if(cellvalue == <%=LoanStatusConstants.FAIL_BID%>){
            value = "<%=LoanStatusConstants.toString(LoanStatusConstants.FAIL_BID)%>";
        } else if(cellvalue == <%=LoanStatusConstants.REPAY_ING%>){
            value = "<%=LoanStatusConstants.toString(LoanStatusConstants.REPAY_ING)%>";
        }else if(cellvalue == <%=LoanStatusConstants.REPAY_FINISH%>){
            value = "<%=LoanStatusConstants.toString(LoanStatusConstants.REPAY_FINISH)%>";
        }else if(cellvalue == <%=LoanStatusConstants.AHEAD_REPAY%>){
            value = "<%=LoanStatusConstants.toString(LoanStatusConstants.AHEAD_REPAY)%>";
        }else if(cellvalue == <%=LoanStatusConstants.BAD_DEBT%>){
            value = "<%=LoanStatusConstants.toString(LoanStatusConstants.BAD_DEBT)%>";
        }
        return value
    }

    //格式化日期单位
    function stylePeriod(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=PeriodConstants.DAY%>){
            value = "<%=PeriodConstants.toString(PeriodConstants.DAY)%>";
        }else if(cellvalue == <%=PeriodConstants.MONTH%>){
            value = "<%=PeriodConstants.toString(PeriodConstants.MONTH)%>";
        }
        return value
    }

    //格式化
    function styleRechargeStatus(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=RechargeStatusConstants.ANGENCY_FUND%>){
            value = "<%=RechargeStatusConstants.toString(RechargeStatusConstants.ANGENCY_FUND)%>";
        }else if(cellvalue == <%=RechargeStatusConstants.ALREADY_FUND%>){
            value = "<%=RechargeStatusConstants.toString(RechargeStatusConstants.ALREADY_FUND)%>";
        }else if(cellvalue == <%=RechargeStatusConstants.REFUSE_FUND%>){
            value = "<%=RechargeStatusConstants.toString(RechargeStatusConstants.REFUSE_FUND)%>";
        }else if(cellvalue == <%=RechargeStatusConstants.SUCCESS_FUND%>){
            value = "<%=RechargeStatusConstants.toString(RechargeStatusConstants.SUCCESS_FUND)%>";
        } else if(cellvalue == <%=RechargeStatusConstants.SUCCESS_REFUSE%>){
            value = "<%=RechargeStatusConstants.toString(RechargeStatusConstants.SUCCESS_REFUSE)%>";
        }
        return value
    }
    //格式化还款状态
    function styleRepayStatus(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=RepayStatusConstants.NO_REPAY%>){
            value = "<%=RepayStatusConstants.toString(RepayStatusConstants.NO_REPAY)%>";
        }else if(cellvalue == <%=RepayStatusConstants.AHEAD_REPAY%>){
            value = "<%=RepayStatusConstants.toString(RepayStatusConstants.AHEAD_REPAY)%>";
        }else if(cellvalue == <%=RepayStatusConstants.ON_TIME_REPAY%>){
            value = "<%=RepayStatusConstants.toString(RepayStatusConstants.ON_TIME_REPAY)%>";
        }else if(cellvalue == <%=RepayStatusConstants.LATE_REPAY%>){
            value = "<%=RepayStatusConstants.toString(RepayStatusConstants.LATE_REPAY)%>";
        } else if(cellvalue == <%=RepayStatusConstants.SEVERE_LATE%>){
            value = "<%=RepayStatusConstants.toString(RepayStatusConstants.SEVERE_LATE)%>";
        }
        return value
    }

    //格式化还款类型
    function styleRepayType(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=RepayTypeConstants.EQUAL_CAPITAL_INTEREST%>){
            value = "<%=RepayTypeConstants.toString(RepayTypeConstants.EQUAL_CAPITAL_INTEREST)%>";
        }else if(cellvalue == <%=RepayTypeConstants.INTEREST_PAYMENT%>){
            value = "<%=RepayTypeConstants.toString(RepayTypeConstants.INTEREST_PAYMENT)%>";
        }else if(cellvalue == <%=RepayTypeConstants.EXPIRE_RETURN_CAPITAL_INTEREST%>){
            value = "<%=RepayTypeConstants.toString(RepayTypeConstants.EXPIRE_RETURN_CAPITAL_INTEREST)%>";
        }
        return value
    }

    //格式化首页图片位置
    function styleHomepageImagePosition(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=HomepageImagePositionConstants.ON_APP%>){
            value = "<%=HomepageImagePositionConstants.toString(HomepageImagePositionConstants.ON_APP)%>";
        }else if(cellvalue == <%=HomepageImagePositionConstants.ON_WEB%>){
            value = "<%=HomepageImagePositionConstants.toString(HomepageImagePositionConstants.ON_WEB)%>";
        }
        return value
    }

    //格式化是环讯支持或平台支持的银行
    function styleIsHxSupportiveBank(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=IsHxSupportiveBankConstants.NO%>){
            value = "<%=IsHxSupportiveBankConstants.toString(IsHxSupportiveBankConstants.NO)%>";
        }else if(cellvalue == <%=IsHxSupportiveBankConstants.YES%>){
            value = "<%=IsHxSupportiveBankConstants.toString(IsHxSupportiveBankConstants.YES)%>";
        }
        return value
    }
    //格式化支付接口之手续费类型
    function stylePayApiFeeType(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=PayApiFeeTypeConstants.USER_PAID%>){
            value = "<%=PayApiFeeTypeConstants.toString(PayApiFeeTypeConstants.USER_PAID)%>";
        }else if(cellvalue == <%=PayApiFeeTypeConstants.PLATFORM_PAID%>){
            value = "<%=PayApiFeeTypeConstants.toString(PayApiFeeTypeConstants.PLATFORM_PAID)%>";
        }
        return value
    }
    //格式化支付接口之接口类型
    function stylePayApiInterfaceType(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=PayApiInterfaceTypeConstants.PLATFORM_INTERFACE%>){
            value = "<%=PayApiInterfaceTypeConstants.toString(PayApiInterfaceTypeConstants.PLATFORM_INTERFACE)%>";
        }else if(cellvalue == <%=PayApiInterfaceTypeConstants.THIRD_PARTY_INTERFACE%>){
            value = "<%=PayApiInterfaceTypeConstants.toString(PayApiInterfaceTypeConstants.THIRD_PARTY_INTERFACE)%>";
        }
        return value
    }
    //格式化支付接口之支付类型
    function stylePayApiPayType(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=PayApiPayTypeConstants.OFFLINE%>){
            value = "<%=PayApiPayTypeConstants.toString(PayApiPayTypeConstants.OFFLINE)%>";
        }else if(cellvalue == <%=PayApiPayTypeConstants.ONLINE%>){
            value = "<%=PayApiPayTypeConstants.toString(PayApiPayTypeConstants.ONLINE)%>";
        }
        return value
    }
    //格式化短信邮件设置之消息模板类型
    function styleMessageTemplateType(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=MessageTemplateTypeConstants.SHORT_MESSAGE%>){
            value = "<%=MessageTemplateTypeConstants.toString(MessageTemplateTypeConstants.SHORT_MESSAGE)%>";
        }else if(cellvalue == <%=MessageTemplateTypeConstants.EMAIL%>){
            value = "<%=MessageTemplateTypeConstants.toString(MessageTemplateTypeConstants.EMAIL)%>";
        }
        return value
    }
    //格式化短信邮件设置之消息发送状态
    function styleMessageSendingStatue(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=MessageSendingStatueConstants.SENT%>){
            value = "<%=MessageSendingStatueConstants.toString(MessageSendingStatueConstants.SENT)%>";
        }else if(cellvalue == <%=MessageSendingStatueConstants.UNSENT%>){
            value = "<%=MessageSendingStatueConstants.toString(MessageSendingStatueConstants.UNSENT)%>";
        }
        return value
    }
    //格式化帮助中心配置之文章类型
    function styleHelpCenterArticleType(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=HelpCenterArticleTypeConstants.ANNOUNCEMENT%>){
            value = "<%=HelpCenterArticleTypeConstants.toString(HelpCenterArticleTypeConstants.ANNOUNCEMENT)%>";
        }else if(cellvalue == <%=HelpCenterArticleTypeConstants.HELP_CENTER_DOCUMENT%>){
            value = "<%=HelpCenterArticleTypeConstants.toString(HelpCenterArticleTypeConstants.HELP_CENTER_DOCUMENT)%>";
        }
        return value
    }
    //格式化消息管理之私信用户阅读状态
    function stylePrivateMessageReadStatus(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=PrivateMessageReadStatusConstants.READ%>){
            value = "<%=PrivateMessageReadStatusConstants.toString(PrivateMessageReadStatusConstants.READ)%>";
        }else if(cellvalue == <%=PrivateMessageReadStatusConstants.UNREAD%>){
            value = "<%=PrivateMessageReadStatusConstants.toString(PrivateMessageReadStatusConstants.UNREAD)%>";
        }
        return value
    }
    //格式化环迅标的状态
    function styleHxBidStatus(cellvalue) {
        var value = "未知错误";
        if(cellvalue=="<%=HxErrCode.BID_CREATE%>"){
            value = "标的新增";
        }else if(cellvalue=="<%=HxErrCode.BID_COLLECTING%>"){
            value = "标的募集中";
        }else if(cellvalue=="<%=HxErrCode.BID_PROCESSING%>"){
            value = "标的进行中";
        }else if(cellvalue=="<%=HxErrCode.BID_PROCESS_FINISHING%>"){
            value = "标的结束处理中";
        }else if(cellvalue=="<%=HxErrCode.BID_FINISH%>"){
            value = "标的结束";
        }else if(cellvalue=="<%=HxErrCode.BID_FAIL%>"){
            value = "标的失败";
        }
        return value
    }


    //格式化平台统计之网站收益记录之类型
    function styleWebsitePaymentType(cellvalue) {
        var value = "未知错误";
        if(cellvalue == <%=DefaultValueConstants.WEBSITEINCOME%>){
            value = "<%=DefaultValueConstants.webIETOString(DefaultValueConstants.WEBSITEINCOME)%>";
        }else if(cellvalue == <%=DefaultValueConstants.WEBSITEEXPENDITURE%>){
            value = "<%=DefaultValueConstants.webIETOString(DefaultValueConstants.WEBSITEEXPENDITURE)%>";
        }
        return value
    }
</script>