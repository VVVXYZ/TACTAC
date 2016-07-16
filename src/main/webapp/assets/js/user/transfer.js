+function ($) {
    $(function () {
        $("#transferable_project_table").dataTable({
            "oLanguage": { // 汉化
                "sProcessing": "正在加载数据...",
                "sLengthMenu": "显示 _MENU_ 条 ",
                "sZeroRecords": "没有您要搜索的内容",
                "sInfo": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
                "sInfoEmpty": "记录数为0",
                "sInfoFiltered": "(全部记录数 _MAX_  条)",
                "sInfoPostFix": "",
                "sSearch": "搜索 ",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
                    "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
                    "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
                    "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
                }
            },
            "aLengthMenu": [[10, 15, 20], [10, 15, 20]],
            "bStateSave": false,
            "bProcessing": true,
            "bServerSide": true,
            //"sAjaxSource": url,//获取服务端数据配置URL
            "sAjaxSource": "/vmalogo/assets/js/user/transferable_project.json",
            "sDom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "sPaginationType": "full_numbers",
            //"aaSorting": [[ 1, "asc" ]],
            "aoColumns": [
                {
                    "mData": function (row, type, val) {

                        return '<span class="text-purple">' + row.project_name + '</span>';
                    },
                    "bSortable": false
                },
                {"mData": "refund_way", 'sClass': '', "bSortable": false},
                {"mData": "deadline", 'sClass': '', "bSortable": false},
                {"mData": "invest_amount", 'sClass': '', "bSortable": false},
                {"mData": "earnings_amount", 'sClass': '', "bSortable": false},
                {"mData": "transferable_amount", 'sClass': '', "bSortable": false},
                {"mData": "remain_amount", 'sClass': '', "bSortable": false},
                {
                    "mData": function (row, type, val) {

                        return '<a href="#transfer-modal" title="转让" class="text-link" data-toggle="modal">转让</a>';
                    },
                    "bSortable": false
                }
            ],
            "fnInitComplete": function () {
                //初始化完成回调
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });

        $("#transferring_project_table").dataTable({
            "oLanguage": { // 汉化
                "sProcessing": "正在加载数据...",
                "sLengthMenu": "显示 _MENU_ 条 ",
                "sZeroRecords": "没有您要搜索的内容",
                "sInfo": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
                "sInfoEmpty": "记录数为0",
                "sInfoFiltered": "(全部记录数 _MAX_  条)",
                "sInfoPostFix": "",
                "sSearch": "搜索 ",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
                    "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
                    "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
                    "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
                }
            },
            "aLengthMenu": [[10, 15, 20], [10, 15, 20]],
            "bStateSave": false,
            "bProcessing": true,
            "bServerSide": true,
            //"sAjaxSource": url,//获取服务端数据配置URL
            "sAjaxSource": "/vmalogo/assets/js/user/transferring_project.json",
            "sDom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "sPaginationType": "full_numbers",
            //"aaSorting": [[ 1, "asc" ]],
            "aoColumns": [
                {
                    "mData": function (row, type, val) {

                        return '<span class="text-purple">' + row.project_name + '</span>';
                    },
                    "bSortable": false
                },
                {"mData": "refund_way", 'sClass': '', "bSortable": false},
                {"mData": "invest_amount", 'sClass': '', "bSortable": false},
                {"mData": "principal_amount", 'sClass': '', "bSortable": false},
                {"mData": "earnings_amount", 'sClass': '', "bSortable": false},
                {"mData": "begin_time", 'sClass': '', "bSortable": false},
                {"mData": "end_time", 'sClass': '', "bSortable": false},
                {
                    "mData": function (row, type, val) {

                        return '<a href="#repeal-modal" title="撤销" class="text-link" data-toggle="modal">撤销</a>';
                    },
                    "bSortable": false
                }
            ],
            "fnInitComplete": function () {
                //初始化完成回调
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });

        $("#transfered_project_table").dataTable({
            "oLanguage": { // 汉化
                "sProcessing": "正在加载数据...",
                "sLengthMenu": "显示 _MENU_ 条 ",
                "sZeroRecords": "没有您要搜索的内容",
                "sInfo": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
                "sInfoEmpty": "记录数为0",
                "sInfoFiltered": "(全部记录数 _MAX_  条)",
                "sInfoPostFix": "",
                "sSearch": "搜索 ",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
                    "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
                    "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
                    "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
                }
            },
            "aLengthMenu": [[10, 15, 20], [10, 15, 20]],
            "bStateSave": false,
            "bProcessing": true,
            "bServerSide": true,
            //"sAjaxSource": url,//获取服务端数据配置URL
            "sAjaxSource": "/vmalogo/assets/js/user/transferring_project.json",
            "sDom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "sPaginationType": "full_numbers",
            //"aaSorting": [[ 1, "asc" ]],
            "aoColumns": [
                {
                    "mData": function (row, type, val) {

                        return '<span class="text-purple">' + row.project_name + '</span>';
                    },
                    "bSortable": false
                },
                {"mData": "refund_way", 'sClass': '', "bSortable": false},
                {"mData": "invest_amount", 'sClass': '', "bSortable": false},
                {"mData": "principal_amount", 'sClass': '', "bSortable": false},
                {"mData": "earnings_amount", 'sClass': '', "bSortable": false},
                {"mData": "begin_time", 'sClass': '', "bSortable": false},
                {"mData": "end_time", 'sClass': '', "bSortable": false},
                {
                    "mData": function (row, type, val) {

                        return '<a href="#protocol-modal" title="转让协议" class="text-link" data-toggle="modal">转让协议</a>';
                    },
                    "bSortable": false
                }
            ],
            "fnInitComplete": function () {
                //初始化完成回调
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });
    });
}(window.jQuery);