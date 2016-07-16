$(function(){
    var path = window.location.pathname.split("/")[0];
    $("#msgbox").on("mouseover", function (e) {
        $("#msgbox").fadeOut("slow");
    });
    $("div").on("mouseover", function (e) {
        $("#msgbox").fadeOut(1800);
    });
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    $(grid_selector).jqGrid({
        url: path + "/audit/list",
        datatype: "json",
        mtype: "GET",
        height: 'auto',
        colNames: ['ID', '用户名', 'IP', 'IP所属', '登陆时间', 'User-Agent', '浏览器版本信息', '操作系统信息'],
        colModel: [
            {name: 'id', index: 'id', width: 10, editable: false, sortable: false},
            {name: 'user', index: 'user', width: 12, editable: false, sortable: false},
            {name: 'ip', index: 'ip', width: 15, editable: false, sortable: false},
            {name: 'ipinfo', index: 'ipinfo', width: 30, editable: false, sortable: false},
            {name: 'logintime', index: 'logintime', width: 25, editable: false, sortable: false},
            {name: 'useragent', index: 'useragent', width: 25, editable: false, sortable: false},
            {name: 'browser', index: 'browser', width: 16, editable: false, sortable: false},
            {name: 'osinfo', index: 'osinfo', width: 18, editable: false, sortable: false}
        ],
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30, 50],
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
        editurl: path + "/audit/edit",
        caption: "登陆日志",
        autowidth: true,
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        }
    });

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
            search: true,
            searchicon: 'icon-search orange',
            refresh: true,
            refreshicon: 'icon-refresh green',
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