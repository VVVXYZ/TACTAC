<%@ page import="com.trio.breakFast.enums.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>订单详情</title>

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
                <div class="title-bar">订单详情</div>
                <div class="pull-right">
                    <form id="pdfDownload" action="${ctx}/pdf/questionnaire.pdf" method="post">
                        <button type="button" class="btn btn-primary" onclick="perfect()">录入底稿</button>
                        <button type="submit" class="btn btn-primary">另存为pdf</button>

                        <input type="hidden" name="businessOrderId" value="${businessOrder.businessOrderId}"/>

                    </form>
                </div>
                <div class="clearfix"></div>
                <div class="pull-left">
                    <span class="control-label">派单时间:</span>
                    <span class="form-control-static"><fmt:formatDate value="${businessOrder.dispatchTime}"
                                                                      pattern="yyyy年MM月dd日 HH时mm分ss秒"/></span>
                </div>
                <div class="clearfix"></div>
            </div>
            <div id="questionnairePart">

            </div>
            <!-- /.row -->
        </div>
        <!-- /.page-content -->
    </div>

    <div class="modal fade" id="perfect" tabindex="-1" role="dialog"
         aria-labelledby="modal-title" aria-hidden="true">
        <div class="modal-dialog ">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title">录入底稿</h4>
                </div>
                <form id="perfectForm" method="post" action="${ctx}/order/designPerfect" role="form"
                      enctype="multipart/form-data"
                      class="form-horizontal">
                    <div class="modal-body">
                        <input type="hidden" name="businessOrderId" value="${businessOrder.businessOrderId}">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">文件</label>

                            <div class="col-sm-10">
                                <input type="file" class="form-control" id="perfectFile" name="designFile">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">
                                确定
                            </button>
                            <button id="reset" type="reset" class="btn btn-default">重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <!-- /.modal-content -->
        </div>
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
        $("#questionnairePart").load("${ctx}/order/questionnaireDetail/${businessOrder.questionnaire.questionnaireId}");

    });
    function perfect() {
        $("#perfectFile").val("");
        $('#perfect').modal('show');
    }
    $("#perfectForm").validate({
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (json) {
                    if (json.success) {
                        $("#grid-table").trigger("reloadGrid");
                        $('#perfect').modal('hide');
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
</script>
</body>
</html>
