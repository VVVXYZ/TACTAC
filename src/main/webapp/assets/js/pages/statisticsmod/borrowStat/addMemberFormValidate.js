/**
 * Created by passerby on 2016/3/17.
 */
$(function(){
    var path = window.location.pathname.split("/")[0];
    $("#addMemberForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,//提交表单后,未通过验证的表单(第一个或提交之前获得焦点的未通过验证的表单)会获得焦点
        rules: {
            'username': {
                required: true
            },
            phoneNumber:{
                required:true
            },
            email:{
                required:false,
                email:true
            },
            password:{
                required:true
            },
            confirmPassword:{
                required:true,
                equalTo:"#password"
            }
        },
        messages: {
            username: {
                required: "用户名必填！"
            },
            phoneNumber:{
                required:"电话号码必填！"
            },
            confirmPassword:{
                required:"确认密码必填！",
                equalTo:"密码不一致"
            },
            password:{
                required:"密码必填！"
            },
            email:{
                email:"不是合法的邮箱"
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
                url: path+"/admin/memberman/enterpriseMember/create",
                data: $(form).serializeArray(),
                dataType: 'json',
                success: function (json) {
                    if(json.success){
                        bootbox.dialog({
                            message: "<span class='bigger-110'>"+json.msg+"</span>",
                            buttons:
                            {
                                "success" :
                                {
                                    "label" : "<i class='icon-ok'></i> 继续添加",
                                    "className" : "btn-sm btn-success",
                                    "callback": function() {
                                        form.reset();
                                    }
                                },
                                "cancel":
                                {
                                    "label" : "<span class='glyphicon glyphicon-arrow-left'> 返回列表",
                                    "className" : "btn-sm btn-inverse",
                                    "callback": function() {
                                        window.location.href = "/admin/memberman/enterpriseMember/memberList";
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
    });
});