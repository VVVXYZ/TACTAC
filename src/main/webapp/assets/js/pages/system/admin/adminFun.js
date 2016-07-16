$(function(){
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    $(grid_selector).jqGrid({
        url: path + "/account/list",
        datatype: "json",
        mtype: "GET",
        height: 'auto',
        colNames: ['编号', '账号', '姓名', '角色','状态','最后登录时间', '问卷地址','操作'],
        colModel: [
            {name: 'accountId', index: 'accountId', width: 20,  key: true},/*hidden: true,key: true(将该列作为ID，删除，修改是需要ID的)*/
            {name: 'username', index: 'username', width: 40},
            {name: 'name', index: 'name', width: 40,editable: false,classes:'red', sortable:false},/*class: 该列元素最终会加上改class*/
            {name: 'role', companyName: 'role', width: 40 ,resizable: false},/*resizable: 该列尺寸是否能改变*/
            {name: 'locked', index: 'locked', width: 40, formatter:makeURLForLocked},
            {name: 'loginTime', index: 'loginTime', width: 40},
            {name: 'url', index: 'url', width: 40, sortable: false},
            {name: 'operation', index: '', width: 30, sortable:false, align: 'center', formatter: makeURLForOperation}/*align: 元素方位*/
        ],
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        altRows: true,

        height: 'auto',
        shrinkToFit: true,
        autowidth: true,

        multiselect: true,

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
        }
    });

    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            //navbar options
            edit: false,
            editicon: 'icon-pencil blue',
            add: false,
            addicon: 'icon-plus-sign purple',
            del: true,
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
                alert(1);
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

    function makeURLForLocked(cellvalue, options, rowObject) {
        //cellvalue:要格式化的值，就是原本单元格的值，放置：<a>cellvalue</a>
        //options["rowId"]:
        //rowObject:当前行的值，可以这样取值：rowObject["id"]，rowObject["username"]，rowObject["pwd"]

        //alert(rowObject["test"]);//此处的值为返回的json中对应的值。
        //alert(options["rowId"]);
        //alert(options["colModel"]["name"]);//此处返回的就是“filename”
        var urlstring;
        if (rowObject["lockStatus"] == true) {
            urlstring = "<span class=\"label label-sm label-warning\">已封禁</span>";
        } else {
            urlstring = "<span class=\"label label-sm label-success\">状态正常</span>";
        }
        return urlstring;
    }

    function makeURLForOperation(cellvalue, options, rowObject) {
        var urlstring =
            "<div class=\"btn-group\" id='operationBtns'>" +
            "<button id=\"delBtn_" + rowObject["id"] + "\" class=\"btn btn-xs btn-info\" style=\"margin: 0;\" onclick=\"javascrtpt:window.location.href='" + path + "/admin/systemSetting/admin/update/" + rowObject["id"] + "'\"><i class=\"icon-edit bigger-120\" style=\"margin: 0;\"></i></button>" +
            "<button class=\"btn btn-xs btn-warning\" style=\"margin: 0;margin-left:10px;\" id=\"changePwd\" onclick=\"javascrtpt:window.location.href='" + path + "/admin/systemSetting/admin/changePassword/" + rowObject["id"] + "'\"><i class=\"icon-key bigger-120\" style=\"margin: 0;\"></i></button>" +
            "<button class=\"btn btn-xs btn-danger\" style=\"margin: 0;margin-left:10px;\" id=\"delRoleBtn\" onclick=\"javascrtpt:window.location.href='" + path + "/admin/systemSetting/admin/delete/" + rowObject["id"] + "'\"><i class=\"icon-trash bigger-120\" style=\"margin: 0;\"></i></button>" +
            "</div>";
        return urlstring;
    }

    //日期插件
    $('.date-picker').each(function(){
        $(this).datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
            $(this).prev().focus();
            console.info("dnc");
        });
    });

    $(window).resize(function () {
        $(grid_selector).setGridWidth($("#tableDiv").width());
    });
});