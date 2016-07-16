<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span> </a>
<div class="sidebar responsive" id="sidebar">
    <ul class="nav nav-list">

        <!-- 订单管理 -->
        <%@ include file="/WEB-INF/jsp/component/sidebar/orderman.jsp" %>
        <shiro:hasRole name="<%=Role.manage.name()%>">
        <!-- 黑名单理 -->
        <%@ include file="/WEB-INF/jsp/component/sidebar/black.jsp" %>
        <!-- 帐号设置 -->
        <%@ include file="/WEB-INF/jsp/component/sidebar/account.jsp" %>
        </shiro:hasRole>

    </ul>
</div>