/**
 * Created by fjfzuhqc on 2015/12/12.
 */
//发送短信
var path = window.location.pathname.split("/")[0];
$("#sendMessage").click(function () {
    var ids = jQuery("#grid-table").jqGrid('getGridParam', 'selarrrow');//获取多行的id
    //var columnCodes=[];
    //$(ids).each(function(index,id){
    //    var rowData = jQuery("#grid-table").jqGrid('getRowData',id);
    //    console.log(rowData);
    //    columnCodes.push(rowData['lmLoanRecord.mmMemberInfo.phoneNumber']);
    //});
    if (ids.length != 0) {
        $.ajax({
            type: "POST",
            url: path + "/admin/loanManage/loanRecord/sendMessage",
            data: {
                'repayId':-1,
                'ids': ids.toString()
            },
            dataType: 'json',
            success: function (data) {
                if (data.success == true) {
                    bootbox.dialog({
                        message: "<span class='bigger-110'>" + data.msg + "</span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {
                                    $('#modal-form').modal('hide');
                                    //window.location.reload();
                                    jQuery("#grid-table").setGridParam({url:path +"/admin/loanManage/loanRecord/getPndRepayList"}).trigger("reloadGrid");
                                }
                            }
                        }
                    });

                }
                else {
                    bootbox.dialog({
                        message: "<span class='bigger-110'>"+ data.msg +" </span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {
                                    $('#modal-form').modal('hide');
                                    //window.location.reload();
                                    jQuery("#grid-table").setGridParam({url:path +"/admin/loanManage/loanRecord/getPndRepayList"}).trigger("reloadGrid");
                                }
                            }
                        }
                    });
                }
            },
            error: function (XMLHttpRequest, errorThrown) {
                bootbox.dialog({
                    message: "<span class='bigger-110'>发送失败！请联系系统管理员解决问题.</span>",
                    buttons: {
                        "success": {
                            "label": "<i class='icon-ok'></i> 确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                                $('#modal-form').modal('hide');
                                window.location.reload();

                            }
                        }
                    }
                });
            }

        });
    } else {
        bootbox.dialog({
            message: "<span class='bigger-110'>没有勾选发送对象，请确认.</span>",
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
