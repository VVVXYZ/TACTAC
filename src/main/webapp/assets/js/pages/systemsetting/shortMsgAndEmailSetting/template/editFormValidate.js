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
            templateType2: {
                required: true
            },
            templateName2: {
                required: true
            },
            templateCode2: {
                required: true
            },
            ytxTemplateId2: {
                required: true
            },
            templateContent2: {
                required: true
            }
        },
        messages: {
            id2: {
                required: ""
            },
            templateType2: {
                required: "模板类型"
            },
            templateName2: {
                required: "模板名称"
            },
            templateCode2: {
                required: "模板编码"
            },
            ytxTemplateId2: {
                required: "云通讯模板编号"
            },
            templateContent2: {
                required: "模板内容"
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
                url: path+"/admin/systemSetting/shortMsgAndEmailSetting/updateMessageTemplate",
                data: {
                    'id': $('#id2').val(),
                    'templateType': $('input[name="templateType2"]:checked').val(),
                    'templateName': $('#templateName2').val(),
                    'templateCode': $('#templateCode2').val(),
                    'ytxTemplateId': $('#ytxTemplateId2').val(),
                    'templateContent': $('#templateContent2').val()
                },
                dataType: 'json',
                success: function (json) {
                    $("#messageTemplate2").dialog("close");
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
                    $("#messageTemplate2").dialog("close");
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
