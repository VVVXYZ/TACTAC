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

    // Handle login
    $('#login-form').validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            },
            remember: {
                required: false
            }
        },
        messages: {
            username: {
                required: "用户名不能为空."
            },
            password: {
                required: "密码不能为空."
            }
        },
        invalidHandler: function (event, validator) { //display error alert on form submit
            $('.alert-danger', $('#login-form')).show();
            $("#usernamePwdNull").removeClass('display-hide');
        },
        highlight: function (element) { // hightlight error inputs
            console.log("aa");
            $(element).closest(".form-group").addClass('has-error'); // set error class to the control group
        },
        success: function (label) {
            label.closest(".form-group").removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element);
        },
        submitHandler: function (form) {
            $.ajax({
                type: "POST",
                url: path+"/login",
                data: {
                    username: $('#username').val(),
                    password: $('#password').val()
                },
                dataType: "json",
                success: function (json) {
                    if(json.success == true){
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-success'><strong>验证通过，正在登录中...</strong><br/></div>"
                        });
                        location.href=path+"/";
                    } else {
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>"+json.message+"</strong><br/></div>",
                            buttons:
                            {
                                "success" :
                                {
                                    "label" : "<i class='icon-ok'></i> 确定",
                                    "className" : "btn-sm btn-success",
                                    "callback": function() {}

                                }
                            }
                        });
                    }
                },
                error: function (XMLHttpRequest, errorThrown) {
                    bootbox.dialog({
                        closeButton: false,
                        title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                        message: "<div class='alert alert-danger'><strong>"+"联系管理员，错误代码：" + XMLHttpRequest.status+"</strong><br/></div>",
                        buttons:
                        {
                            "success" :
                            {
                                "label" : "<i class='icon-ok'></i> 确定",
                                "className" : "btn-sm btn-success",
                                "callback": function() {}

                            }
                        }
                    });
                }
            });
        }
    });
    $('#login-form').keypress(function (e) {
        if (e.which == 13) {
            if ($('#login-form').validate().form()) {
                $('#login-form').submit(); //form validation success, call ajax form submit
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
