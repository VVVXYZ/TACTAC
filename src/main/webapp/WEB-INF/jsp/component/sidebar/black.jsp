<%--
  User: ercha
  Date: 2015/11/15 22:09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<li <c:if test="${navMenu == 'black'}">class="active open"</c:if>>
    <a href="${ctx}/black" class="dropdown-toggle">
        <span class="glyphicon glyphicon-user"></span>
        <span class="menu-text">
            黑名单管理
        </span>
    </a>
</li>
