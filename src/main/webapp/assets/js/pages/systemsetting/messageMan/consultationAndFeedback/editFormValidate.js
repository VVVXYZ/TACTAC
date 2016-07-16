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
            mmMemberInfo2: {
                required: true
            },
            contactPerson2: {
                required: true
            },
            contactWay2: {
                required: true
            },
            description2: {
                required: true
            },
            //feedbackTime2: {
            //    required: true
            //},
            replyContent2: {
                required: true
                //},
                //handlingTime2: {
                //    required: true
            }
        },
        messages: {
            id2: {
                required: ""
            },
            mmMemberInfo2: {
                required: "会员名"
            },
            contactPerson2: {
                required: "联系人"
            },
            contactWay2: {
                required: "联系方式"
            },
            description2: {
                required: "问题或建议"
            },
            //feedbackTime2: {
            //    required: "反馈时间"
            //},
            replyContent2: {
                required: "管理员回复"
                //},
                //handlingTime2: {
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
                url: path+"/admin/systemSetting/messageMan/updateConsultFeedbackManage",
                data: {
                    'id': $('#id2').val(),
                    'mmDesigner.id': $('#mmMemberInfo2').val(),
                    'contactPerson': $('#contactPerson2').val(),
                    'contactWay': $('#contactWay2').val(),
                    'description': $('#description2').val(),
                    'feedbackTime': $('#feedbackTime2').val(),
                    'replyContent': $('#replyContent2').val()//,
                    //'handlingTime': $('#handlingTime2').val()
                },
                dataType: 'json',
                success: function (json) {
                    $("#consultationAndFeedback2").dialog("close");
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
                    $("#consultationAndFeedback2").dialog("close");
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
