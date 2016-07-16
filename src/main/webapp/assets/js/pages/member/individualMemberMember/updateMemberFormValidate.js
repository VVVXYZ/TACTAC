/**
 * Created by passerby on 2016/3/17.
 */
$(function(){
    $("#updateMemberForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,//提交表单后,未通过验证的表单(第一个或提交之前获得焦点的未通过验证的表单)会获得焦点
        rules: {
            phoneNumber:{
                required:true
            },
            email:{
                required:false,
                email:true
            }
        },
        messages: {
            phoneNumber:{
                required:"电话号码必填！"
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
                url: path+"/admin/memberman/individualMember/update",
                data: $(form).serializeArray(),
                dataType: 'json',
                success: function (json) {
                    bootbox.dialog({
                        message: "<span class='bigger-110'>"+json.msg+"</span>",
                        buttons:
                        {
                            "success" :
                            {
                                "label" : "<i class='icon-ok'></i> 确定",
                                "className" : "btn-sm btn-success",
                                "callback": function() {
                                    if(json.success){
                                        window.location.href = path+"/admin/memberman/individualMember/memberList";
                                    }
                                }
                            }
                        }
                    });

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