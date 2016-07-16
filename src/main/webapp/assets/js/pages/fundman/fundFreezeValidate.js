/**
 * Created by Geo on 2016/2/29.
 */
$(function(){
    $("#fundFreezeForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,
        rules: {
            memberName: {
                required: true
            },
            operaType: {
                required: true
            },
            freezeAmount: {
                required: true
            }
        },
        messages: {
            memberName: {
                required: "选择会员名称"
            },
            operaType: {
                required: "选择操作类型"
            },
            freezeAmount: {
                required: "请输入冻结金额"
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
                url: path+"/admin/fundsmanage/mnlOper/freezeFundOper",
                data: {
                    'mmDesigner.id': $("#memberId").val(),
                    'operaType': $("input[name='operaType']:checked").val(),
                    'remark': $('#mark').val(),
                    'freezeAmount': $('#freezeAmount').val()
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
                                        $('#fundFreezeForm')[0].reset();
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
