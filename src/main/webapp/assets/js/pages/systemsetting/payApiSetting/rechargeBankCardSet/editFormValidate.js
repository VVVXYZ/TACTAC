/**
 * Created by Geo on 2016/2/29.
 */
$(function(){

    $("#editForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,//提交表单后,未通过验证的表单(第一个或提交之前获得焦点的未通过验证的表单)会获得焦点
        rules: {
            id2: {
                required: true
            },
            payee2: {
                required: true
            },
            paymentAccount2: {
                required: true
            },
            paymentMethodName2: {
                required: true
            },
            bankDeposit2: {
                required: true
            },
            effectivity2: {
                required: true
            }
        },
        messages: {
            id2: {
                required: ""
            },
            payee2: {
                required: "收款人"
            },
            paymentAccount2: {
                required: "收款账号"
            },
            paymentMethodName2: {
                required: "收款方式名称"
            },
            bankDeposit2: {
                required: "开户行"
            },
            effectivity2: {
                required: "是否有效"
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
                url: path+"/admin/systemSetting/payApiSetting/updateCreditCardSet",
                data: {
                    'id': $('#id2').val(),
                    'paymentMethodName': $('#paymentMethodName2').val(),
                    'payee': $('#payee2').val(),
                    'paymentAccount': $('#paymentAccount2').val(),
                    'bankDeposit': $('#bankDeposit2').val(),
                    'effectivity': $('input[name="effectivity2"]:checked').val()
                },
                dataType: 'json',
                success: function (json) {
                    $("#creditCardSet2").dialog("close");
                    var success = json.success;
                    if(success == true){
                        bootbox.dialog({
                            message: "<span class='bigger-110'>操作成功！</span>",
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
                            message: "<span class='bigger-110'>操作失败，请重试！</span>",
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
                    $("#creditCardSet2").dialog("close");
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
