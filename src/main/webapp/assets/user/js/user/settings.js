/**
 * Created by lw on 2016/5/6.
 */
+function ($) {
    $(function () {
        $('#identityAuthentication').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                realName: {
                    required: true
                },
                identificationNumber: {
                    required: true,
                    digits: true,
                    rangelength: [18, 18]
                },
                birthDate: {
                    required: true
                },
                identityCardFronUrl: {required: true},
                identityCardContraryUrl: {required: true}
            },
            messages: {
                realName: {
                    required: "真实姓名不能空"
                },
                identificationNumber: {
                    required: "身份证号不能为空",
                    digits: "身份证号只能输入数字",
                    rangelength: "身份证位数不对"
                },
                birthDate: {
                    required: "出生日期不能为空"
                },
                identityCardFronUrl: {required: "身份证照片不能为空"},
                identityCardContraryUrl: {required: "身份证照片不能为空"}

            },
            invalidHandler: function (event, validator) { //display error alert on form submit

            },
            highlight: function (element) { // hightlight error inputs
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
                $(form).ajaxSubmit({
                    //target:,
                    url: path + "/client/security/identityAuthentication",
                    type: "POST",
                    dataType: "json",
                    //resetForm:false,
                    clearForm: false,
                    beforeSubmit: function (formData, jqForm, options) {
                        return true;
                    },
                    success: function (data) {
                        if (data.success == true) {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-success'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/security";
                                        }
                                    }
                                }
                            });
                        } else {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-danger'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/security";
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        var msg = "联系管理员，错误代码：" + XMLHttpRequest.status
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>" + msg + "</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/security";
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    });

    $(function () {
        $('#changePassword').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                oldPassword: {
                    required: true
                },
                newPassword: {
                    required: true,
                    rangelength: [6, 20]

                },
                aganiNewPassword: {
                    equalTo: "#newPassword"
                },
                loginVerificationCode: {
                    required: true
                }
            },
            messages: {
                oldPassword: {
                    required: "登录密码不能为空"
                },
                newPassword: {
                    required: "新登录密码不能为空",
                    rangelength: "密码必须在6到20位之间"
                },
                aganiNewPassword: {
                    equalTo: "两次输入的登录密码必须一致"
                },
                loginVerificationCode: {
                    required: "验证码不能为空"
                }
            },
            invalidHandler: function (event, validator) { //display error alert on form submit

            },
            highlight: function (element) { // hightlight error inputs
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
                    url: path + "/client/security/webChangePassword",
                    data: {
                        oldPassword: $('#oldPassword').val(),
                        newPassword: $('#newPassword').val(),
                        verificationCode: $('#loginVerificationCode').val()
                    },
                    dataType: 'json',
                    success: function (data) {
                        //data = $.parseJSON(data);
                        //console.log("data toString= " + data);
                        //console.log("data success=" + " " + data.success + " " + data.msg);
                        if (data.success == true) {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-success'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/logout";
                                        }
                                    }
                                }
                            });
                        } else {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-danger'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/security";
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        var msg = "联系管理员，错误代码：" + XMLHttpRequest.status
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>" + msg + "</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/security";
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    });

    $("#getLoginVerificationCode").bind('click', function () {
        var i = 0;
        var interval = setInterval(function () {
            i++;
            if (i == 60) {
                $("#getLoginVerificationCode").attr("disabled", false);
                $("#getLoginVerificationCode").text("获取短信验证码");
                window.clearInterval(interval);
            } else {
                $("#getLoginVerificationCode").attr("disabled", true);
                $("#getLoginVerificationCode").text((60 - i) + "s后重新获取");
            }
        }, 1000);

        $.ajax({
            type: "post",
            url: path + "/client/sendIdentifyingCode",
            data: {
                phoneNumber: $('#parameterPhone').val(),
                identifyingType: 6

            },
            dataType: 'json',
            success: function (json) {
                //$("#message").html(json.msg);

            }
        });


    });

    $(function () {
        $('#BindingMailbox').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                bindindEmail: {
                    required: true,
                    email: true

                },
                mailVerificationCode: {
                    required: true
                }
            },
            messages: {
                bindindEmail: {
                    required: "邮箱地址不能为空",
                    email: "必须输入正确的邮箱格式"
                },
                mailVerificationCode: {
                    required: "验证码不能为空"
                }
            },
            invalidHandler: function (event, validator) { //display error alert on form submit

            },
            highlight: function (element) { // hightlight error inputs
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
                    url: path + "/client/security/BindingMailbox",
                    data: {
                        bindindEmail: $('#bindindEmail').val(),
                        verificationCode: $('#mailVerificationCode').val()
                    },
                    dataType: 'json',
                    success: function (data) {
                        //data = $.parseJSON(data);
                        console.log("data toString= " + data);
                        console.log("data success=" + " " + data.success + " " + data.msg);
                        if (data.success == true) {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-success'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/security";
                                        }
                                    }
                                }
                            });
                        } else {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-danger'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/security";
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        var msg = "联系管理员，错误代码：" + XMLHttpRequest.status
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>" + msg + "</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/security";
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    });

    $(document).on("click", "#getMailVerificationCode", function () {
        var temp = document.getElementById("bindindEmail");
        //对电子邮件的验证
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!myreg.test(temp.value)) {
            alert('提示\n\n请输入有效的邮箱！');
            myreg.focus();
            return false;
        }
        var i = 0;
        var interval = setInterval(function () {
            i++;
            if (i == 60) {
                $("#getMailVerificationCode").attr("disabled", false);
                $("#getMailVerificationCode").text("获取短信验证码");
                window.clearInterval(interval);
            } else {
                $("#getMailVerificationCode").attr("disabled", true);
                $("#getMailVerificationCode").text((60 - i) + "s后重新获取");
            }
        }, 1000);
        $.ajax({
            type: "post",
            url: path + "/client/security/MailboxVerificationCode",
            data: {
                receiveMail: $('#bindindEmail').val()
            },
            dataType: 'json',
            success: function (json) {
                //$("#mailMessage").html(json.msg);

            }
        });


    });

    $(function () {
        $('#setTradersPassword').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                tradersPassword: {
                    required: true,
                    rangelength: [6, 20]

                },
                againTradersPassword: {
                    equalTo: "#tradersPassword"
                },
                tradeVerificationCode: {
                    required: true
                }
            },
            messages: {
                tradersPassword: {
                    required: "支付密码不能为空",
                    rangelength: "密码必须在6到20位之间"
                },
                againTradersPassword: {
                    equalTo: "两次输入的支付密码必须一致"
                },
                tradeVerificationCode: {
                    required: "验证码不能为空"
                }
            },
            invalidHandler: function (event, validator) { //display error alert on form submit

            },
            highlight: function (element) { // hightlight error inputs
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
                    url: path + "/client/security/setTradersPassword",
                    data: {
                        tradersPassword: $('#tradersPassword').val(),
                        verificationCode: $('#tradeVerificationCode').val()
                    },
                    dataType: 'json',
                    success: function (data) {
                        //data = $.parseJSON(data);
                        console.log("data toString= " + data);
                        console.log("data success=" + " " + data.success + " " + data.msg);
                        if (data.success == true) {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-success'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/security";
                                        }
                                    }
                                }
                            });
                        } else {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-danger'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/security";
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        var msg = "联系管理员，错误代码：" + XMLHttpRequest.status
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>" + msg + "</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/security";
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    });

    $(document).on("click", "#getTradeVerificationCode", function () {
        var i = 0;
        var interval = setInterval(function () {
            i++;
            if (i == 60) {
                $("#getTradeVerificationCode").attr("disabled", false);
                $("#getTradeVerificationCode").text("获取短信验证码");
                window.clearInterval(interval);
            } else {
                $("#getTradeVerificationCode").attr("disabled", true);
                $("#getTradeVerificationCode").text((60 - i) + "s后重新获取");
            }
        }, 1000);
        $.ajax({
            type: "post",
            url: path + "/client/sendIdentifyingCode",
            data: {
                phoneNumber: $('#parameterTradePhone').val(),
                identifyingType: 2
            },
            dataType: 'json',
            success: function (json) {
                //$("#tradeMessage").html(json.msg);

            }
        });


    });

}(window.jQuery);