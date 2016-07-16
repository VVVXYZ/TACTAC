/**
 * Created by Geo on 2016/2/29.
 */
$(function(){
    //console.log("path = " + path);
    $("#funDeductForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,
        rules: {
            memberName: {
                required: true
            },
            deductType: {
                required: true
            },
            deductAmount: {
                required: true
            },
            authCode: {
                required: true,
                remote:{
                    type:"POST",
                    url:path+"/admin/authCodeUtil/checkAuthcode",
                    data:{
                        authCode:function(){return $("#authCode").val();}
                    }
                }
            }
        },
        messages: {
            memberName: {
                required: "选择会员名称"
            },
            deductType: {
                required: "选择操作类型"
            },
            deductAmount: {
                required: "请输入扣款金额"

            },
            authCode: {
                required: "请输入验证码",
                remote: jQuery.format("验证码错误")
            }
        },

        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').removeClass('has-info').addClass('has-error');  // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function(error, element) {                 //错误信息位置设置方法
            // error.insertAfter( element.parent() );              //这里的element是录入数据的对象
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
                url: path+"/admin/fundsmanage/mnlOper/rpdDeducteOper",
                data: {
                    'mmDesigner.id': $("#memberId").val(),
                    'operaType': $("input[name='deductType']:checked").val(),
                    'remark': $('#mark').val(),
                    'deductAmount': $('#deductAmount').val()
                },
                dataType: 'json',
                success: function (json) {
                    var success = json.success;
                    var msg = json.msg;
                    if(success == true){
                        bootbox.dialog({
                            message: "<span class='bigger-110'>"+ msg +"</span>",
                            buttons:
                            {
                                "success" :
                                {
                                    "label" : "<i class='icon-ok'></i> 确定",
                                    "className" : "btn-sm btn-success",
                                    "callback": function() {
                                        $('#funDeductForm')[0].reset();
                                    }
                                }
                            }
                        });
                    }else {
                        bootbox.dialog({
                            message: "<span class='bigger-110 red'>"+ msg +"</span>",
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
