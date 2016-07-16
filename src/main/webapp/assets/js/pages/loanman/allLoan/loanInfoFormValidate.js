/**
 * Created by Geo on 2016/2/29.
 */

$(function () {
    jQuery.validator.addMethod("divideSumPeriod", function (value, element) {
        var sumPeriod = $('#sumPeriod').val();
        if (value % sumPeriod != 0) {
            return false;
        } else {
            return true;
        }
    }, "融资额无法被期数整除");

    jQuery.validator.addMethod("deadLineDivideSumPeriod", function (value, element) {
        var sumPeriod = $('#sumPeriod').val();
        if (value % sumPeriod != 0) {
            return false;
        } else {
            return true;
        }
    }, "融资期限无法被期数整除");
    jQuery.validator.addMethod("equalPeriod", function (value, element) {
        var sumPeriod = $('#sumPeriod').val();
        var unit = $("[name='projectDeadlinePeriod']:checked").val();
        var repayType = $("#repayType").val();
        if(repayType==99){
            return true;//到期还本息不需要融资期限与期数相等
        }
        if(unit==3){
            if (value!=sumPeriod) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }, "按月融资期限与期数不相等");

    jQuery.validator.addMethod("divide100", function (value, element) {

        if (value % 100 != 0) {
            return false;
        } else {
            return true;
        }
    }, "不是100的整数倍");

    jQuery.validator.addMethod("numShare", function (value, element) {
        var financingAmount = $('#financingAmount').val();
        if (value != 0) {
            if (financingAmount % value != 0) {
                return false;
            }
        }
        return true;

    }, "融资额无法被份数整除");

    jQuery.validator.addMethod("judgeNullByLoanStatus", function (value, element) {
        var loanStatus = $("[name='loanStatus']:checked").val();
        if (loanStatus == 1) {
            if (value == "") {
                return false;
            }
        }
        return true;

    }, "不能为空");
    //验证小数点后的位数
    jQuery.validator.addMethod("decimals", function (value, element, d) {
        var a = value.indexOf(".") + 1;
        if (a == 0) {
            a = value.length;
        }
        var b = value.length;
        var c = b - a;
        return this.optional(element) || c <= d;
    }, "小数点后只能两位");
    jQuery.validator.addMethod("hxBidStatus", function (value, element) {
        var loanStatus = $("[name='loanStatus']:checked").val();
        if(value==1){
            if(loanStatus!=2)
            {
                return false;
            }
        }
        return true;
    }, "托管标请选择投资中状态");
    //开始验证
    $("#loanInfoForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,//提交表单后,未通过验证的表单(第一个或提交之前获得焦点的未通过验证的表单)会获得焦点
        rules: {
            loanName: {required: true},
            briefName: {required: true},
            'loanPer': {required: true},
            'smLoanCity.id': {required: true},
            'smProjectTypes.id': {required: true},
            repayType: {required: true},
            'smModelContract.id': {required: true},
            financingAmount: {
                required: true,
                divideSumPeriod: true,
                divide100: true,
                number: true
            },
            numShare: {required: true, numShare: true},
            beginInvestAmount: {required: true, divide100: true},
            projectDate: {required: true, deadLineDivideSumPeriod: true,equalPeriod:true},//融资期限
            gracePeriod: {required: true},
            sumPeriod: {required: true},//期数
            annualInterestRate: {required: true, decimals: [2], number: true},
            beginBidTime: {judgeNullByLoanStatus: true},
            raiseBidDeadline: {required: true},//筹标期限
            bidType: {required: true,hxBidStatus:true},
            calculateInterestMode: {required: true},//计息方式
            sort: {required: true},
            projectDescribe: {required: true},
            riskControl: {required: true},
            transactionServiceCharge: {required: true},
            lateCharge: {required: true},
            badlyLateCharge: {required: true},
            investorsRebate: {required: true}
        },
        messages: {
            loanName: {required: "不能为空"},
            briefName: {required: "不能为空"},
            'loanPer': {required: "不能为空"},
            'smLoanCity.id': {required: "没有选择"},
            'smProjectTypes.id': {required: "没有选择"},
            repayType: {required: "没有选择"},
            'smModelContract.id': {required: "没有选择"},
            financingAmount: {required: "不能为空", number: "请输入有效数字"},
            beginInvestAmount: {required: "不能为空"},
            numShare: {required: "不能为空"},
            projectDate: {required: "不能为空"},//融资期限
            gracePeriod: {required: "不能为空"},//宽限期限
            sumPeriod: {required: "不能为空"},//期数
            annualInterestRate: {required: "不能为空", number: "请输入有效数字"},
            //beginBidTime: {required: "不能为空"},
            raiseBidDeadline: {required: "不能为空"},//筹标期限
            bidType: {required: "没有选择"},
            calculateInterestMode: {required: "不能为空"},//计息方式
            sort: {required: "不能为空"},
            projectDescribe: {required: "不能为空"},
            riskControl: {required: "不能为空"},
            transactionServiceCharge: {required: "不能为空"},
            lateCharge: {required: "不能为空"},
            badlyLateCharge: {required: "不能为空"},
            investorsRebate: {required: "不能为空"}
        },

        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').removeClass('has-info').addClass('has-error');  // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {                             //错误信息位置设置方法
            // error.insertAfter( element.parent() );                            //这里的element是录入数据的对象
            if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                var controls = element.closest('div[class*="col-"]');
                if (controls.find(':checkbox,:radio').length > 1) controls.append(error);
                else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            }
            else error.insertAfter(element.parent());
        },
        submitHandler: function (form) {
            // form validation success, call ajax form submit
            //submitFun();
            submitForm("#loanInfoForm");
        }
    });


});


function alertMessage(msg) {
    bootbox.dialog({
        message: "<span class='bigger-110'>" + msg + "</span>",
        buttons: {
            "success": {
                "label": "<i class='icon-ok'></i> 确定",
                "className": "btn-sm btn-success",
                "callback":function(){
                    window.location.href = path + '/admin/loanManage/allLoan/loanList';
                }
            }
        }
    });
}

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
                                window.location.href = path + '/admin/loanManage/hxLoan/hxLoanList';
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

function submitForm(form) {
    var bidType = $("#bidType").val();
    if(bidType==0){//平台标
        $(form).ajaxSubmit({
            //target:,
            url: path + "/admin/loanManage/allLoan/addLoanRecord",
            type: "POST",
            dataType:"json",
            //resetForm:false,
            clearForm:false,
            beforeSubmit:function(formData, jqForm, options){
                return true;
            },
            success:function(responseText,statusText){
                alertMessage(responseText.msg);
            },
            error:function(XMLHttpRequest, errorThrown){
                alertMessage("错误代码"+XMLHttpRequest.status+",请重试后联系管理员");
            }
        });
    }else{
        $(form).ajaxSubmit({
            //target:,
            url: path + "/admin/loanManage/hxLoan/addLoanRecord",
            type: "POST",
            //dataType:"json",
            //resetForm:false,
            clearForm:false,
            beforeSubmit:function(formData, jqForm, options){
                return true;
            },
            success:function(responseText,statusText){
                //document.write(responseText);
                //document.close();
                if(responseText.success){
                    bootbox.dialog({
                        title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>新发布标处理情况</strong>",
                        message: "<span class='bigger-110'>" + responseText.msg + "</span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback":function(){
                                    queryStatus(responseText.hxLoanId);
                                }
                            },
                            "fail":{
                                "label": "<i class='icon-ok'></i> 标新增失败,重新提交",
                                "className": "btn-sm btn-danger",
                                "callback":function(){
                                    window.location.reload();
                                }
                            }
                        }
                    });
                    setTimeout(function(){
                        window.open(path + '/admin/loanManage/hxLoan/addLoanRecord/'+responseText.hxLoanId);
                    },3000);

                }else{
                    bootbox.dialog({
                        message: "<span class='bigger-110'>" + responseText.msg + "</span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback":function(){
                                }
                            }
                        }
                    });
                }

            },
            error:function(XMLHttpRequest, errorThrown){
                alertMessage("错误代码"+XMLHttpRequest.status+",请重试后联系管理员");
            }
        });
    }
}
