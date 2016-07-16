$(function () {
    jQuery.validator.addMethod("telphoneValid", function(value, element) {
        var tel = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        return tel.test(value) || this.optional(element);
    }, "请输入正确的手机号码");
    // Handle login
    jQuery.validator.addMethod("usernameValid", function(value, element) {
        var uName = /^[\w\d\-_]{4,30}$/i;
        return uName.test(value) || this.optional(element);
    }, "请输入正确的用户名");
    $("#register_for").validate({

        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        rules: {
            username: {
                required: true,
                usernameValid: true
            },
            phoneNumber: {
                required: true,
                telphoneValid:true
            },
            passWord: {
                required: true,
                rangelength: [6,20]
            },
            rePassWord: {
                required: true,
                rangelength: [6,20],
                equalTo: "#passWord"
            },
            account_type: {
                required: true
            },
            agreement: {
                required: true
            },
            verificationCode:{
                required: true
            }

        },
        messages: {
            username: {
                required: "用户名不能为空.",
                usernameValid: "用户名长度必须介于 4和 30 之间的英文字母、数字、“-”和“_”."
            },
            phoneNumber: {
                required: "手机号码不能为空.",
                telphoneValid:" 请输入正确的手机号码."
            },
            passWord: {
                required: "密码不能为空.",
                rangelength: "密码长度必须介于 6 和 20 之间的字符串."
            },
            memberType: {
                required: "密码不能为空."
            },
            rePassWord: {
                required: "密码不能为空.",
                rangelength: "密码长度必须介于 6 和 18 之间的字符串.",
                equalTo: "两次密码不一致"
            },
            agreement:{
                required: "必须同意协议."
            },
            verificationCode:{
                required: "验证码不能为空."
            }
        },

        invalidHandler: function (event, validator) { //display error alert on form submit
            $('.alert-danger', $('#register_for')).show();
            $("#usernamePwdNull").removeClass('display-hide');
        },
        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            $(element).closest('.col-sm-1').addClass('has-error'); // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
            $(".chosen-choices").css('border-color', '#b5b5b5');
        },

        submitHandler: function (form) {
            console.log("=======f=fff");
            $.ajax({
                type: "POST",
                url: path + "/client/register",
                data: {
                    phoneNumber: $("#phoneNumber").val(),
                    password: $("#passWord").val(),
                    memberType: $('#memberType input[name="account_type"]:checked ').val(),
                    username: $("#username").val(),
                    identifyingCode: $("#verificationCode").val()
                },

                dataType: 'json',
                success: function (data) {
                    console.log("data toString="+" "+data.success+" "+data.msg)
                    if(data.success == true){
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>"+data.msg+"</strong><br/></div>",
                            buttons:
                            {
                                "success" :
                                {
                                    "label" : "<i class='icon-ok'></i> 确定",
                                    "className" : "btn-sm btn-success",
                                    "callback": function() {
                                        location.href=path+"/user/index/frontindex";
                                    }
                                }
                            }
                        });
                    } else {
                        /*bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>" + data.msg + "</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "user/index/frontindex";
                                    }
                                }
                            }
                        });*/
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>"+data.msg+"</strong><br/></div>",
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
                    messageBox("注册失败，错误代码：" + XMLHttpRequest.status);
                }
            });
            //form.submit(); // form validation success, call ajax form submit
        }
    });

    $('#register_for').keypress(function (e) {
        if (e.which == 13) {
            if ($('#register_for').validate().form()) {
                alert("回车");
                //$('#register_for').submit(); //form validation success, call ajax form submit
            }
            return false;
        }
    });


});

/*jQuery(document).ready(function($){
 var $timeline_block = $('.cd-timeline-block');

 //hide timeline blocks which are outside the viewport
 $timeline_block.each(function(){
 if($(this).offset().top > $(window).scrollTop()+$(window).height()*0.75) {
 $(this).find('.cd-timeline-img, .cd-timeline-content').addClass('is-hidden');
 }
 });

 //on scolling, show/animate timeline blocks when enter the viewport
 $(window).on('scroll', function(){
 $timeline_block.each(function(){
 if( $(this).offset().top <= $(window).scrollTop()+$(window).height()*0.75 && $(this).find('.cd-timeline-img').hasClass('is-hidden') ) {
 $(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden').addClass('bounce-in');
 }
 });
 });
 });*/


$(".navbar-collapse").css({ maxHeight: $(window).height() - $(".navbar-header").height() + "px" });

function messageBox(message){
    bootbox.dialog({
        message: "<span class='bigger-110'>" + message + "</span>",
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