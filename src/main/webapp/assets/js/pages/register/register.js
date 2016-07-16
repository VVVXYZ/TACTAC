$(function () {
    $('a[href*=#]:not([href=#])').click(function () {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                $('html,body').animate({
                    scrollTop: target.offset().top - 50
                }, 1000);
                return false;
            }
        }
    });
    var path = window.location.pathname.split("/")[0];

    jQuery.validator.addMethod("telphoneValid", function(value, element) {
        var tel = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        return tel.test(value) || this.optional(element);
    }, "请输入正确的手机号码");
    // Handle login
    $('#register-form').validate({
        //errorElement: 'span', //default input error message container
        //errorClass: 'help-block', // default input error message class
        //focusInvalid: false, // do not focus the last invalid input
        rules: {
            email: {
                required: true,
                email:true
            },
            tel: {
                required: true,
                telphoneValid:true
            },
            register_password: {
                required: true,
                rangelength: [6,18]
            },
            register_password_confirm: {
                required: true,
                equalTo: "#register_password"
            },
            agreement: {
                required: true
            }

        },
        messages: {
            email: {
                required: "邮箱不能为空.",
                email:" 请输入正确的邮箱."
            },
            tel: {
                required: "手机号码不能为空.",
                telphoneValid:" 请输入正确的手机号码."
            },
            register_password: {
                required: "密码不能为空.",
                rangelength: "密码长度必须介于 6 和 18 之间的字符串."
            },
            register_password_confirm: {
                required: "密码不能为空.",
                rangelength: "密码长度必须介于 6 和 18 之间的字符串.",
                equalTo: "两次密码不一致"
            },
            agreement:{
                required: "必须同意协议."
            }
        },
        //invalidHandler: function (event, validator) { //display error alert on form submit
        //    $('.alert-danger', $('#login-form')).show();
        //    $("#usernamePwdNull").removeClass('display-hide');
        //},
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

            $.ajax({
                type: "POST",
                url: path+"/user/common/registerCompanyAccount",
                data: {
                    username: $('#email').val(),
                    email: $('#email').val(),
                    tel: $('#tel').val(),
                    password: $('#register_password').val(),
                    campusId: $('#campus').val(),
                    incubatorId: $('#incubator').val()
                },
                dataType: 'json',
                success: function (data) {
                    console.log("data toString="+" "+data.success+" "+data.msg)
                    if(data.success == true){
                        bootbox.dialog({
                            message: "<span class='bigger-110'>"+data.msg+"</span>",
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
                    } else {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>"+data.msg+"</span>",
                            buttons:
                            {
                                "success" :
                                {
                                    "label" : "<i class='icon-ok'></i> 确定",
                                    "className" : "btn-sm btn-success",
                                    "callback": function() {
                                        //window.location.href=path+"/appeal/show";
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

    $('#register-form').keypress(function (e) {
        if (e.which == 13) {
            if ($('#register-form').validate().form()) {
                $('#register-form').submit(); //form validation success, call ajax form submit
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