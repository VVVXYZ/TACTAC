$(function () {
    // Handle
    $('#login').validate({
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
            $('.alert-danger', $('#login')).show();
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
                url: path+"/client/login",
                data: {
                    username: $('#username').val(),
                    password: $('#password').val()
                },
                dataType: 'json',
                success: function (data) {
                    //data = $.parseJSON(data);
                    console.log("data toString= "+data);
                    console.log("data success="+" "+data.success+" "+data.msg);
                    if(data.success == true){
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-success'><strong>验证通过，正在登录中...</strong><br/></div>"
                        });
                        window.location.reload();
                    } else {
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
                    messageBox("联系管理员，错误代码：" + XMLHttpRequest.status);
                }
            });
        }
    });
    $('#login').keypress(function (e) {
        if (e.which == 13) {
            if ($('#login').validate().form()) {
                $('#login').submit(); //form validation success, call ajax form submit
            }
            return false;
        }
    });
});
$(".navbar-collapse").css({maxHeight: $(window).height() - $(".navbar-header").height() + "px"});