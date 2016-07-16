/**
 * Created by Geo on 2016/2/29.
 */
$(function(){
    $("#refuseRechForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,
        rules: {
            refuseRechRes: {
                required: true
            }
        },
        messages: {
            refuseRechRes: {
                required: "请输入拒绝原因！"
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
                url: path+"/admin/fundsmanage/rechargeMan/recfuseOper",
                data: {
                    'id': $('#rechargeId').val(),
                    'refuseReason': $('#refuseRechRes').val()
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
                                        $("#grid-table").setGridParam().trigger("reloadGrid");
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
                                        $("#grid-table").setGridParam().trigger("reloadGrid");
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
