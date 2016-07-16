<%@ page import="com.trio.vmalogo.enums.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@ page language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script language="javascript">
    path = "${pageContext.request.contextPath}";
</script>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="navbar-container" id="navbar-container">
        <%--<div class="navbar-header pull-left">--%>
        <%--<a class="navbar-brand" style="padding: 5px">--%>
        <%--<span style="font-family:'楷体','Helvetica Neue Light', 'Lucida Grande', 'Calibri', 'Arial', 'sans-serif';width: 20px;font-size: 20px;word-wrap: break-word;letter-spacing: 5px; display: block;padding-top: 0px">--%>
        <%--卿云金融--%>
        <%--</span>--%>
        <%--</a><!-- /.brand -->--%>
        <%--</div>--%>
        <%--<span style="float: left;margin-top:10px;margin-left: 5px;">--%>
        <%--<img src="${ctx}/assets/img/logo.png" width="70px" height="70px"/>--%>
        <%--</span>--%>
        <shiro:hasRole name="<%=Role.saleman.name()%>">
            <nav class="navbar-header">
                <a class="navbar-title " style="color: white">问卷链接：</a>
                <a href="${ctx}/questionnaire/<shiro:principal/>"
                   class="navbar-title " style="color: white"><%=request.getScheme() + "://" + request.getServerName() %>
                    ${ctx}/questionnaire/<shiro:principal/></a>
            </nav>
        </shiro:hasRole>
        <div class="pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li>
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle" style="background: #438EB9;">
                        <span style="line-height: 25px;" class="user-info">欢迎<shiro:principal/><i
                                class="icon-caret-down"></i></span>
                    </a>
                    <ul class="user-menu dropdown-menu dropdown-menu-left dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="${ctx}/changePassword">
                                <i class="icon-user"></i>
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${ctx}/logout">
                                <i class="icon-off red"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

        <%--<div class="navbar-header pull-right" role="navigation" style="height: 35px">--%>
        <%--<ul class="nav ace-nav">--%>
        <%--<li>--%>
        <%--<a data-toggle="dropdown" href="#" class="dropdown-toggle" style="background: #438EB9;">--%>
        <%--<span style="line-height: 25px;" class="user-info">登入账户：<b>${user.username}</b><i class="icon-caret-down"></i></span>--%>
        <%--</a>--%>
        <%--<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">--%>
        <%--<li>--%>
        <%--<a href="${ctx}/user/changePassword">--%>
        <%--<i class="icon-user"></i>--%>
        <%--修改密码--%>
        <%--</a>--%>
        <%--</li>--%>
        <%--<li class="divider"></li>--%>
        <%--<li>--%>
        <%--<a href="${ctx}/logout">--%>
        <%--<i class="icon-off red"></i>--%>
        <%--退出--%>
        <%--</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</div>--%>
    </div>
    <!-- /.container -->
</div>
<div class="clearfix"></div>