+function ($) {
    $(function () {
        var table_selector = "#enterprise_st";
        $(table_selector).dataTable({
            "language": { // 汉化
                "processing": "正在加载数据...",
                "lengthMenu": "每页显示 _MENU_ 条 ",
                "zeroRecords": "没有您要搜索的记录",
                "info": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
                "infoEmpty": "记录数为0",
                //"infoFiltered": "(全部记录数 _MAX_  条)",
                "infoFiltered": "",
                "infoPostFix": "",
                "search": "搜索 ",
                "url": "",
                "paginate": {
                    "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
                    "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
                    "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
                    "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
                }
            },
            "orderMulti": false,
            "sort": true,
            "ajaxDataProp": "dataObj",
            "serverData": function (source, data, callback) {
                var table = $(table_selector).DataTable();

                var params = new Object();
                params["enterpriseId"] = $('#enterpriseId').val();
                params["rows"] = table.page.len();
                params["page"] = table.page() + 1;
                params["filed"] = table.settings()[0].aoColumns[table.order()[0][0]].name;
                params["sortOp"] = table.settings()[0].aaSorting[0][1];
                $.ajax({
                    "type": "GET",
                    "contentType": "application/json",
                    "url": source,
                    "dataType": "json",
                    "data": params, //以json格式传递
                    "success": function (resp) {
                        resp["recordsFiltered"] = resp["totalrecords"];
                        resp["recordsTotal"] = resp["totalrecords"];
                        callback(resp); //服务器端返回的对象的returnObject部分是要求的格式
                    }
                });
            },
            "lengthChange": true, //改变每页显示数据数量
            "paginate": true,//分页按钮
            "stateSave": false,
            "processing": true,
            "serverSide": true,
            "sAjaxSource": path + "/client/security/getEnterpriseSt",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                //{"data": "id", "name": "id", 'sClass': '', "bSortable": true,"visible":false},
                {"data": "stockholderName", "name": "stockholderName", 'sClass': '', "bSortable": true},
                {"data": "shareholdingRatio", "name": "shareholdingRatio", 'sClass': '', "bSortable": true},


            ],
            //"aaSorting": [[ 1, "asc" ]],

            "initComplete": function () {
            },
            "rowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });
    });
}(window.jQuery);
