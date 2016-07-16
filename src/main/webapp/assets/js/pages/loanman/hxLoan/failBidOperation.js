/**
 * Created by fjfzuhqc on 2016/3/23.
 * 环迅流标操作
 */
//var path = window.location.pathname.split("/")[0];
//$('#fakeDelBtn').on('click',);
function queryStatus(hxLoanId){
    $.ajax({
        type: "POST",
        url: path+"/admin/loanManage/hxLoan/queryLoanRecordStatus",
        data: {
            'hxLoanId':hxLoanId
        },
        dataType: 'json',
        success: function (json) {
            if(json.success){
                bootbox.dialog({
                    message: "<span class='bigger-110'>"+json.msg+"</span>",
                    buttons:
                    {
                        "success" :
                        {
                            "label" : "<i class='icon-ok'></i> 确定",
                            "className" : "btn-sm btn-success",
                            "callback": function() {
                                window.location.reload();
                            }
                        }
                    }
                });
            }else {
                bootbox.dialog({
                    message: "<span class='bigger-110'>"+json.msg+"</span>",
                    buttons:
                    {
                        "success" :
                        {
                            "label" : "<i class='icon-ok'></i> 确定",
                            "className" : "btn-sm btn-success",
                            "callback": function() {
                            }
                        }
                    }
                });
            }

        },
        error: function (XMLHttpRequest, errorThrown) {
            bootbox.dialog({
                message: "<span class='bigger-110'>错误代码"+XMLHttpRequest.status+"，请联系管理员！</span>",
                buttons:
                {
                    "success" :
                    {
                        "label" : "<i class='icon-ok'></i> 确定",
                        "className" : "btn-sm btn-success",
                        "callback": function() {
                        }
                    }
                }
            });
        }
    });
}
function failBidRepay(hxLoanId){
    $.ajax({
        type: "POST",
        url: path+"/admin/loanManage/hxLoan/failBidRepay",
        data: {
            'hxLoanId':hxLoanId,
            'failureBidReason':$("#bidFailRes").val()
        },
        dataType: 'json',
        success: function (json) {
            if(json.success){
                window.open(path+"/admin/loanManage/hxLoan/hxLoanRecordFail/"+hxLoanId);
                bootbox.dialog({
                    title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>流标处理情况</strong>",
                    message: "<span class='bigger-110'>" + json.msg + "</span>",
                    buttons: {
                        "success": {
                            "label": "<i class='icon-ok'></i> 确定",
                            "className": "btn-sm btn-success",
                            "callback":function(){
                                queryStatus(hxLoanId);
                            }
                        },
                        "fail":{
                            "label": "<i class='icon-ok'></i> 流标处理失败，请重新发起",
                            "className": "btn-sm btn-danger",
                            "callback":function(){
                                window.location.reload();
                            }
                        }
                    }
                });
            }else {
                bootbox.dialog({
                    message: "<span class='bigger-110'>"+json.msg+"</span>",
                    buttons:
                    {
                        "success" :
                        {
                            "label" : "<i class='icon-ok'></i> 确定",
                            "className" : "btn-sm btn-success",
                            "callback": function() {
                            }
                        }
                    }
                });

            }

        },
        error: function (XMLHttpRequest, errorThrown) {
            bootbox.dialog({
                message: "<span class='bigger-110'>错误代码"+XMLHttpRequest.status+"，请联系管理员！</span>",
                buttons:
                {
                    "success" :
                    {
                        "label" : "<i class='icon-ok'></i> 确定",
                        "className" : "btn-sm btn-success",
                        "callback": function() {
                        }
                    }
                }
            });
        }
    });
}