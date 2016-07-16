/**
 * Created by Geo on 2016/2/29.
 */
$(function(){

    $("#relParaForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,//提交表单后,未通过验证的表单(第一个或提交之前获得焦点的未通过验证的表单)会获得焦点
        rules: {
            interfaceName: {
                required: true
            },
            status: {
                required: true
            },
            feeType: {
                required: true
            },
            interfaceType: {
                required: true
            },
            payType: {
                required: true
            },
            'smPayInterfaceParamentSet': {
                required: true
            },
            'smCreditCardSet': {
                required: true
            //},
            //description: {
            //    required: true
            }
        },
        messages: {
            interfaceName: {
                required: "接口名称"
            },
            status: {
                required: "状态"
            },
            feeType: {
                required: "手续费支付方式"
            },
            interfaceType: {
                required: "接口类型"
            },
            payType: {
                required: "支付类型"
            },
            'smPayInterfaceParamentSet': {
                required: "接口参数配置"
            },
            'smCreditCardSet': {
                required: "收款银行卡"
            //},
            //description: {
            //    required: "描述"
            }
        },

        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').removeClass('has-info').addClass('has-error');  // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function(error, element) {                             //错误信息位置设置方法
            // error.insertAfter( element.parent() );                            //这里的element是录入数据的对象
            if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                var controls = element.closest('div[class*="col-"]');
                if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
                else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            }
            else error.insertAfter(element.parent());
        },
        submitHandler: function (form) {
            // form validation success, call ajax form submit
            $.ajax({
                type: "POST",
                url: path+"/admin/systemSetting/payApiSetting/createPayInterfaceSet",
                data: {
                    'interfaceName': $('#interfaceName').val(),
                    'status': $('input[name="status"]:checked').val(),
                    'feeType': $('#feeType').val(),
                    'interfaceType': $('#interfaceType').val(),
                    'payType': $('#payType').val(),
                    'smPayInterfaceParamentSet.id': $('#smPayInterfaceParamentSet').val(),
                    'smCreditCardSet.id': $('#smCreditCardSet').val(),
                    'description': $('#description').val()
                },
                dataType: 'json',
                success: function (json) {
                    success = json.success;
                    if(success == true){
                        bootbox.dialog({
                            message: "<span class='bigger-110'>添加成功操作成功！</span>",
                            buttons:
                            {
                                "success" :
                                {
                                    "label" : "<i class='icon-ok'></i> 确定",
                                    "className" : "btn-sm btn-success",
                                    "callback": function() {
                                        location.replace(location.href);//刷新页面
                                    }
                                }
                            }
                        });
                    }else {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>添加成功操作失败，请重试！</span>",
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
    });
});
