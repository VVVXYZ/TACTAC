/**
 * Created by QiKai on 2014/4/27.
 */
$(function () {
    $("#modifyUserPwdForm").validate({
        rules: {
            password: {
                required: true
            },
            newPassword: {
                required: true
            }
        },
        messages: {
            password: {
                required: "&nbsp;* 密码不能为空！"
            },
            newPassword: {
                required: "&nbsp;* 新密码不能为空！"
            }
        },
        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            $(element).closest('.col-sm-1').addClass('has-error'); // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {                             //错误信息位置设置方法
            error.insertAfter(element);                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (json) {
                    if(json.success){
                        $("#password").val("");
                        $("#newPassword").val("");
                    }
                    bootbox.dialog({
                        message: "<span class='bigger-110'>" + json.message + "</span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {

                                }
                            }
                        }
                    });
                },
                error: function (XMLHttpRequest, errorThrown) {
                    bootbox.dialog({
                        message: "<span class='bigger-110'>错误代码" + XMLHttpRequest.status + "，请联系管理员！</span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {
                                }
                            }
                        }
                    });
                }
            });
        }
    });
});