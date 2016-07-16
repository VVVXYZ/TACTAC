/**
 * Created by QiKai on 2014/4/27.
 */
$(function () {
    var path = window.location.pathname.split("/")[0];
    $("#changePwdForm").validate({
        rules: {
            newPassword: {
                required: true
            },
            confirm: {
                required: true,
                equalTo: "#newPassword"
            }
        },
        messages: {
            newPassword: {
                required: "&nbsp;* 新密码不能为空！"
            },
            confirm: {
                required: "&nbsp;* 密码不能为空！",
                equalTo: "两次输入的密码不一致"
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
            $.ajax(path + "/user/changePassword",{
                type: "post",
                data: {
                    newPassword: $('#newPassword').val()
                },
                success: function(data){
                    bootbox.dialog({
                        message: "<span class='bigger-110'>操作成功！</span>",
                        buttons:
                        {
                            "success" :
                            {
                                "label" : "<i class='icon-ok'></i> 确定",
                                "className" : "btn-sm btn-success",
                                "callback": function() {
                                    //Example.show("great success");
                                }
                            }
                        }
                    });
                }
            });
        }
    });
});