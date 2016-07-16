
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html >

<html >
<head lang="en">
    <meta charset="utf-8">
    <title>提交完成</title>
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/assets/css/jqmobo.css?v=5" />
    <script type="text/javascript">
        var isWeiXin=0;
    </script>

    <script src="${ctx}/assets/js/mobile/jquery-1.10.1.min.js"></script>

</head>
<body style="height: 100%;">

<div class="main">

<div id="toptitle" >
    <h1 class="htitle">
        提交完成</h1>
</div>

<div style=" margin:20px 0 10px;">
    <div class="logo"></div>
    <div class="formfield" style="padding-bottom:30px;border: 0;">
        <div class="description" id="divdsc">
            您好，我们已经收到您的logo需求，请尽快联系官方客服QQ。方便后期沟通修改。

            <br/>
            <br/>

        </div>



<div style="color: #2695D0;text-align: center;">
    如果您未付款，请尽快在猪八戒下单付款。
</div>






    </div>



    <div id="userdialog"  style="background: #FFF;width:80%; max-width: 500px; margin: 20px auto; display:none;">

    </div>


</div>


<div style=" padding-right:20px;">
    <!--<a href="urlreport.aspx?url=3741019&cm=1" style="float: right; color: #aaa;-->
            <!--font-size: 13px;" class="reportto">  技术支持：微码科技&nbsp;&nbsp; QQ:365703546</a>-->
    <div style="clear: both;">
    </div>
</div>
</div>

<div id="dialog" style="display:none;">

</div>

<script>
    function postHeight() {
        if (window == window.top)
            return;
        try {
            var pmp = parent.postMessage ? parent : parent.document.postMessage ? parent.document : null;
            if (pmp != null)
                return pmp.postMessage("heightChanged," + ($("body").height() + 20), "*");
        } catch (e) {
        }
    }
    $(function(){
        if (window !=window.top)
            $("body").css("background","#fff");
        postHeight();
    });
</script>

</body>
</html>
