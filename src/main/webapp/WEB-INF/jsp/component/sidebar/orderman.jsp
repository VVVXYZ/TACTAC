<%--
  User: ercha
  Date: 2015/11/15 22:09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<li <c:if test="${navMenu == 'order'}">class="active open"</c:if>>
    <a href="${ctx}/order" class="dropdown-toggle">
        <span class="glyphicon glyphicon-euro"></span>
        <span class="menu-text">
            订单管理
        </span>
    </a>
</li>

