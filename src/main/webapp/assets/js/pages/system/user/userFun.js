$(function(){
    var path = window.location.pathname.split("/")[0];
    $("#msgbox").on("mouseover", function (e) {
        $("#msgbox").fadeOut("slow");
    });
    $("div").on("mouseover", function (e) {
        $("#msgbox").fadeOut(2500);
    });

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    $(grid_selector).jqGrid({
        url: path + "/user/list",
        datatype: "json",
        mtype: "GET",
        height: 332,
        colNames: ['ID', '用户名', '角色', '所属部门', '联系号码', '用户状态', '编辑'],
        colModel: [
            {name: 'id', index: 'id', width: 20, sorttype: "int", editable: false, sortable: false},
            {name: 'username', index: 'username', width: 30, editable: true,
                formatter: "showlink", formatoptions: {baseLinkUrl: path + "/user/", idName: "id"}},
            {name: 'roleNames', index: 'roleNames', width: 90, editable: false},
            {name: 'orgName', index: 'orgName', width: 40, sorttype: "int", editable: false},
            {name: 'tel', index: 'tel', width: 26, sorttype: "int", editable: true},
            {name: 'locked', index: 'locked', width: 20, sorttype: "int", editable: true, formatter: makeURLForLocked,
                edittype: 'select', editoptions: {value: 'false:账号可用;true:封禁账号'}},
            {name: 'operation', index: '', width: 110, fixed: true, sortable: false, resize: false, formatter: makeURLForOperation}
        ],
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        altRows: true,
        //toppager: true,
        multiselect: false,
        //multikey: "ctrlKey",
        multiboxonly: true,
        prmNames: {
            page: "page", // 表示请求页码的参数名称
            rows: "rows", // 表示请求行数的参数名称
            sort: "id", // 表示用于排序的列名的参数名称
            order: "sord", // 表示采用的排序方式的参数名称
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
            root: "dataObj",//json中代表实际模型数据的入口
            total: "totalpages",//json中代表页码总数的数据
            page: "currentpage",//json中代表当前页码的数据
            records: "totalrecords",//json中代表数据行总数的数据
            repeatitems: false
        },
        onPaging: function () {

        },
        editurl: path + "/user/inLineModify",//nothing is saved
        caption: "管理员信息",
        autowidth: true,
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        }
    });
    function makeURLForLocked(cellvalue, options, rowObject) {
        //cellvalue:要格式化的值，就是原本单元格的值，放置：<a>cellvalue</a>
        //options["rowId"]:
        //rowObject:当前行的值，可以这样取值：rowObject["id"]，rowObject["username"]，rowObject["pwd"]

        //alert(rowObject["test"]);//此处的值为返回的json中对应的值。
        //alert(options["rowId"]);
        //alert(options["colModel"]["name"]);//此处返回的就是“filename”
        var urlstring;
        if (rowObject["locked"] == true) {
            urlstring = "<span class=\"label label-sm label-warning\">已封禁</span>";
        } else {
            urlstring = "<span class=\"label label-sm label-success\">状态正常</span>";
        }
        return urlstring;
    }

    function makeURLForOperation(cellvalue, options, rowObject) {
        var urlstring =
            "<div class=\"btn-group\" id='operationBtns'>" +
            "<button class=\"btn btn-xs btn-info\" style=\"margin: 0;\" id=\"modRoleBtn\" onclick=\"javascrtpt:window.location.href='" + path + "/user/update/" + rowObject["id"] + "'\"><i class=\"icon-edit bigger-120\" style=\"margin: 0;\"></i></button>" +
            "<button class=\"btn btn-xs btn-warning\" style=\"margin: 0;margin-left:10px;\" id=\"changePwd\" onclick=\"javascrtpt:window.location.href='" + path + "/user/changePassword/" + rowObject["id"] + "'\"><i class=\"icon-key bigger-120\" style=\"margin: 0;\"></i></button>" +
            "<button class=\"btn btn-xs btn-danger\" style=\"margin: 0;margin-left:10px;\" id=\"delRoleBtn\" onclick=\"javascrtpt:window.location.href='" + path + "/user/delete/" + rowObject["id"] + "'\"><i class=\"icon-trash bigger-120\" style=\"margin: 0;\"></i></button>" +
            "</div>";
        return urlstring;
    }

    //switch element when editing inline
    function aceSwitch(cellvalue, options, cell) {
        setTimeout(function () {
            $(cell).find('input[type=checkbox]')
                .wrap('<label class="inline" />')
                .addClass('ace ace-switch ace-switch-5')
                .after('<span class="lbl"></span>');
        }, 0);
    }

    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {     //navbar options
            edit: false,
            add: false,
            del: false,
            search: false,
            refresh: false,
            view: false
        }
    );
    //replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement =
        {
            'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev': 'icon-angle-left bigger-140',
            'ui-icon-seek-next': 'icon-angle-right bigger-140',
            'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
        };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container: 'body'});
        $(table).find('.ui-pg-div').tooltip({container: 'body'});
    }

    //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
    $(window).resize(function () {
        $(grid_selector).setGridWidth($("#tableDiv").width());
    });
});