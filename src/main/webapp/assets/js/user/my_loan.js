+function ($) {
    $(function () {
        $("#all_load_table").dataTable({
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
            "sAjaxSource": "/breakFast/assets/js/user/all_loan.json",
            "sDom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "sPaginationType": "full_numbers",
            //"aaSorting": [[ 1, "asc" ]],
            "aoColumns": [
                {
                    "mData": function (row, type, val) {

                        return '<a href="#" title="' + row.project_name + '" class="text-purple">' + row.project_name + '</a>';
                    },
                    "bSortable": false
                },
                {"mData": "amount", 'sClass': '', "bSortable": false},
                {"mData": "time", 'sClass': '', "bSortable": false},
                {"mData": "rate", 'sClass': '', "bSortable": false},
                {"mData": "refund_way", 'sClass': '', "bSortable": false},
                {
                    "mData": function (row, type, val) {
                        var status_str = "";
                        switch (row.status) {
                            case 1:
                                status_str = "还款中";
                                break;
                            case 2:
                                status_str = "投标中";
                                break;
                            case 3:
                                status_str = "已流标";
                                break;
                            case 4:
                                status_str = "还款完毕";
                                break;
                            default :
                                break;
                        }
                        return status_str;
                    },
                    "bSortable": false
                },
                {
                    "mData": function (row, type, val) {

                        return '<a href="/breakFast/user/contract.html" title="查看合同" class="text-link m-l-sm" target=“_blank” >[查看合同]</a>';
                    },
                    "bSortable": false
                },
                {
                    "mData": function (row, type, val) {

                        return '<a href="javascript:;" title="还款" class="text-link m-l-sm loan-detail">还款</a>';
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

        $(document).on("click", ".loan-detail", function () {
            $(this).parents(".tab-pane").removeClass("active");
            $("[data-toggle='tab']").parents("li").removeClass("active");
            $("#loan_detail").show();
        });

        $("[data-toggle='tab']").click(function () {
            $("#loan_detail").hide();
            $(this).trigger("toggle");
        });
    });
}(window.jQuery);