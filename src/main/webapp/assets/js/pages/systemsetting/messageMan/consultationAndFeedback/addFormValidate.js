/**
 * Created by Geo on 2016/2/29.
 */
$(function(){

    $("#addForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,//提交表单后,未通过验证的表单(第一个或提交之前获得焦点的未通过验证的表单)会获得焦点
        rules: {
            mmMemberInfo: {
                required: true
            },
            contactPerson: {
                required: true
            },
            contactWay: {
                required: true
            },
            description: {
                required: true
            },
            //feedbackTime: {
            //    required: true
            //},
            replyContent: {
                required: true
            //},
            //handlingTime: {
            //    required: true
            }
        },
        messages: {
            mmMemberInfo: {
                required: "会员名"
            },
            contactPerson: {
                required: "联系人"
            },
            contactWay: {
                required: "联系方式"
            },
            description: {
                required: "问题或建议"
            },
            //feedbackTime: {
            //    required: "反馈时间"
            //},
            replyContent: {
                required: "管理员回复"
            //},
            //handlingTime: {
            //    required: "回复时间"
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
                url: path+"/admin/systemSetting/messageMan/createConsultFeedbackManage",
                data: {
                    'mmDesigner.id': $('#mmDesigner').val(),
                    'contactPerson': $('#contactPerson').val(),
                    'contactWay': $('#contactWay').val(),
                    'description': $('#description').val(),
                    'feedbackTime': $('#feedbackTime').val(),
                    'replyContent': $('#replyContent').val()//,
                    //'handlingTime': $('#handlingTime').val()
                },
                dataType: 'json',
                success: function (json) {
                    $("#consultationAndFeedback").dialog("close");
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
                    $("#consultationAndFeedback").dialog("close");
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
