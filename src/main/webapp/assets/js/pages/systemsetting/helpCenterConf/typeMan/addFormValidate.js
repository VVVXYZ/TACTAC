/**
 * Created by Geo on 2016/2/29.
 */
$(function(){

    $("#addForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,//提交表单后,未通过验证的表单(第一个或提交之前获得焦点的未通过验证的表单)会获得焦点
        rules: {
            signedNumber: {
                required: true
            },
            credential: {
                required: true
            },
            desSecretKey: {
                required: true
            },
            desVectorQuantity: {
                required: true
            },
            accountTypeName: {
                required: true
            },
            merchantNo: {
                required: true
            }
        },
        messages: {
            signedNumber: {
                required: "签约号"
            },
            credential: {
                required: "证书"
            },
            desSecretKey: {
                required: "3DES密钥"
            },
            desVectorQuantity: {
                required: "3DES向量"
            },
            accountTypeName: {
                required: "账号类名"
            },
            merchantNo: {
                required: "商户号"
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
                url: path+"/admin/systemSetting/payApiSetting/createPayInterfaceParamentSet",
                data: {
                    'signedNumber': $('#signedNumber').val(),
                    'credential': $('#credential').val(),
                    'desSecretKey': $('#desSecretKey').val(),
                    'desVectorQuantity': $('#desVectorQuantity').val(),
                    'accountTypeName': $('#accountTypeName').val(),
                    'merchantNo': $('#merchantNo').val()
                },
                dataType: 'json',
                success: function (json) {
                    $("#payInterfaceParamentSet").dialog("close");
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
                    $("#payInterfaceParamentSet").dialog("close");
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
