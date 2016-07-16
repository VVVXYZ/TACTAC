/**
 * Created by fjfzuhqc on 2016/3/23.
 */
var path = window.location.pathname.split("/")[0];
//$('#fakeDelBtn').on('click',);
function fakeDel (loanId){//彻底删除
    $("#confirmDel").removeClass('hide').dialog({
        resizable: false,
        modal: true,
        title: "确认删除？",
        buttons: [
            {
                html: "<i class='icon-trash bigger-110'></i>&nbsp; 确认删除",
                "class": "btn btn-danger btn-xs",
                click: function () {
                    $.ajax({
                        type: "POST",
                        url: path + "/admin/loanManage/allLoan/fakeDelLoan",
                        data: {
                            loanId: loanId
                        },
                        dataType: 'json',
                        success: function (json) {
                            if (json.success) {
                                //删除该行，不与后台交互，减少一次数据库读取
                                $("#grid-table").jqGrid("delRowData", loanId);
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>"+json.msg+"</span>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                //Example.show("great success");
                                            }
                                        }
                                    }
                                });
                            } else {
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>"+json.msg+"</span>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                //Example.show("great success");
                                            }
                                        }
                                    }
                                });
                            }
                        },
                        error: function (XMLHttpRequest, errorThrown) {
                            bootbox.dialog({
                                message: "<span class='bigger-110'>操作失败！请联系系统管理员解决问题.</span>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            //Example.show("great success");
                                        }
                                    }
                                }
                            });
                        }
                    });
                    $(this).dialog("close");
                }
            }
            ,
            {
                html: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
}
