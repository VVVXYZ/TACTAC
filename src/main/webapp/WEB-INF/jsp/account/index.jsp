<%@ page import="com.trio.breakFast.enums.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>帐号管理</title>

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
                <div class="title-bar">帐号管理</div>
                <div class="m-l-15">
                    <button class="btn btn-primary btn-sm" type="button" id="addSaleman">
                        <span class="glyphicon glyphicon-plus bigger-110">添加业务员</span>
                    </button>
                    <button class="btn btn-primary btn-sm" type="button" id="addDesigner">
                        <span class="glyphicon glyphicon-plus bigger-110">添加设计师</span>
                    </button>
                </div>
                <div class="space-6"></div>
                <div class="col-md-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div id="tableDiv">
                        <!--This is the table.-->
                        <table id="grid-table"></table>
                        <!--This is the pager.-->
                        <div id="grid-pager"></div>
                    </div>

                    <script type="text/javascript">
                        var $path_base = "/";//this will be used in gritter alerts containing images
                    </script>

                    <!-- PAGE CONTENT ENDS -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->
    <%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</div>
<!-- /.main-container -->
<div class="modal fade" id="addAccount" tabindex="-1" role="dialog"
     aria-labelledby="modal-title" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="modal-title"></h4>
            </div>
            <form id="addAccountForm" method="post" action="" role="form" class="form-horizontal">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>

                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">姓名</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入姓名">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">
                        确定
                    </button>
                    <button id="reset" type="reset" class="btn btn-default">重置
                    </button>

                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
<div class="modal fade" id="resetPassword" tabindex="-1" role="dialog"
     aria-labelledby="modal-title" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" >密码重置</h4>
            </div>
            <form id="resetPasswordForm" method="post"  role="form" class="form-horizontal" action="${ctx}/account/resetPassword">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="newPassword" class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-10">
                            <input type="hidden"  id="accountId" name="accountId">
                            <input type="password" class="form-control" id="newPassword" name="newPassword"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">
                        确定
                    </button>
                    <button type="reset" class="btn btn-default">重置
                    </button>

                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
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
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";
        $(grid_selector).jqGrid({
            url: path + "/account/list",
            datatype: "json",
            mtype: "GET",
            height: 'auto',
            colNames: ['编号', '账号', '姓名', '角色', '状态', '最后登录时间', '问卷地址', '操作'],
            colModel: [
                {
                    name: 'accountId',
                    index: 'accountId',
                    width: 20,
                    key: true,
                    hidden:true
                }, /*hidden: true,key: true(将该列作为ID，删除，修改是需要ID的)*/
                {name: 'username', index: 'username', width: 40},
                {
                    name: 'name',
                    index: 'name',
                    width: 40,
                    editable: false
                }, /*class: 该列元素最终会加上改class*/
                {
                    name: 'role',
                    companyName: 'role',
                    width: 40,
                    classes: 'red',
                    resizable: false,
                    formatter: makeRole
                }, /*resizable: 该列尺寸是否能改变*/
                {name: 'locked', index: 'locked', width: 40, formatter: makeURLForLocked},
                {name: 'loginTime', index: 'loginTime', width: 40, formatter: styleDate},
                {name: 'url', index: 'url', width: 40, sortable: false, formatter: makeURL},
                {
                    name: 'operation',
                    index: '',
                    width: 30,
                    sortable: false,
                    align: 'center',
                    formatter: makeURLForOperation
                }/*align: 元素方位*/
            ],
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,

            height: 'auto',
            shrinkToFit: true,
            autowidth: true,

            multiselect: false,

            prmNames: {
                page: "page", // 表示请求页码的参数名称
                rows: "rows", // 表示请求行数的参数名称
                sort: "sort", // 表示用于排序的列名的参数名称
                order: "order", // 表示采用的排序方式的参数名称
                search: "_search", // 表示是否是搜索请求的参数名称
                nd: "nd", // 表示已经发送请求的次数的参数名称
                id: "id", // 表示当在编辑数据模块中发送数据时，使用的id的名称
                oper: "oper", // operation参数名称
                editoper: "edit", // 当在edit模式中提交数据时，操作的名称
                addoper: "add", // 当在add模式中提交数据时，操作的名称
                deloper: "del", // 当在delete模式中提交数据时，操作的名称
                subgridid: "id", // 当点击以载入数据到子表时，传递的数据名称
                npage: null,
                totalrows: "totalrecords" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
            },
            jsonReader: {
                root: "dataList",//json中代表实际模型数据的入口
                total: "totalPages",//json中代表页码总数的数据
                page: "page",//json中代表当前页码的数据
                records: "totalRows",//json中代表数据行总数的数据
                repeatitems: false
            },
            onPaging: function () {

            },
            editurl: path + "/account/delete", //导航栏的所有默认按钮操作（增删改查）都传递到editUrl指定的控制器处理，在控制器里判断操作的类型。

            loadComplete: function () {
                var table = this;
                setTimeout(function () {
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            }
        });

        jQuery(grid_selector).jqGrid('navGrid', pager_selector,
                {
                    //navbar options
                    edit: false,
                    editicon: 'icon-pencil blue',
                    add: false,
                    addicon: 'icon-plus-sign purple',
                    del: false,
                    delicon: 'icon-trash red',
                    search: false,
                    searchicon: 'icon-search orange',
                    refresh: true,
                    refreshicon: 'icon-refresh green',
                    view: false,
                    viewicon: 'icon-zoom-in grey'
                },
                {
                    //edit record form
                    //closeAfterEdit: true,
                    recreateForm: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_edit_form(form);
                    }
                },
                {
                    //new record form
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_edit_form(form);
                    }
                },
                {
                    //delete record form
                    recreateForm: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        if (form.data('styled')) return false;

                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_delete_form(form);

                        form.data('styled', true);
                    },
                    onClick: function (e) {

                    }
                },
                {
                    //search form
                    recreateForm: true,
                    afterShowSearch: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                        style_search_form(form);
                    },
                    afterRedraw: function () {
                        style_search_filters($(this));
                    },
                    multipleSearch: true
                    /**
                     multipleGroup:true,
                     showQuery: true
                     */
                },
                {
                    //view record form
                    recreateForm: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                    }
                }
        );

        //格式化日期至秒
        function styleDate(cellvalue, options, rowObject) {
            if (cellvalue == "" || cellvalue == null)
                return "";

            var myDate = new Date(cellvalue);

            var year = myDate.getFullYear();
            var month = styleTwoBit(myDate.getMonth() + 1);
            var day = styleTwoBit(myDate.getDate());
            var hours = styleTwoBit(myDate.getHours());
            var minutes = styleTwoBit(myDate.getMinutes());
            var seconds = styleTwoBit(myDate.getSeconds());

            return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
        }

        function makeRole(cellvalue, options, rowObject) {
            if (cellvalue == "<%=Role.saleman.name()%>") {
                return "<%=Role.saleman.getName()%>";
            }
            if (cellvalue == "<%=Role.designer.name()%>") {
                return "<%=Role.designer.getName()%>";
            }
            if (cellvalue == "<%=Role.manage.name()%>") {
                return "<%=Role.manage.getName()%>";
            }

            return "出错!";
        }

        function makeURL(cellvalue, options, rowObject) {
            if (rowObject['role'] != "<%=Role.saleman.name()%>") {
                return "";
            }
            return '<a href="${ctx}/questionnaire/' + rowObject["username"] + '">问卷地址</a>';
        }

        function makeURLForLocked(cellvalue, options, rowObject) {
            var urlstring;
            if (cellvalue) {
                urlstring = "<span class=\"label label-sm label-warning\">禁用</span>";
            } else {
                urlstring = "<span class=\"label label-sm label-success\">启用</span>";
            }
            return urlstring;
        }

        function makeURLForOperation(cellvalue, options, rowObject) {
            var urlstring =
                    "<div class=\"btn-group\" id='operationBtns'>" +
                    "<button class=\"btn btn-xs btn-warning\" style=\"margin: 0;margin-left:10px;\"  onclick=\"resetPassword(" + rowObject["accountId"] + ")\"><i class=\"icon-key bigger-120\" style=\"margin: 0;\"></i></button>";
            if (rowObject["locked"]) {
                urlstring += "<button class=\"btn btn-xs btn-success\" style=\"margin: 0;margin-left:10px;\"  onclick=\"unlock(" + rowObject["accountId"] + ")\"><i class=\"icon-trash bigger-120\" style=\"margin: 0;\"></i></button>";
            } else {
                urlstring += "<button class=\"btn btn-xs btn-danger\" style=\"margin: 0;margin-left:10px;\" onclick=\"lock(" + rowObject["accountId"] + ")\"><i class=\"icon-trash bigger-120\" style=\"margin: 0;\"></i></button>";

            }
            urlstring += "</div>";
            return urlstring;
        }

        //日期插件
        $('.date-picker').each(function () {
            $(this).datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
                $(this).prev().focus();
                console.info("dnc");
            });
        });

        $(window).resize(function () {
            $(grid_selector).setGridWidth($("#tableDiv").width());
        });

        $('#addAccount').on('hide.bs.modal', function () {
            $("#reset").click();
        });
    })
    $("#addSaleman").click(function () {
        $('#addAccountForm').attr("action", "${ctx}/account/createSaleman");
        $("#modal-title").text("添加业务员");
        $('#addAccount').modal('show');
    });

    $("#addDesigner").click(function () {
        $('#addAccountForm').attr("action", "${ctx}/account/createDesigner");
        $("#modal-title").text("添加设计师");
        $('#addAccount').modal('show');
    });

    $(function () {
        $("#addAccountForm").validate({
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: "&nbsp;* 用户名不能为空！"
                },
                password: {
                    required: "&nbsp;* 密码不能为空！"
                }
            },
            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
                $(element).closest('.col-sm-1').addClass('has-error'); // set error class to the control group
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
                $(".chosen-choices").css('border-color', '#b5b5b5');
            },
            errorPlacement: function (error, element) {                             //错误信息位置设置方法
                if (element.attr("name") == 'roleIds') {
                    $("#roleIds-error").html(error);
                    $(".chosen-choices").css('border-color', '#f09784');

                } else {
                    error.insertAfter(element);                            //这里的element是录入数据的对象
                }
            },
            submitHandler: function (form) {

                $(form).ajaxSubmit({
                    success: function (json) {
                        if (json.success) {
                            $("#reset").click();
                            $("#grid-table").trigger("reloadGrid");
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
        $("#resetPasswordForm").validate({
            rules: {
                newPassword: {
                    required: true
                }
            },
            messages: {
                newPassword: {
                    required: "&nbsp;* 密码不能为空！"
                }
            },
            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
                $(element).closest('.col-sm-1').addClass('has-error'); // set error class to the control group
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
                $(".chosen-choices").css('border-color', '#b5b5b5');
            },
            errorPlacement: function (error, element) {                             //错误信息位置设置方法
                if (element.attr("name") == 'roleIds') {
                    $("#roleIds-error").html(error);
                    $(".chosen-choices").css('border-color', '#f09784');

                } else {
                    error.insertAfter(element);                            //这里的element是录入数据的对象
                }
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: function (json) {
                        if (json.success) {
                            $('#resetPassword').modal('hide');
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
    });

    function resetPassword(accountId) {
        $("#accountId").val(accountId);
        $("#newPassword").val("");
        $('#resetPassword').modal('show');

    }

    function lock(accountId) {
        $.ajax({
            type: "POST",
            url: "${ctx}/account/lock",
            data: {accountId: accountId},
            dataType: 'json',
            success: function (json) {
                if (json.success) {
                    $("#grid-table").trigger("reloadGrid");
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
    function unlock(accountId) {

        $.ajax({
            type: "POST",
            url: "${ctx}/account/unLock",
            data: {accountId: accountId},
            dataType: 'json',
            success: function (json) {
                if (json.success) {
                    $("#grid-table").trigger("reloadGrid");
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
</script>
</body>
</html>
