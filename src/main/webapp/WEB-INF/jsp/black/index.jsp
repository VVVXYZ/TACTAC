<%@ page import="com.trio.vmalogo.enums.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>黑名单管理</title>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css"/>

    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
</head>

<body>
<%--导入头部banner--%>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <%--导入左边导航菜单--%>
    <%@include file="/WEB-INF/jsp/component/sidebar.jsp" %>
    <div class="main-content">
        <div class="page-content">
            <div class="row">
                <div class="title-bar">黑名单管理</div>
                <form id="blackForm" method="post" action="" role="form" class="form-horizontal">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="code" class=" col-sm-offset-2 col-sm-2 control-label">QQ/手机号</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="code" name="code"
                                       placeholder="请输入QQ/手机号">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="center">
                                <button type="button" id="search" class="btn btn-primary">
                                    查找
                                </button>
                                <button type="button" id="put" class="btn btn-primary">
                                    移入
                                </button>
                                <button type="button" id="remove" class="btn btn-primary">
                                    移出
                                </button>
                            </div>
                        </div>
                    </div>



                </form>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->
    <%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</div>
<!-- /.main-container -->
<!-- /.modal -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<!-- page specific plugin scripts -->

<script src="${ctx}/assets/dep/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<!-- page custom common scripts -->
<script src="${ctx}/assets/js/chainwin-custom-common/date-formatter.js"></script>
<script src="${ctx}/assets/js/chainwin-custom-common/jqgrid-nav-custom-def.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.form.js"></script>
<script>
    $(function () {

    })
    $("#search").click(function () {
        $('#blackForm').attr("action", "${ctx}/black/search");
        formSubmit();
    });

    $("#put").click(function () {
        $('#blackForm').attr("action", "${ctx}/black/put");
        formSubmit();
    });

    $("#remove").click(function () {
        $('#blackForm').attr("action", "${ctx}/black/remove");
        formSubmit();
    });

    function formSubmit(){
        $('#blackForm').ajaxSubmit({
            success: function (json) {
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
</script>
</body>
</html>
