<%--
  Created by IntelliJ IDEA.
  User: lw
  Date: 2016/7/11
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>登录/注册</title>
  <meta name="description" content="Admin login page" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <%--导入头部css--%>
  <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
  <!-- inline styles related to this page -->
  <link href="${ctx}/assets/css/pages/loginAndRegister.css" rel="stylesheet" type="text/css" />

</head>
<body class="login-layout blur-login">
<div class="main-container">
  <div class="main-content">
    <div class="row">
      <div class="col-sm-10 col-sm-offset-1">
        <div class="login-container">
          <div class="center">
            <h1>
              <i class="ace-icon fa fa-leaf green"></i>

              <span class="white" id="id-text2">后台管理平台</span>
            </h1>
            <%--<h4 class="white" id="id-company-text">&copy; <a class="white" href="http://chainwin.com">http://chainwin.com</a></h4>--%>
          </div>

          <div class="space-3"></div>

          <div class="position-relative">
            <div id="login-box" class="login-box visible widget-box no-border">
              <div class="widget-body">
                <div class="widget-main">
                  <h4 class="header blue lighter bigger">
                    <span class="ace-icon glyphicon glyphicon-info-sign blue"></span>
                    登录信息
                  </h4>

                  <div class="space-6"></div>

                  <form class="form" id="login-form">
                    <fieldset>
                      <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="username" id="username" class="form-control" placeholder="用户名" />
															<i class="icon-user"></i>
														</span>
                      </label>

                      <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" id="password" class="form-control" placeholder="密码" />
															<i class="icon-lock"></i>
														</span>
                      </label>

                      <div class="space"></div>

                      <div class="clearfix">
                        <%--<label class="inline">--%>
                        <%--<input type="checkbox" class="ace" />--%>
                        <%--<span class="lbl"> 记住我</span>--%>
                        <%--</label>--%>

                        <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                          <i class="ace-icon fa fa-key"></i>
                          <span class="bigger-110">登录</span>
                        </button>
                      </div>

                      <div class="space-4"></div>
                    </fieldset>
                  </form>

                  <div class="social-or-login center">
                    <span class="bigger-110"></span>
                  </div>

                </div><!-- /.widget-main -->

                <%--<div class="toolbar">--%>
                <%--<div>--%>
                <%--<a href="${ctx}/user/common/getpassword"  class="forgot-password-link">--%>
                <%--<i class="icon-arrow-left"></i>--%>
                <%--忘记密码--%>
                <%--</a>--%>
                <%--<a href="#" data-target="#signup-box" class="user-signup-link" style="float: right ;margin-right: 10px" >--%>
                <%--公司入驻申请--%>
                <%--<i class="icon-arrow-right"></i>--%>
                <%--</a>--%>
                <%--</div>--%>

                <%--</div>--%>
              </div><!-- /.widget-body -->
            </div><!-- /.login-box -->

            <%--技术支持--%>
            <div id="support" class="tech-support-login">
              <%@include file="/WEB-INF/jsp/common/tech-support.jsp" %>
            </div>
          </div><!-- /.position-relative -->
        </div>
      </div><!-- /.col -->
    </div><!-- /.row -->
  </div><!-- /.main-content -->
</div><!-- /.main-container -->
<!-- basic scripts -->

<!--[if IE]>
<script type="text/javascript">
  window.jQuery || document.write("<script src='${ctx}/assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<!--[if !IE]> -->
<script type="text/javascript">
  window.jQuery || document.write("<script src='${ctx}/assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
  //you don't need this, just used for changing background


</script>
<!-- <![endif]-->

<script type="text/javascript">
  var path = "${ctx}";
  if ("ontouchend" in document) document.write("<script src='${ctx}/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script type="text/javascript" src="${ctx}/assets/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<!-- page specific plugin scripts -->
<script type="text/javascript" src="${ctx}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/bootbox.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/pages/login/login.js"></script>
<script type="text/javascript">
  jQuery(function($) {
    $(document).on('click', '.toolbar a[data-target]', function(e) {
      e.preventDefault();
      var target = $(this).data('target');
      $('.widget-box.visible').removeClass('visible');//hide others
      $(target).addClass('visible');//show target
    });
  });
</script>

</body>
</html>
