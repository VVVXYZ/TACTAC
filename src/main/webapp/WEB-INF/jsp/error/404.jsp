<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>
        Oops! 页面没有找到！
    </title>
    <meta name="description" content="404 Error Page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
</head>
<body>
    <div class="well center">
        <div class="space-32"></div>
        <h1 class="grey lighter smaller">
            <span class="blue bigger-125">
                <i class="icon-sitemap"></i>
                404
            </span>
            该页面没有找到！<br/>
        </h1>
        <div class="space-32"></div>
        <hr />
        <h3 class="lighter smaller">您所要查看的页面不存在</h3>
        <hr/>
        <h4 class="lighter smaller">点击“返回”回到上一页</h4>
        <h4 class="lighter smaller">点击“主页”回到主页面</h4>
        <hr />
        <div class="space-32"></div>
        <div class="center">
            <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回上一页 </a>
            <a href="${ctx}/" class="btn btn-primary"> <i class="icon-dashboard"> </i> 返回主页面 </a>
        </div>
        <div class="space-32"></div>
        <div class="space-32"></div>
        <div class="space-32"></div>
        <div class="space-20"></div>
    </div>
    <%--技术支持--%>
    <%@include file="/WEB-INF/jsp/common/tech-support.jsp" %>
</div>
<!-- PAGE CONTENT ENDS -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<!-- inline scripts related to this page -->
</body>
</html>