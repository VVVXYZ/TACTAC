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
            signedNumber2: {
                required: true
            },
            credential2: {
                required: true
            },
            desSecretKey2: {
                required: true
            },
            desVectorQuantity2: {
                required: true
            },
            accountTypeName2: {
                required: true
            },
            merchantNo2: {
                required: true
            }
        },
        messages: {
            id2: {
                required: ""
            },
            signedNumber2: {
                required: "签约号"
            },
            credential2: {
                required: "证书"
            },
            desSecretKey2: {
                required: "3DES密钥"
            },
            desVectorQuantity2: {
                required: "3DES向量"
            },
            accountTypeName2: {
                required: "账号类名"
            },
            merchantNo2: {
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
                url: path+"/admin/systemSetting/payApiSetting/updatePayInterfaceParamentSet",
                data: {
                    'id': $('#id2').val(),
                    'signedNumber': $('#signedNumber2').val(),
                    'credential': $('#credential2').val(),
                    'desSecretKey': $('#desSecretKey2').val(),
                    'desVectorQuantity': $('#desVectorQuantity2').val(),
                    'accountTypeName': $('#accountTypeName2').val(),
                    'merchantNo': $('#merchantNo2').val()
                },
                dataType: 'json',
                success: function (json) {
                    $("#payInterfaceParamentSet2").dialog("close");
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
                    $("#payInterfaceParamentSet2").dialog("close");
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
