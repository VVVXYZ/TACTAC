<%@ page import="com.trio.vmalogo.enums.Role" %>
<%@ page import="com.trio.vmalogo.enums.OrderStatus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>订单管理</title>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
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
                <div class="title-bar">订单管理
                    <div class="pull-right">
                        <button class="btn btn-link" onclick="window.open('${ctx}/order.xsl')">
                            <span class="white"><big>导出Excel</big></span>
                        </button>
                    </div>
                </div>

                <div class="space-6"></div>

                <div class="col-md-12">
                    <div class="well fillet-search">
                        <div class="row">
                            <form class="form-horizontal" role="form" id="searchForm">
                                <div class="form-group col-md-4">
                                    <label for="questionnaire.qq" class="col-md-4 search-label">QQ：</label>
                                    <div class="col-md-8">
                                        <input op="cn" type="text" class="form-control input-sm" id="questionnaire.qq" name="questionnare.qq" placeholder="请输入qq">
                                    </div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="questionnaire.cellphone" class="col-md-4 search-label">手机号：</label>
                                    <div class="col-md-8">
                                        <input op="cn" type="text" class="form-control input-sm" id="questionnaire.cellphone" name="questionnaire.cellphone" placeholder="请输入手机号">
                                    </div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="zbj" class="col-md-4 search-label">猪八戒id：</label>
                                    <div class="col-md-8">
                                        <input op="cn" type="text" class="form-control input-sm" id="zbj" name="zbj" placeholder="请输入猪八戒id">
                                    </div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="saleman.name" class="col-md-4 search-label">业务员：</label>
                                    <div class="col-md-8">
                                        <input op="cn" type="text" class="form-control input-sm" id="saleman.name" name="saleman.name" placeholder="请输入业务员">
                                    </div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="designer.name" class="col-md-4 search-label">设计师：</label>
                                    <div class="col-md-8">
                                        <input op="cn" type="text" class="form-control input-sm" id="designer.name" name="designer.name" placeholder="请输入设计师">
                                    </div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="questionnaire.chinese" class="col-md-4 search-label">logo中文名：</label>

                                    <div class="col-md-8">
                                        <input op="cn" type="text" class="form-control input-sm" id="questionnaire.chinese"
                                               name="questionnaire.chinese" placeholder="请输入logo中文名">
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="datepicker" class="col-md-3 search-label">提交时间：</label>
                                    <div class="input-group col-md-9" id="datepicker">
                                        <input type="text" class="form-control date-picker" name="submitTime"  op="bw"/>
                                        <span class="input-group-addon">至</span>
                                        <input type="text" class="form-control date-picker" name="submitTime" op="ew"/>
                                    </div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="designer.name" class="col-md-4 search-label"></label>
                                    <button class="btn btn-sm btn-bold btn-yellow" type="submit">
                                        <span class="ace-icon glyphicon glyphicon-search"></span>
                                        搜索
                                    </button>
                                    <button class="btn btn-sm btn-bold btn-yellow" type="reset">
                                        <span class="ace-icon glyphicon glyphicon glyphicon-refresh"></span>
                                        重置
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>
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
<!-- /.modal -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<!-- page specific plugin scripts -->
<script src="${ctx}/assets/dep/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/dep/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<!-- page custom common scripts -->
<script src="${ctx}/assets/js/chainwin-custom-common/date-formatter.js"></script>
<script src="${ctx}/assets/js/chainwin-custom-common/jqgrid-nav-custom-def.js"></script>
<script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.helper.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.form.js"></script>
<script>
    $(function () {
        //日期插件
        $('.date-picker').each(function () {
            $(this).datepicker({
                autoclose: true,
                language: 'zh-CN',
                format: 'yyyy-mm-dd'
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
                console.info("dnc");
            });
        });
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";
        $(grid_selector).jqGrid({
            sortname : "submitTime",
            sortorder : "desc",
            url: path + "/order/list",
            datatype: "json",
            mtype: "GET",
            height: 'auto',
            colNames: ['编号', 'logo名', '业务员', '设计师','客户QQ', '客户手机号', '猪八戒id', '当前状态', '操作时间'],
            colModel: [

                {
                    name: 'businessOrderId',
                    index: 'businessOrderId',
                    width: 20,
                    key: true,
                    hidden:true
                }, /*hidden: true,key: true(将该列作为ID，删除，修改是需要ID的)*/
                {name: 'chinese', index: 'questionnaire.chinese', width: 30},
                {name: 'salemanName', index: 'saleman.name', width: 30},
                {
                    name: 'designerName',
                    companyName: 'designer.name',
                    width: 30,
                    resizable: false
                }, /*resizable: 该列尺寸是否能改变*/

                {name: 'qq', index: 'questionnaire.qq', width: 30, formatter: styleQq},
                {name: 'cellphone', index: 'questionnaire.cellphone', width: 30, formatter: styleCellPhone},
                {name: 'zbj', index: 'zbj', width: 30, sortable: false},
                {
                    name: 'status',
                    index: 'status',
                    width: 30,
                    formatter: makeStatus
                }/*align: 元素方位*/,
                {
                    name: 'operateTime',
                    index: 'operateTime',
                    width: 40,
                    editable: false,
                    formatter: styleDate
                }
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

        function styleQq(cellvalue, options, rowObject) {
            if(rowObject["qqBlack"]){
                return "<span class='red'>"+cellvalue+"</span>"
            }else{
                return cellvalue;
            }
        }

        function styleCellPhone(cellvalue, options, rowObject) {
            if(rowObject["cellphoneBlack"]){
                return "<span class='red'>"+cellvalue+"</span>"
            }else{
                return cellvalue;
            }
        }
        function makeStatus(cellvalue, options, rowObject) {
            if (cellvalue == "<%=OrderStatus.unpaid.name()%>") {
                return "<%=OrderStatus.unpaid.getName()%>";
            }
            if (cellvalue == "<%=OrderStatus.paid.name()%>") {
                return "<%=OrderStatus.paid.getName()%>";
            }
            if (cellvalue == "<%=OrderStatus.designing.name()%>") {
                return "<%=OrderStatus.designing.getName()%>";
            }
            if (cellvalue == "<%=OrderStatus.designed.name()%>") {
                return "<%=OrderStatus.designed.getName()%>";
            }
            if (cellvalue == "<%=OrderStatus.finished.name()%>") {
                return "<%=OrderStatus.finished.getName()%>";
            }
            if (cellvalue == "<%=OrderStatus.cancel.name()%>") {
                return "<%=OrderStatus.cancel.getName()%>";
            }
            return "出错!";
        }


        $(window).resize(function () {
            $(grid_selector).setGridWidth($("#tableDiv").width());
        });


        simpleSearchRegister(grid_selector, "#searchForm");
    })
</script>
</body>
</html>
